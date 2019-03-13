package gone;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardConfigurationTest {

    /* METHODS TESTED SO FAR:
        isValidBoardConfiguration
        blackRemains
        whitePebbleCoordinate

       @TODO
       METHODS TO BE TESTED:
       BoardConfiguration
       ReplacePebblesOnce
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
        // Set up
        List<Coordinate> invalidCoordinates = Arrays.asList(
                new Coordinate(invalidValue, invalidValue), // Invalid X, Invalid Y, should return False
                new Coordinate(validValue, invalidValue),   // Valid X, Invalid Y, should return False
                new Coordinate(invalidValue, validValue));   // Invalid X, Valid Y, should return False

        for(Coordinate testCoordinate : invalidCoordinates){
            // Act
            testMap.put(testCoordinate, PebbleColor.BLACK);

            // Assert
            assertFalse(testHook.isValidBoardConfiguration(testMap));

            // Clean up
            testMap.remove(testCoordinate);
        }
    }

    @Test
    public void isValidBoardConfigurationReturnsTrueOnValidCoordinate(){
        // Set up
        testMap.put(validCoordinate, PebbleColor.BLACK);

        // Act + Assert
        assertTrue(testHook.isValidBoardConfiguration(testMap));

        // Clean up
        testMap.remove(validCoordinate);
    }

    @Test
    public void blackRemainsReturnsFalseWhenNoBlackCoordinatesInBoardConfiguration(){
        // Set up
        testMap.put(validCoordinate, PebbleColor.WHITE);
        BoardConfiguration noBlackCoordinatesConfiguration = new BoardConfiguration(testMap);

        // Act + Assert
        assertFalse(noBlackCoordinatesConfiguration.blackRemains());

        // Clean up
        testMap.remove(validCoordinate);
    }

    @Test
    public void blackRemainsReturnsTrueWhenBlackCoordinatesInBoardConfiguration(){
        // Set up
        testMap.put(validCoordinate, PebbleColor.BLACK);
        BoardConfiguration blackCoordinatesConfiguration = new BoardConfiguration(testMap);

        // Act + Assert
        assertTrue(blackCoordinatesConfiguration.blackRemains());

        // Clean up
        testMap.remove(validCoordinate);
    }

    @Test
    public void whitePebbleCoordinatesReturnsASetOfOnlyWhiteCoordinates(){
        // Set up
        Coordinate whiteCoordinate = validCoordinate;
        testMap.put(whiteCoordinate, PebbleColor.WHITE);
        Coordinate blackCoordinate = new Coordinate(validValue + 1, validValue);
        testMap.put(blackCoordinate, PebbleColor.BLACK);

        // Act
        Set<Coordinate> testWhitePebbleCoordinates = testHook.whitePebbleCoordinates(testMap);

        // Assert
        assertTrue(testWhitePebbleCoordinates.contains(whiteCoordinate));
        assertFalse(testWhitePebbleCoordinates.contains(blackCoordinate));

        // Clean up
        testMap.remove(whiteCoordinate);
        testMap.remove(blackCoordinate);
    }
}
