import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PlayerTest {

	@Before
	public void setUp() throws Exception {
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testhasSameElement() {
		Player p = new Player();
		int[] s = new int[4];
		s[0] = 1;
		s[1] = 2;
		s[2] = 3;
		s[3] = 4;
		int b = 5;
		
		//assertEquals(false, p.hasSameElement(s, b));
	}

}
