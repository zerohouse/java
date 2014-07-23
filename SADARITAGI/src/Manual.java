import java.util.Scanner;


public class Manual {
	
	

	void lnChk(int r, int c, int r2, int c2, Sadari s){
		c--;
		r--;
		c2--;
		r2--;
		try{
			if (s.sadaric[r][c] == 0 && s.sadaric[r2][c2] == 0) {
				
				s.sadaric[r][c] = c2-c;
				s.sadaric[r2][c2] = c-c2;
		
				s.sadarir[r][c] = r2-r;
				s.sadarir[r2][c2] = r-r2;
								
				System.out.println((r+1) +","+ (c+1)+"과 "+ (r2+1)+","+(c2+1) +"를 연결했습니다.");
			}
			else{
				System.out.println("이미 추가한 지점입니다.");
				
			}
		}
		catch (Exception e){
			System.out.println("잘못된 입력입니다.");
		}
	}
	
	void manAddLn(Scanner scan, GetValue get, Sadari s){
		int[] rc = new int[2];
		System.out.println(".을 입력하면 연결선 입력을 종료합니다.");
		while(true){
			System.out.print("연결점1 (row,col) : ");
			try{
				rc = get.Rc(scan);
			}
			catch (Exception e){
				return;
			}
			int r = rc[0];
			int c = rc[1];
			
			System.out.print("연결점2 (row,col) : ");
			
			try{
				rc = get.Rc(scan);
			}
			catch (Exception e){
				return;
			}
			
			int r2 = rc[0];
			int c2 = rc[1];
			
			lnChk(r,c,r2,c2,s);
		}
	}
	
	
	int sadariTagi(int start, Sadari s) {
		int r = 0;
		int c = start - 1;
		int r0,c0;
		if (c >= s.sadaric[0].length || c < 0) {
			System.out.println("없는 번호를 골랐습니다.");
			return 0;
		}
		printPath(r, c, s);
		while (r + 1 < s.sadaric.length) {
			c0 = s.sadaric[r][c];
			r0 = s.sadarir[r][c];
			c = c + c0;
			r = r + 1 + r0;
			printPath(r, c, s);
		}
		return c + 1;
	}
	
	void printPath(int r, int c, Sadari s) {
		for (int j = 0; j < s.sadaric[0].length; j++) {
			if (c == j)
				System.out.print("*");
			else
				System.out.print("|");

				System.out.print("  ");
		}
		System.out.print("("+(r+1)+","+(c+1)+")");
		System.out.println("");
		
	}
}
