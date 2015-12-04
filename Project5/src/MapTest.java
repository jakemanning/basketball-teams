import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;


public class MapTest 
{
	@Test
	public void testSortByValue()
	{
		Random random = new Random(System.currentTimeMillis());
		Map<String, Integer> testMap = new LinkedHashMap<String, Integer>(1000);
		for(int i = 0 ; i < 1000 ; ++i) {
			testMap.put( "SomeString" + random.nextInt(), random.nextInt());
		}
		testMap = MapClass.sortByValue( testMap );
		assertEquals( 1000, testMap.size() );
		
		Integer previous = null;
		for(Map.Entry<String, Integer> entry : testMap.entrySet()) {
			assertNotNull( entry.getValue() );
			if (previous != null) {
				assertTrue( entry.getValue() >= previous );
			}
			previous = entry.getValue();
		}
	}

	@Test
	public void testAddToList()
	{
		Map<String,Integer> testMap = new LinkedHashMap<String,Integer>();
		for(int i = 0 ; i < 1000 ; ++i) 
		{
			testMap.put( "Hello" + i, i);
		}
		Integer two = MapClass.putInMapListIfAbsent(testMap, "Hello" + 2,2);
		assertTrue(two == testMap.get("Hello2"));
		
		assertNull(testMap.get("Hello1001"));
		
		MapClass.putInMapListIfAbsent(testMap, "Hello" + 1001,1001);
		assertNotNull(testMap.get("Hello1001"));
			
	}
	
}
