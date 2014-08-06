import junit.framework.TestCase;


public class SadariTest extends TestCase {
	
	Sadari sadari;

	protected void setUp() throws Exception {
		super.setUp();
		sadari = new Sadari(8,8);
		sadari.addLine(1,1,4,1);
		sadari.addLine(2,1);
		sadari.addLine(3,1,1,3);
		sadari.addLine(1,2,2,3);
		sadari.addLine(3,2);
		sadari.randomLine(10);
		
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testsadariTagi(){
		assertEquals(1, sadari.sadariTagi(3));
	}


}


