import java.util.Scanner;

public class InnOut {
	public static void main(String[] args) {

		int r, c, t;
		int start = 0;
		int res;

		
		Scanner scan = new Scanner(System.in);
		GetValue get = new GetValue();
		
		System.out.print("몇명인가요? ");
		c = get.Int(scan);
		
		System.out.print("몇줄로 할까요? ");
		r = get.Int(scan);
		
		Sadari s = new Sadari(r, c);
		
			
		System.out.println("1. 사다리 라인을 랜덤으로 생성합니다. ");
		System.out.println("2. 사다리 라인을 직접 입력합니다. ");
		System.out.print("선택해주세요! : ");
		
		
		t=1;
		
		t = get.Int(scan);
		
		//System.out.println();
		
		
		
		if (t==1){
			s.randomLn();
			s.printSadari();
			System.out.println("사다리가 생성되었습니다.");
			while (start != -1) {
				System.out.print("몇번을 고르셨나요? ");
				start = get.Int(scan);
				res = s.sadariTagi(start);
				System.out.println("시작:" + start + ", 도착:" + res);
			}			
		}
		
		
		
		else {
			Manual m = new Manual();
			m.manAddLn(scan, get, s);
			System.out.println("사다리가 생성되었습니다.");
			while (start != -1) {
				System.out.print("몇번을 고르셨나요? ");
				start = get.Int(scan);
				res = m.sadariTagi(start, s);
				System.out.println("시작:" + start + ", 도착:" + res);
			}	

		}
		
		



		scan.close();
		System.out.print("종료합니다.");
	}
	

	
	
}
