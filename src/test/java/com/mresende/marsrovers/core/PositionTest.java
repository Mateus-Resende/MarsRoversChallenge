package com.mresende.marsrovers.core;

import com.mresende.marsrovers.exceptions.InvalidMoveException;
import com.mresende.marsrovers.exceptions.InvalidOrientationException;
import com.mresende.marsrovers.exceptions.InvalidPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mresende on 08/01/17.
 */
class PositionTest {

    @Test
    void PositionInvalidCoordinates() {
        Integer[] upperRightLimits = {3 , 3};
        try {
            new Position("5 5 N", upperRightLimits);

        } catch (InvalidOrientationException e) {
            fail("Position should be verified first");
            e.printStackTrace();

        } catch (InvalidPositionException e) {
            String expected = "The initial position of Mars Rover should be inside the landing area";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    void PositionInvalidOrientation() {
        Integer[] upperRightLimits = {4, 4};

        try {
            new Position("3 2 Q", upperRightLimits);

        } catch (InvalidOrientationException e) {
            String expected = "Invalid orientation given! Q";
            assertEquals(expected, e.getMessage());

        } catch (InvalidPositionException e) {
            fail("Position should be fine");
            e.printStackTrace();
        }
    }

    @Test
    void moveMarsRoverInvalidMove() {
        Integer[] upperRightLimits = {5, 5};

        try {
            Position p = new Position("3 3 S", upperRightLimits);
            String moves = "LMLLMRMRMT";
            p.moveMarsRover(moves, upperRightLimits);

        } catch (InvalidPositionException | InvalidOrientationException e) {
            fail("The initialization should be fine");
            e.printStackTrace();
        } catch (InvalidMoveException e) {
            String actual = e.getMessage();
            String expected = "This is not a valid movement";

            assertEquals(actual, expected);
        }
    }

    @Test
    void MoveMarsRoverValidMove() {
        Integer[] upperRightLimits = {4, 4};

        try {
            Position p = new Position("2 3 W", upperRightLimits);
            String moves = "RRMMMLMLLMM";
            p.moveMarsRover(moves, upperRightLimits);
            String expected = "4 2 S";
            String actual = p.toString();

            assertEquals(actual, expected);
        } catch (InvalidOrientationException | InvalidPositionException | InvalidMoveException e) {
            e.printStackTrace();
        }
    }

}