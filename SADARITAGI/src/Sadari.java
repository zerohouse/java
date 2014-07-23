import java.util.Random;

public class Sadari {

	int[][] sadari;

	Random r = new Random();

	Sadari(int c, int r) {
		sadari = new int[c + 1][r];
	}

	
	
	void addLn(int c, int r) {
		c--;
		r--;
		if (sadari[c][r] == 0 && sadari[0].length > r - 1) {
			sadari[c][r] = 1;
			sadari[c][r + 1] = -1;
		}
	}
	
	
	
	void randomLn() {
		int ln;
		for (int i = 1; i < sadari[0].length; i++) {
			ln = r.nextInt(sadari.length - 1);
			randomCol(ln, i);
		}
	}

	void randomCol(int ln, int i) {
		int rn;
		for (int j = 0; j < ln; j++) {
			rn = r.nextInt(sadari.length - 1);
			addLn(rn + 1, i);
		}
	}

	int sadariTagi(int start) {
		int c = 0;
		int r = start - 1;
		if (r >= sadari[0].length || r < 0) {
			System.out.println("없는 번호를 골랐습니다.");
			return 0;
		}
		while (c + 1 < sadari.length) {
			r = r + sadari[c][r];
			c++;
		}
		return r + 1;
	}
	
	// 사다리 출력
	
	int sadariTagiWithPrint(int start) {
		int c = 0;
		int r = start - 1;
		if (r >= sadari[0].length || r < 0) {
			System.out.println("없는 번호를 골랐습니다.");
			return 0;
		}
		printPath(c, r);
		while (c + 1 < sadari.length) {
			r = r + sadari[c][r];
			c++;
			printPath(c, r);
		}
		return r + 1;
	}
	
	void printPath(int c, int r) {
		for (int j = 0; j < sadari[0].length; j++) {
			if (r == j)
				System.out.print("*");
			else
				System.out.print("|");
			if (sadari[c][j] == 1)
				System.out.print("__");
			else
				System.out.print("  ");
		}
		System.out.println("");
	}

	void printSadari() {
		for (int i = 0; i < sadari.length; i++) {
			for (int j = 0; j < sadari[i].length; j++) {
				System.out.print("|");
				if (sadari[i][j] == 1) {
					System.out.print("__");
				}
				else
					System.out.print("  ");
			}
			System.out.println("");
		}
	}
}
