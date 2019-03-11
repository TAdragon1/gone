package gone;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardConfigurationTest {

    private static final int invalidValue = -1;
    private static final int validValue = 1;
    private static final BoardConfiguration.BoardConfigurationTestHook testHook = new BoardConfiguration.BoardConfigurationTestHook();

    @Test
    public void isValidBoardConfigurationReturnsFalseWhenInvalidXAndYInMap(){
        Map<Coordinate, PebbleColor> testMap = new HashMap<>();
        testMap.put(new Coordinate(invalidValue, invalidValue), PebbleColor.BLACK);

        assertFalse(testHook.isValidBoardConfiguration(testMap));
    }

    @Test
    public void isValidBoardConfigurationReturnsFalseWhenOnlyInvalidXInMap(){
        Map<Coordinate, PebbleColor> testMap = new HashMap<>();
        testMap.put(new Coordinate(invalidValue, invalidValue), PebbleColor.BLACK);

        assertFalse(testHook.isValidBoardConfiguration(testMap));
    }

    @Test
    public void isValidBoardConfigurationReturnsFalseWhenOnlyInvalidYInMap(){
        Map<Coordinate, PebbleColor> testMap = new HashMap<>();
        testMap.put(new Coordinate(validValue, invalidValue), PebbleColor.BLACK);
        
        assertFalse(testHook.isValidBoardConfiguration(testMap));
    }

    @Test
    public void isValidBoardConfigurationReturnsTrueWhenOnlyValidCoordinatesInMap(){
        Map<Coordinate, PebbleColor> testMap = new HashMap<>();
        testMap.put(new Coordinate(validValue, validValue), PebbleColor.BLACK);

        assertTrue(testHook.isValidBoardConfiguration(testMap));
    }
}
