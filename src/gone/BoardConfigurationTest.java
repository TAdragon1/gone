package gone;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardConfigurationTest {

    /* METHODS TESTED SO FAR:
        isValidBoardConfiguration
        blackRemains

       @TODO
       METHODS TO BE TESTED:
       BoardConfiguration
       ReplacePebblesOnce
       whitePebbleCoordinates
       hasUnvisitedBlackCoordinates
       updateNeighbors
     */


    private static final int invalidValue = -1;
    private static final int validValue = 1;
    private static final BoardConfiguration.BoardConfigurationTestHook testHook;
    private static final Map<Coordinate, PebbleColor> testMap;
    private static final Coordinate validCoordinate;

    static {
        testHook = new BoardConfiguration.BoardConfigurationTestHook();
        testMap = new HashMap<>();
        validCoordinate = new Coordinate(validValue, validValue);
    }

    @Test
    public void isValidBoardConfigurationReturnsFalseForEachInvalidCoordinate(){
        List<Coordinate> invalidCoordinates = Arrays.asList(
                new Coordinate(invalidValue, invalidValue), // Invalid X, Invalid Y, should return False
                new Coordinate(validValue, invalidValue),   // Valid X, Invalid Y, should return False
                new Coordinate(invalidValue, validValue));   // Invalid X, Valid Y, should return False

        for(Coordinate testCoordinate : invalidCoordinates){
            testMap.put(testCoordinate, PebbleColor.BLACK);

            assertFalse(testHook.isValidBoardConfiguration(testMap));

            testMap.remove(testCoordinate);
        }
    }

    @Test
    public void isValidBoardConfigurationReturnsTrueOnValidCoordinate(){
        testMap.put(validCoordinate, PebbleColor.BLACK);

        assertTrue(testHook.isValidBoardConfiguration(testMap));

        testMap.remove(validCoordinate);
    }

    @Test
    public void blackRemainsReturnsFalseWhenNoBlackCoordinatesInBoardConfiguration(){
        testMap.put(validCoordinate, PebbleColor.WHITE);
        BoardConfiguration noBlackCoordinatesConfiguration = new BoardConfiguration(testMap);

        assertFalse(noBlackCoordinatesConfiguration.blackRemains());

        testMap.remove(validCoordinate);
    }

    @Test
    public void blackRemainsReturnsTrueWhenBlackCoordinatesInBoardConfiguration(){
        testMap.put(validCoordinate, PebbleColor.BLACK);
        BoardConfiguration blackCoordinatesConfiguration = new BoardConfiguration(testMap);

        assertTrue(blackCoordinatesConfiguration.blackRemains());

        testMap.remove(validCoordinate);
    }
}
