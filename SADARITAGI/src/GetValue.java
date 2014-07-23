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
	
	int[] Rc(Scanner s) throws Exception{
		
		String input;
		String[] strxy = new String[2];
		int[] xy = new int[2];
		
		input = s.next();
		if (input.isEmpty()){
			throw new Exception();
		}
		
		strxy = input.split(",");
				
		try{
		xy[0] = Integer.parseInt(strxy[0]);
		xy[1] = Integer.parseInt(strxy[1]);
		}
		catch (Exception e){
			throw new Exception();
		}
		
		return xy;
	}
	
}
