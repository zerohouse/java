/**
 * 객체를 추출했는데 거의 사용하지 않고 있다.
 * 객체를 만들었으면 이 객체를 활용해 많은 작업을 할 수 있도록 노력한다. from javajigi
 */
public class Point {
	/**
	 * 필드의 접근 제어자를 private으로 변경하고 구현한다. from javajigi
	 */
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
