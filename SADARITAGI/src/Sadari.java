import java.util.Random;

public class Sadari

 {

	int[][] sadaric;
	int[][] sadarir;
	
	Random r = new Random();

	Sadari(int r, int c) {
		sadaric = new int[r + 1][c];
		sadarir = new int[r + 1][c];
	}

	void addLn(int r, int c) {
		c--;
		r--;
		if (sadaric[r][c] == 0 && sadaric[0].length > c - 1) {
			sadaric[r][c] = 1;
			sadaric[r][c + 1] = -1;
		}
	}
	
	void randomLn() {
		int ln;
		for (int i = 1; i < sadaric[0].length; i++) {
			ln = r.nextInt(sadaric.length - 1);
			randomCol(ln, i);
		}
	}

	void randomCol(int ln, int i) {
		int rn;
		for (int j = 0; j < ln; j++) {
			rn = r.nextInt(sadaric.length - 1);
			addLn(rn + 1, i);
		}
	}

	// 사다리 출력
	
	int sadariTagi(int start) {
		int r = 0;
		int c = start - 1;
		int r0,c0;
		if (c >= sadaric[0].length || c < 0) {
			System.out.println("없는 번호를 골랐습니다.");
			return 0;
		}
		printPath(r, c);
		while (r + 1 < sadaric.length) {
			c0 = sadaric[r][c];
			r0 = sadarir[r][c];
			c = c + c0;
			r = r + 1 + r0;
			printPath(r, c);
		}
		return c + 1;
	}
	
	void printPath(int r, int c) {
		for (int j = 0; j < sadaric[0].length; j++) {
			if (c == j)
				System.out.print("*");
			else
				System.out.print("|");
			if (sadaric[r][j] == 1)
				System.out.print("__");
			else
				System.out.print("  ");
		}
		System.out.println("");
	}

	void printSadari() {
		for (int i = 0; i < sadaric.length; i++) {
			for (int j = 0; j < sadaric[i].length; j++) {
				System.out.print("|");
				if (sadaric[i][j] == 1) {
					System.out.print("__");
				}
				else
					System.out.print("  ");
			}
			System.out.println("");
		}
	}
}
