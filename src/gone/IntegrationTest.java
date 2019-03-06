package gone.src.gone;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;

import static org.junit.Assert.*;

public class IntegrationTest {

	@Test
	public void testGoneExampleFromAssignment6() {
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

		// Test
		GameResults testResults = testGame.finalGameResults();
		int rounds = testResults.getRounds();
		boolean blackRemaining = testResults.isBlackPebbleRemaining();

		// Assert
		System.out.println("Actual rounds = " + rounds);
		System.out.println("Actual black pebble remaining = " + blackRemaining);

		assertEquals(3, rounds);
		assertTrue(blackRemaining);
	}

	@Test
	public void testLineBlackPebblesOneWhite(){
		// Setup
		Map<Coordinate, PebbleColor> testMap = new HashMap<>();
		testMap.put(new Coordinate(0, 0), PebbleColor.WHITE);
		for(int i = 1; i < 10; i++){
			testMap.put(new Coordinate(i, 0), PebbleColor.BLACK);
		}

		BoardConfiguration testConfiguration = new BoardConfiguration(testMap);
		Gone testGame = new Gone(testConfiguration);

		// Test
		GameResults testResults = testGame.finalGameResults();
		int rounds = testResults.getRounds();
		boolean blackRemaining = testResults.isBlackPebbleRemaining();

		// Assert
		System.out.println("Actual rounds = " + rounds);
		System.out.println("Actual black pebble remaining = " + blackRemaining);

		assertEquals(9, rounds);
		assertFalse(blackRemaining);
	}

}
