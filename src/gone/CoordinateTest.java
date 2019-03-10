package gone;

import org.junit.Test;


import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {

    @Test
    public void testEquals(){
        Coordinate coordinate = new Coordinate(5, 5);
        Coordinate coordinate2 = new Coordinate(5, 5);
        assertTrue(coordinate.equals(coordinate2));
    }

    @Test
    public void testAdjacentCoordinates(){
        Coordinate center = new Coordinate(1, 1);

        Coordinate expectedLeft = new Coordinate(0, 1);
        Coordinate expectedDown = new Coordinate(1, 0);
        Coordinate expectedRight = new Coordinate(2, 1);
        Coordinate expectedUp = new Coordinate(1, 2);

        List<Coordinate> expectedCoords = new ArrayList<>();
        expectedCoords.add(expectedLeft);
        expectedCoords.add(expectedRight);
        expectedCoords.add(expectedUp);
        expectedCoords.add(expectedDown);

        Set<Coordinate> actualCoords = center.getAdjacentCoordinates();

        assertEquals(expectedCoords.size(), actualCoords.size());

        for(Coordinate coord : actualCoords){
            assertTrue(expectedCoords.contains(coord));
        }

    }

}
