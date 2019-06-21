	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
    import java.util.Scanner;

import javax.swing.JFrame;

	public class hantei_BU {
		public static void main(String[] args) {
			  //自機情報入力
			  Scanner sc = new Scanner(System.in);
			  System.out.println("自機のデータを入力してください");
			  System.out.println("X座標を入力");
			  int x = sc.nextInt();
			  System.out.println("Y座標を入力");
			  int y = sc.nextInt();
			  System.out.println("高さを入力");
			  int w = sc.nextInt();
			  System.out.println("幅を入力");
			  int h = sc.nextInt();

			  //敵の数
			  System.out.println("敵の数を入力してください(1~3)");
			  int t = sc.nextInt();

			  //敵データ入力
			  int i;
			  int j = 0;
			  int teki[] = new int[20];

			  for(i = 0; i < t; i++) {
			  System.out.println("敵のデータを入力してください");
			  System.out.println("X座標を入力");
			  int tx = sc.nextInt();
			  teki[j] = tx;
			  j = j + 1;
			  System.out.println("Y座標を入力");
			  int ty = sc.nextInt();
			  teki[j] = ty;
			  j = j + 1;
			  System.out.println("高さを入力");
			  int tw = sc.nextInt();
			  teki[j] = tw;
			  j = j + 1;
			  System.out.println("幅を入力");
			  int th = sc.nextInt();
			  teki[j] = th;
			  j = j + 1;
			  }

			 }
	}