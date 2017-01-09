package com.mresende.marsrovers.core;


import com.mresende.marsrovers.exceptions.InvalidOrientationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by mresende on 08/01/17.
 */
class OrientationTest {

    @Test
    public void InvalidOrientation() {
        try {
            new Orientation('I');
        } catch (InvalidOrientationException e) {
            assertEquals("Invalid orientation given! I", e.getMessage());
        }
    }

    @Test
    void turnRightNorth() {
        try {
            Orientation o = new Orientation('N');
            o.turnRight();
            Character actual = o.getCurrentOrientation();
            Character expected = 'E';

            assertEquals(actual, expected);
        } catch (InvalidOrientationException e) {
            e.printStackTrace();
        }

    }

    @Test
    void turnRightWest() {
        try {
            Orientation o = new Orientation('W');
            o.turnRight();
            Character actual = o.getCurrentOrientation();
            Character expected = 'N';

            assertEquals(actual, expected);
        } catch (InvalidOrientationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void turnLeftSouth() {
        try {
            Orientation o = new Orientation('S');
            o.turnLeft();
            Character actual = o.getCurrentOrientation();
            Character expected = 'E';

            assertEquals(actual, expected);
        } catch (InvalidOrientationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void turnLeftNorth() {
        try {
            Orientation o = new Orientation('N');
            o.turnLeft();
            Character actual = o.getCurrentOrientation();
            Character expected = 'W';

            assertEquals(actual, expected);
        } catch (InvalidOrientationException e) {
            e.printStackTrace();
        }
    }

}