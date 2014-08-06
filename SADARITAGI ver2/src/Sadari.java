import java.util.Random;

public class Sadari

{
	Point[][] points; // 각 Points는 각 Point의 Column과 row 변화량을 담고잇음, occupy속성은 이미 쓰였을때 true
	Point now; //현재라인.

	Sadari(int row, int column) {
		points = new Point[row + 1][column]; 
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[0].length; j++) {
				points[i][j] = new Point(0, 0);
			}
		}
	}

	boolean addLine(int row, int column) { //바로 옆라인과 연결.

		column--;
		row--;

		if (!rowRangeCheck(row)) {
			return false;
		}

		if (!columnRangeCheck(column)) {
			return false;
		}

		if (!points[row][column].occupy && !points[row][column + 1].occupy) {
			points[row][column].column = 1;
			points[row][column + 1].column = -1;
			points[row][column].occupy();
			points[row][column + 1].occupy();
			return true;
		}
		return false;
	}

	boolean addLine(int fromrow, int fromcolumn, int torow, int tocolumn) { //두개의 점을 연결

		fromrow--;
		fromcolumn--;
		torow--;
		tocolumn--;

		if (!rowRangeCheck(fromrow)||!rowRangeCheck(torow)) {
			return false;
		}
		
		if (!columnRangeTwoLines(fromcolumn)||!columnRangeTwoLines(tocolumn)) {
			return false;
		}

		if (!points[fromrow][fromcolumn].occupy && !points[torow][tocolumn].occupy) {
			
			points[fromrow][fromcolumn].row = torow - fromrow;
			points[fromrow][fromcolumn].column = tocolumn - fromcolumn;
			
			points[torow][tocolumn].row = fromrow - torow;
			points[torow][tocolumn].column = fromcolumn - tocolumn;
			
			points[fromrow][fromcolumn].occupy();
			points[torow][tocolumn].occupy();
			return true;
		}
		return false;
	}

	boolean rowRangeCheck(int row) {
		if (-1 < row && row < points.length - 1) {
			return true;
		}
		return false;
	}

	boolean columnRangeCheck(int column) { 
		if (-1 < column && column < points[0].length - 1) { //바로 옆라인과 연결시 어차피 맨마직 라인은 긋지 못하므로..
			return true;
		}
		return false;
	}
	
	boolean columnRangeTwoLines(int column) {
		if (-1 < column && column < points[0].length) {
			return true;
		}
		return false;
	}

	void randomLine(int size) { //입력한 사이즈만큼 라인을 생성..
		int randomlines = 0, row, column;
		Random random = new Random();

		while (randomlines < size) {
			row = random.nextInt(points.length - 1);
			column = random.nextInt(points[0].length - 1);
			if (addLine(row, column)) {
				randomlines++;
			}
		}
	}

	int sadariTagi(int start) {
		now = new Point(0, start - 1);
		while (now.row < points.length) {
			movePoint(now);
		}
		return now.column + 1;
	}

	
	Point movePoint(Point start) {
		print(); 
		int row = start.row;
		int column = start.column;
		start.row += points[row][column].row + 1;
		start.column += points[row][column].column;

		try {
			Thread.sleep(1000); // 애니메이션을 위한 슬립.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return start;
	}

	void print() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("<사다리>");
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[0].length; j++) {
				if (now.row == i && now.column == j) {
					System.out.print("*");
				} else {
					System.out.print("|");
				}

				if (points[i][j].column == 1 && points[i][j].row == 0 ) {
					System.out.print("__");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}
}
