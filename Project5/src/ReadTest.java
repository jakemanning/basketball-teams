import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ReadTest 
{
	@Test
	public void testCityFile()
	{
		DataBaseModel db = new DataBaseModel();
		Map<String,City> testMap = null;
		try 
		{
			testMap = db.readCitiesFromFile("cities.csv");
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		assertNull(testMap.get("Hello"));
		assertNotNull(testMap.get("DALLASTX"));
		System.out.println(testMap.get("WEST PALM BEACHFL").getLatitude());
	}
	
	@Before
	public int testPersonFile()
	{
		DataBaseModel db = new DataBaseModel();
		Map<String,Person> testPersonMap = null;
		try 
		{
			testPersonMap = db.readDataFromFile("players.csv");
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		assertNull(testPersonMap.get("Hello"));
		assertNotNull(testPersonMap.get("LARRY NELSON STEELE"));
		System.out.println(testPersonMap.get("ANTHONY GOLDWIRE").getCityOfBirth().getLatitude());
		return 0;
	}	
	
	@Test public void testTeamFile()
	{
		DataBaseModel db = new DataBaseModel();
		
		try 
		{
			db.readDataFromFile("players.csv");
			db.readCitiesFromFile("cities.csv");
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		Map<String,State> stateMap = DataBaseModel.getJListOfStates();
		Map<String,Identification> testIdentificationMap = null;
		try
		{
			testIdentificationMap = db.readTeamsFromFile("teams2.csv");
		} catch(IOException f)
		{
			f.printStackTrace();
		}
		
		assertNull(testIdentificationMap.get("HELLO"));
		
		assertTrue(testIdentificationMap.containsKey("MIA"));
		assertTrue(testIdentificationMap.containsKey("PHI"));
	    
		System.out.println(stateMap.get("OK").getListOfCities().values());
		System.out.println(DataBaseModel.getJListOfPersons().get("WILLIAM PARKER").getSeasonList());
//		System.out.println(testIdentificationMap.get("SEA").getSeasonMap().get(2006).getRosterMap().get("RASHARD QUOVON LEWIS").getCityOfBirth().getLatitude());
//		System.out.println(stateMap.keySet());
				
	}
}
