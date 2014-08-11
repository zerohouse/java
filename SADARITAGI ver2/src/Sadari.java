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

	/**
	 * addLine(int fromrow, int fromcolumn, int torow, int tocolumn) method와 중복이 많은 것으로 판단된다.
	 * 이 두 개의 method에 대한 중복을 제거한다. from javajigi
	 */
	boolean addLine(int row, int column) { //바로 옆라인과 연결.

		column--;
		row--;

		if (!rowRangeCheck(row)) {
			return false;
		}

		if (!columnRangeCheck(column)) {
			return false;
		}

		/**
		 * points[row][column]가 계속해서 중복 코드로 등장하고 있다.
		 * 중복을 제거해 본다. from javajigi
		 */
		if (!points[row][column].occupy && !points[row][column + 1].occupy) {
			points[row][column].column = 1; // 객체 필드에 직접 접근하는 것은 좋은 방법이 아니다.
			points[row][column + 1].column = -1; // 객체 필드에 직접 접근하는 것은 좋은 방법이 아니다.
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

	/**
	 * 경계값을 확인하는 부분을 별도의 객체를 만들어 추출해 본다. from javajigi
	 */
	boolean rowRangeCheck(int row) {
		if (-1 < row && row < points.length - 1) {
			return true;
		}
		return false;
	}

	/**
	 * 경계값을 확인하는 부분을 별도의 객체를 만들어 추출해 본다. from javajigi
	 */
	boolean columnRangeCheck(int column) { 
		if (-1 < column && column < points[0].length - 1) { //바로 옆라인과 연결시 어차피 맨마직 라인은 긋지 못하므로..
			return true;
		}
		return false;
	}
	
	/**
	 * columnRangeCheck method와 중복되는 부분이 많다. 중복을 제거한다. from javajigi
	 */
	boolean columnRangeTwoLines(int column) {
		if (-1 < column && column < points[0].length) {
			return true;
		}
		return false;
	}

	/**
	 * 지금 사다리는 Random으로 동작하는 기능 밖에 없다.
	 * 옵션에 따라 Random으로 동작할 수도 있고, 사용자가 직접 라인을 그리는 방법 두 가지 방식을 모두 지원하고 싶다.
	 * 어떤 구주로 변경하면 좋을까? from javajigi
	 * 
	 * Random으로 생성되는 라인의 수도 자동으로 생성되도록 해야 되지 않을까?
	 * 만약 현재와 같이 구현하더라도 입력한 값에 대한 예외 처리가 필요한 것으로 판단된다. from javajigi
	 */
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
		/**
		 * 다음 로직 구현을 Point 객체 내부로 이동해 본다.
		 * Point 객체 내부로 이동했을 때와 현재의 차이점은? from javajigi
		 */
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
				/**
				 * now Point와 row/column에 해당하는 Point를 얻어온 후 Point와 Point가 같은지를 비교하는 방식으로 개선해 본다. from javajigi
				 */
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
