package gone;

import org.junit.Test;


import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CoordinateTest {

    private static final int testX = 0;
    private static final int testY = 0;
    private static final Coordinate testCoordinate = new Coordinate(testX, testY);

    @Test
    public void coordinateDoesNotEqualADifferentObject(){
        Object differentObject = new Object();

        assertFalse(testCoordinate.equals(differentObject));
    }

    @Test
    public void twoCoordinatesWithSameXDifferentYAreNotEqual(){
        Coordinate differentY = new Coordinate(testX, testY + 1);

        assertFalse(testCoordinate.equals(differentY));
    }

    @Test
    public void twoCoordinatesWithSameYDifferentXAreNotEqual(){
        Coordinate differentX = new Coordinate(testX + 1, testY);

        assertFalse(testCoordinate.equals(differentX));
    }

    @Test
    public void twoCoordinatesWithSameXAndYAreEqual(){
        Coordinate sameXandY = new Coordinate(testX, testY);

        assertTrue(testCoordinate.equals(sameXandY));
    }

    @Test
    public void testAdjacentCoordinates(){

        List<Coordinate> expectedAdjacents = Arrays.asList(new Coordinate(testX - 1, testY),
                                                           new Coordinate(testX + 1, testY),
                                                           new Coordinate(testX, testY - 1),
                                                           new Coordinate(testX, testY + 1));

        for(Coordinate actualAdjacent : testCoordinate.getAdjacentCoordinates()){
            assertTrue(expectedAdjacents.contains(actualAdjacent));
        }
    }

}
