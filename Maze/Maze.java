import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

public class Maze extends Applet implements Runnable{
  Thread thread = null;
  Dimension size;
  Image back;
  Graphics buffer;

  int block[][];
  int dx[] = {0, 1, 0, -1};
  int dy[] = {1, 0, -1, 0};

  int stage;
  int marux;    /* 迷路を解く人 */
  int maruy;
  int marud;    /* 向き 0～3 */
  int oldx;     /* 1つ前の位置 */
  int oldy;

  public void init(){
    stage = 0;

    marux = 1;
    maruy = 1;
    marud = 0;
    oldx = 1;
    oldy = 1;

    size = getSize();
    back = createImage(size.width, size.height);
    buffer = back.getGraphics();

    block = new int[21][21];
    makeMaze();

    thread = new Thread(this);
    thread.start();
  }

  private void makeMaze(){
    /* 全体をクリア */
    for (int i = 0 ; i < 21 ; i++){
      for (int j = 0 ; j < 21 ; j++){
        block[i][j] = 0;
      }
    }

    /* 外枠をセット */
    for (int i = 0 ; i < 21 ; i++){
      block[0][i] = 1;
      block[20][i] = 1;
      block[i][0] = 1;
      block[i][20] = 1;
    }

    /* 基準点をセット */
    for (int i = 1 ; i <= 9 ; i++){
      for (int j = 1 ; j <= 9 ; j++){
        block[i * 2][j * 2] = 1;
      }
    }

    /* 迷路作成 */
    for (int i = 1 ; i <= 9 ; i++){
      for (int j = 1 ; j <= 9 ; j++){
        if (i == 1){
          int d = (int)(Math.random() * 4);
          block[i * 2 + dx[d]][j * 2 + dy[d]] = 1;
        }else{
          boolean flag = true;
          while(flag){
            int d = (int)(Math.random() * 3);
            if (block[i * 2 + dx[d]][j * 2 + dy[d]] == 0){
              block[i * 2 + dx[d]][j * 2 + dy[d]] = 1;
              flag = false;
            }
          }
        }
      }
    }
  }

  public void update(Graphics g){
    paint(g);
  }

  public void paint(Graphics g){
    if (stage == 0){
      buffer.setColor(Color.black);
      buffer.fillRect(0, 0, size.width, size.height);

      for (int i = 0 ; i < 21 ; i++){
        for (int j = 0 ; j < 21 ; j++){
          if (block[i][j] == 1){
            buffer.setColor(Color.white);
            buffer.fillRect(j * 15, i * 15, 15, 15);

            buffer.setColor(Color.blue);
            buffer.drawRect(j * 15 + 1, i * 15 + 1, 13, 13);
          }
        }
      }

      buffer.setColor(Color.red);
      buffer.fillRect(15 + 1, 15 + 1, 13, 13);

      buffer.setColor(Color.blue);
      buffer.fillRect(15 * 19 + 1, 15 * 19 + 1, 13, 13);

      stage = 1;
    }else if (stage == 1){
      buffer.setColor(new Color(34, 139, 34));
      buffer.fillRect(oldx * 15 + 1, oldy * 15 + 1, 13, 13);

      buffer.setColor(Color.green);
      buffer.fillRect(marux * 15 + 1, maruy * 15 + 1, 13, 13);
    }

    g.drawImage(back, 0, 0, this);
  }

  public void run(){
    while(true){
      if (stage == 0){
        makeMaze(); /* 迷路作成 */

        marux = 1;  /* スタート地点を初期化 */
        maruy = 1;
        marud = 0;
        oldx = 1;
        oldy = 1;

        repaint();

        /* 2000ミリ秒待機する */
        try{
          Thread.sleep(2000);
        }catch (InterruptedException e){
        }
      }else if (stage == 1){
        /* 迷路を解いている途中 */

        repaint();

        /* 300ミリ秒待機する */
        try{
          Thread.sleep(300);
        }catch (InterruptedException e){
        }

        move();

        if ((marux == 19) && (maruy == 19)){
          /* ゴールに着いたら地図初期化へ */
          stage = 0;
        }
      }
    }
  }

  private void move(){
    /* まず左に行けるかどうかチェック */
    int leftd = marud + 1;
    if (leftd == 4){
      leftd = 0;
    }

    int left = block[maruy + dy[leftd]][marux + dx[leftd]];

    if (left == 0){
      marud = leftd;

      oldx = marux;
      oldy = maruy;
      marux += dx[marud];
      maruy += dy[marud];

      return;
    }

    /* 次に現在の進行方向に行けるかチェック */
    int next = block[maruy + dy[marud]][marux + dx[marud]];

    while(next == 1){
      /* 行けなければ右へ右へと向きを変えて行けるかどうかチェック */
      marud--;
      if (marud == -1){
        marud = 3;
      }

      next = block[maruy + dy[marud]][marux + dx[marud]];
    }

    oldx = marux;
    oldy = maruy;
    marux += dx[marud];
    maruy += dy[marud];
  }
}