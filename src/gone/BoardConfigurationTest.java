package gone;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;

public class BoardConfigurationTest {

    private static final int invalidValue = -1;
    private static final BoardConfiguration.BoardConfigurationTestHook testHook = new BoardConfiguration()

    @Test
    public void isValidBoardConfigurationReturnsFalseWhenInvalidCoordinateInMap(){
        Map<Coordinate, PebbleColor> testMap = new HashMap<>();
        testMap.put(new Coordinate(invalidValue, invalidValue), PebbleColor.BLACK);

        assertFalse(testHook.isValidBoardConfiguration(testMap));
    }

//    @Test
//    public void testInvalidBoardConfiguration(){
//        Map<Coordinate, PebbleColor> testMap = new HashMap<>();
//        testMap.put(new Coordinate(0, 0), PebbleColor.BLACK);
//        testMap.put(new Coordinate(1, -3), PebbleColor.WHITE);
//        testMap.put(new Coordinate(2, 0), PebbleColor.WHITE);
//        testMap.put(new Coordinate(-3, 4), PebbleColor.BLACK);
//
//        boolean validBoard = true;
//        try{
//            BoardConfiguration testConfiguration = new BoardConfiguration(testMap);
//        }
//        catch(IllegalArgumentException e){
//            System.out.println(e);
//            validBoard = false;
//        }
//        assertFalse(validBoard);
//    }

}
