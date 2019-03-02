package gone;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;


public class IntegrationTest {
	
//	@Test
//	public void testExampleFromAssignment6() {
//		// Setup
//		Map<Coordinate, PebbleColor> testMap = new HashMap<>();
//		testMap.put(new Coordinate(0, 0), PebbleColor.BLACK);
//		testMap.put(new Coordinate(1, 0), PebbleColor.WHITE);
//		testMap.put(new Coordinate(2, 0), PebbleColor.WHITE);
//		testMap.put(new Coordinate(0, 1), PebbleColor.BLACK);
//		testMap.put(new Coordinate(1, 1), PebbleColor.BLACK);
//		testMap.put(new Coordinate(2, 1), PebbleColor.BLACK);
//		testMap.put(new Coordinate(0, 2), PebbleColor.BLACK);
//		testMap.put(new Coordinate(3, 3), PebbleColor.BLACK);
//		
//		BoardConfiguration testConfiguration = new BoardConfiguration(testMap);
//		Gone testGame = new Gone(testConfiguration);
//		
//		// Test
//		GameResults testResults = testGame.roundsAndBlackPebbleRemains();
//		
//		// Assert
//		assertEquals(3, testResults.getRounds());
//		assertEquals(true, testResults.isBlackPebbleRemaining());
//	}
	
	public static void main(String[] args) {
		// Setup
		Map<Coordinate, PebbleColor> testMap = new HashMap<>();
		testMap.put(new Coordinate(0, 0), PebbleColor.BLACK);
		testMap.put(new Coordinate(1, 0), PebbleColor.WHITE);
		testMap.put(new Coordinate(2, 0), PebbleColor.WHITE);
		testMap.put(new Coordinate(0, 1), PebbleColor.BLACK);
		testMap.put(new Coordinate(1, 1), PebbleColor.BLACK);
		testMap.put(new Coordinate(2, 1), PebbleColor.BLACK);
		testMap.put(new Coordinate(0, 2), PebbleColor.BLACK);
		testMap.put(new Coordinate(3, 3), PebbleColor.BLACK);
				
		BoardConfiguration testConfiguration = new BoardConfiguration(testMap);
		Gone testGame = new Gone(testConfiguration);
		
		Coordinate test1 = new Coordinate(0, 0);
		Coordinate test2 = new Coordinate(0, 0);
		System.out.println(test1.equals(test2));
		
		// Test
		//GameResults testResults = testGame.roundsAndBlackPebbleRemains();
				
		// Assert
		//assertEquals(3, testResults.getRounds());
		//assertEquals(true, testResults.isBlackPebbleRemaining());
		//System.out.println(testResults.getRounds());
		//System.out.println(testResults.isBlackPebbleRemaining());
	}
}
