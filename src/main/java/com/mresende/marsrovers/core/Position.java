package com.mresende.marsrovers.core;


import com.mresende.marsrovers.exceptions.InvalidOrientationException;

/**
 * Created by mresende on 08/01/17.
 */
public class Position {

    private Integer _xPos;
    private Integer _yPos;
    private Orientation _orientation;

    Position(String line) throws InvalidOrientationException {
        String[] positionArray = line.split(" ");
        _xPos = Integer.parseInt(positionArray[0]);
        _yPos = Integer.parseInt(positionArray[1]);

        _orientation = new Orientation(positionArray[2].charAt(0));
    }

    @Override
    public String toString() {
        return _xPos.toString() + " " + _yPos.toString() + " " + _orientation.getCurrentOrientation();
    }

    void moveMarsRover(String line, Integer[] upperRightLimits) {
        for (int i = 0; i < line.length(); i++) {
            Character instruction = line.charAt(i);

            switch (instruction) {
                case 'L':
                    _orientation.turnLeft();
                    break;

                case 'R':
                    _orientation.turnRight();
                    break;

                case 'M':
                    this.move(upperRightLimits);
                    break;
            }
        }
    }

    private void move(Integer[] upperRightLimits) {
        switch(_orientation.getCurrentOrientation()) {
            case 'N':
                _yPos += this.moveUp(upperRightLimits[1]);
                break;

            case 'E':
                _xPos += this.moveRight(upperRightLimits[0]);
                break;

            case 'S':
                _yPos -= this.moveDown();
                break;

            case 'W':
                _xPos -= this.moveLeft();
                break;
        }
    }

    private Integer moveUp(Integer upperLimits) {
        if (_yPos + 1 > upperLimits) {
            return 0;
        }
        return 1;
    }

    private Integer moveRight(Integer rightLimits) {
        if (_xPos + 1 > rightLimits) {
            return 0;
        }
        return 1;
    }

    private Integer moveDown() {
        if (_yPos - 1 < 0) {
            return 0;
        }
        return 1;
    }

    private Integer moveLeft() {
        if (_xPos - 1 < 0) {
            return 0;
        }
        return 1;
    }

    public Integer getXPos() {
        return _xPos;
    }

    public void setXPos(Integer _xPos) {
        this._xPos = _xPos;
    }

    public Integer getYPos() {
        return _yPos;
    }

    public void setYPos(Integer _yPos) {
        this._yPos = _yPos;
    }

    public Orientation getOrientation() {
        return _orientation;
    }

    public void setOrientation(Orientation orientation) {
        _orientation = orientation;
    }
}
