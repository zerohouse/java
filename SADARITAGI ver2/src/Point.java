public class Point {
	int row, column;
	boolean occupy;
	
	Point(int row, int column) {
		this.row = row;
		this.column = column;
		this.occupy = false;
	}

	public void occupy() {
		this.occupy=true;
	}
}
