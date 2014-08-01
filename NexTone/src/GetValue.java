import java.util.ArrayList;
import java.util.Scanner;

public class GetValue {
	Scanner scan;

	GetValue() {
		scan = new Scanner(System.in);
	}

	public int Int() {
		while (!scan.hasNextInt()) {
			scan.next(); // 잘못된 입력 값 버리기
			System.err.print("에러! 다시 입력바랍니다: ");
		}
		int res = scan.nextInt();
		return res;
	}

	public int[] IntArray() {
		String inputstring = scan.next();
		try {
			String[] input = inputstring.split(",");
			int[] toint = new int[input.length];
			for (int i = 0; i < input.length; i++) {
				toint[i] = Integer.parseInt(input[i]);
			}
			return toint;
		} catch (Exception e) {
			System.err.println("바꾸실 카드를 0,0의 형태로 입력해주세요.");
			int[] toint = IntArray();
			return toint;
		}
	}

	public int Dek(ArrayList<Integer> dek) {
		int select = Int() - 1;
		if (!dek.contains(select)) {
			System.err.print("유효하지 않은 덱입니다. 다시 선택해주세요: ");
			Dek(dek);
		}
		return select;
	}

	public String String() {
		String res = scan.next();
		return res;
	}
}
