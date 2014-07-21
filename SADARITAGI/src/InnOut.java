import java.util.Scanner;

public class InnOut {
	public static void main(String[] args) {

		int r, c;
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
		Sadari s = new Sadari(r, c);

		s.randomLn();
		s.printSadari();
		System.out.println("사다리가 생성되었습니다.");

		while (start != -1) {
			System.out.print("몇번을 고르셨나요? ");
			while (!scan.hasNextInt()) {
				scan.next(); // 잘못된 입력 값 버리기
				System.err.print("에러! 다시 입력바랍니다: ");
			}
			start = scan.nextInt();
			res = s.sadariTagiWithPrint(start);
			// s.printSadari();
			System.out.println("시작:" + start + ", 도착:" + res);
		}
		scan.close();
		System.out.print("종료합니다.");
	}
}
