import java.util.*;
public class hantei {
	public static void main(String[] args) {
		String card[][] = new String[5][13];
		  String item[] = new String[5];

		  String []mark = {"♦","♥","☘","♠"};
		  int save[] = new int[5];
		  int num[] = new int[5];
		  int hantei[] = new int[13];
		  int i;
		  int j;
		  int w;
		  int sub = 0;
		  int flag = 0;
		  int flag3= 0;
		  int flag4 = 0;
		  int flagst = 0;
		  int flash = 0;
		  int royal = 0;
		  Random r =  new Random();
		  //
		  for(i=0;i<13;i++) {
		   hantei[i] = 0;
		  }
		  //カードを初期化
		  for(j=0;j < 4; j++) {
		   for(i=0;i < 13;i++) {
		    if(i == 0) {
		     card[j][i] = "A";
		    }
		    else if(i < 10) {
		     w = i +1;
		   card[j][i] = "" + w;
		    }else {
		     card[j][i] = "j";
		     i++;
		     card[j][i] = "Q";
		     i++;
		     card[j][i] = "K";
		    }
		   }
		  }
		  //カードを配る
		  for(w=0;w < 5;w++) {
		   save[w] = r.nextInt(4);
		   num[w] = r.nextInt(13);
		   item[w] = card[save[w]][num[w]];
		   for(i=0,j=w;i < j;i++) {
		    for(;item[i] == item[j];) {
		     if(save[i] == save[j]) {
		    save[w] = r.nextInt(4);
		    num[w] = r.nextInt(13);
		    item[w] = card[save[w]][num[w]];
		    }
		   }
		  }
		   }
		  for(w=0;w<5;w++) {
		   System.out.println( mark[save[w]] + item[w]);

		  }
		  //判定を格納ペア判定
	       num[0] = 2;
	       num[1] = 3;
	       num[2] = 4;
	       num[3] = 5;
	       num[4] = 6;
	       save[0] = 1;
	       save[1] = 1;
	       save[2] = 1;
	       save[3] = 1;
	       save[4] = 1;
		    for(i=0;i < 5;i++) {
		     for(w=0;w < 5;w++) {
		      if(!(i==w)) {
		      if(num[i] == num[w]) {
		       hantei[num[i]] = hantei[num[i]] + 1;
		       }
		      }
		     }
		    }
		 for(i=0;i<13;i++) {
		  if(hantei[i] > 7) {
		   flag4 = flag4 +1;
		  }
		  else if(hantei[i] > 5) {
		   flag3 = flag3 +1;
		  }
		 if(hantei[i] > 1) {
		  flag++;
		 }
		 }
		//ロイヤル判定
		 Arrays.sort(num);
		 if(num[1] == 9 && num[0] == 0) {
		  royal = 1;
		 }

		 //フラッシュ判定
		 for(i=0;i<5;i++) {
		 if( save[0] == save[i]) {
		  sub++;
		 }
		 if(sub == 5) {
		  flash = 1;
		 }
		 }
		 //ストレート判定
		 Arrays.sort(num);
		 sub = 0;
		 for(i=0;i < 5;i++) {
		  if(num[0]+i == num[i]) {
		  sub++;
		  }
		  if(sub == 5) {
		   flagst = 1;
		  }
		 }

		 if(!(flagst == 1)) {
		  if(num[1] == 9 && num[0] == 0) {
		   for(i=0;i < 1;i++) {
		    num[i] = num[i]+13;
		   }
		  }else if(num[2] == 10 && num[0] == 0) {
		    for(i=0;i < 3;i++) {
		     num[i] = num[i]+13;
		    }
		  }else if(num[3] == 11 && num[0] == 0) {
		   for(i=0;i < 3;i++) {
		    num[i] = num[i]+13;
		   }
		 }else if(num[4] == 12 && num[0] == 0) {
		  for(i=0;i < 4;i++) {
		   num[i] = num[i]+13;
		  }
		 }
		 }

		 Arrays.sort(num);
		 sub = 0;
		 for(i=0;i < 5;i++) {
		  if(num[0]+i == num[i]) {
		  sub++;
		  }
		  if(sub == 5) {
		   flagst = 1;
		  }
		 }



		if(royal == 1 && flagst == 1 && flash ==1) {
		 System.out.println("ロイヤルストレートフラッシュ");
		}else if(royal == 1 && flagst == 1) {
		 System.out.println("ロイヤルストレート");
		}else if(flagst == 1 && flash == 1) {
		 System.out.println("ストレートフラッシュ");
		}else if(flagst == 1) {
		 System.out.println("ストレート");
		}else if(flash == 1) {
		 System.out.println("フラッシュ");
		}else if(flag3>0 && flag > 1) {
		  System.out.println("フルハウス");
		 }else if(flag3 > 0) {
		  System.out.println("スリーカード");
		 }else if(flag4>0) {
		  System.out.println("フォーカード");
		 }else {
		  System.out.println(flag + "ペア");
		 }
		 }
	}
