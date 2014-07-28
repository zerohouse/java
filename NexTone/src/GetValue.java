import java.util.Scanner;


public class GetValue {
	int Int(Scanner s){
		while (!s.hasNextInt()) {
			s.next(); // 잘못된 입력 값 버리기
			System.err.print("에러! 다시 입력바랍니다: ");
		}
		int res = s.nextInt();
		return res;
	}

}
