package com.mresende.marsrovers.core;

import com.mresende.marsrovers.exceptions.InvalidOrientationException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mresende on 08/01/17.
 */
public class Orientation {

    private static List<Character> _orientationList = new LinkedList<>();

    static {
        _orientationList.add('N');
        _orientationList.add('E');
        _orientationList.add('S');
        _orientationList.add('W');
    }

    private Character _currentOrientation;

    public Orientation(Character orientation) throws InvalidOrientationException {
        if (isOrientationValid(orientation)) {
            _currentOrientation = orientation;
        } else {
            throw new InvalidOrientationException();
        }
    }

    public Character getCurrentOrientation() {
        return _currentOrientation;
    }

    private boolean isOrientationValid(Character c) {
        return _orientationList.contains(c);
    }

    void turnRight() {
        Integer index = _orientationList.indexOf(_currentOrientation);
        if (index == (_orientationList.size() - 1)) {
            _currentOrientation = _orientationList.get(0);
        } else {
            _currentOrientation = _orientationList.get(index + 1);
        }
    }

    void turnLeft() {
        Integer index = _orientationList.indexOf(_currentOrientation);
        if (index == 0) {
            _currentOrientation = _orientationList.get(3);
        } else {
            _currentOrientation = _orientationList.get(index - 1);
        }
    }
}
