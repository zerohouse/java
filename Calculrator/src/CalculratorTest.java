import junit.framework.TestCase;


public class CalculratorTest extends TestCase {

	Calculrator cal;
	
	@Override
	protected void setUp() throws Exception {
		
		cal = new Calculrator();
		System.out.println("setUp");
	}
	
	
	public void testAdd() {
		
		System.out.println("add");
		
		int res = cal.add(2,1);
		assertEquals(3,res);

	}
	
	public void testSub() throws Exception {
		System.out.println("sub");
		
		int res = cal.sub(2,1);
		assertEquals(1,res);
		
	}
	
	@Override
	protected void tearDown() throws Exception {
		System.out.println("end");
	
	}

}
