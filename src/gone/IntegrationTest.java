package gone.src.gone;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class IntegrationTest {
	
	@Test
	public void testExampleFromAssignment6() {
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

		// Assert
		assertEquals(3, testResults.getRounds());
		assertEquals(true, testResults.isBlackPebbleRemaining());
	}

}
