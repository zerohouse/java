import java.util.Scanner;
import java.util.Random;

public class Sadari {
	

	public static void main(String[] args) {

		int r,c;
		int start = 0;
		int res;

		
		System.out.print("몇명인가요? ");
		Scanner scan = new Scanner(System.in);
	    while (!scan.hasNextInt()) {
		      scan.next(); // 잘못된 입력 값 버리기
		      System.err.print("에러! 다시 입력바랍니다: ");
		    }
		c = scan.nextInt();
		
		System.out.print("몇줄로 할까요? ");
	    while (!scan.hasNextInt()) {
		      scan.next(); // 잘못된 입력 값 버리기
		      System.err.print("에러! 다시 입력바랍니다: ");
		    }
		r = scan.nextInt();
		Sadari s = new Sadari(r,c);
		
		s.randomLn();
		s.printSadari();
		System.out.println("사다리가 생성되었습니다.");

		
		while(start!=-1){
			System.out.print("몇번을 고르셨나요? ");
		    while (!scan.hasNextInt()) {
			      scan.next(); // 잘못된 입력 값 버리기
			      System.err.print("에러! 다시 입력바랍니다: ");
		    }
			start = scan.nextInt();
			res = s.sadariTagi(start);
			//s.printSadari();
			System.out.println("시작:"+start + ", 도착:"+res);
		}
		scan.close();
		System.out.print("종료합니다.");
		}
	
	int[][] sadari;

	Random r = new Random();
	
	Sadari(int c, int r){
		sadari = new int [c+1][r];
	}
	
	
	
	void addLn(int c, int r){
		c--;
		r--;
			if (sadari[c][r]==0 && sadari[0].length > r-1){
				sadari[c][r]=1;
				sadari[c][r+1]=-1;
			}
		}
	
	void randomLn(){
		int ln;
		for (int i=1; i<sadari[0].length; i++){
			ln = r.nextInt(sadari.length-1);
			randomCol(ln, i);
			}		
		}	
	
	void randomCol(int ln,int i){
		int rn;
		for (int j=0;j<ln;j++){
			rn = r.nextInt(sadari.length-1);
			addLn(rn+1, i);
		}
	}

	int sadariTagi(int start){
		int c = 0;
		int r=start-1;
		if (r>=sadari[0].length || r<0){
			System.out.println("없는 번호를 골랐습니다.");
			return 0;
		}
		int type = sadari[c][r];
		printPath(c,r);
		while(c+1 < sadari.length){
			r = r + sadari[c][r];
			c++;
			printPath(c,r);
		}
		return r+1;
	}
	
	// 사다리 출력	
	
	void printPath(int c, int r){
		for (int j=0;j<sadari[0].length;j++){
			if (r==j)
				System.out.print("*");
			else
				System.out.print("|");
			if (sadari[c][j]==1)
				System.out.print("__");
			else
				System.out.print("  ");
		}
		System.out.println("");
	}
	
	void printSadari(){
	for (int i =0;i<sadari.length;i++){
		for (int j=0;j<sadari[i].length;j++){
				System.out.print("|");
			if (sadari[i][j]==1){
				System.out.print("__");
			}
			else
				System.out.print("  ");
			}
			System.out.println("");
		}
	}
	
}
