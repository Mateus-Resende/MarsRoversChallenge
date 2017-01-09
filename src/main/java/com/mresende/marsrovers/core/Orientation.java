package com.mresende.marsrovers.core;

import com.mresende.marsrovers.exceptions.InvalidOrientationException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mresende on 08/01/17.
 * Classe responsável por validar e movimentar a orientação da Mars Rover
 */
class Orientation {

    private static List<Character> _orientationList = new LinkedList<>();

    static {
        _orientationList.add('N');
        _orientationList.add('E');
        _orientationList.add('S');
        _orientationList.add('W');
    }

    private Character _currentOrientation;

    /**
     * Criação da orientação inicial
     * @param orientation o caracter contendo a orientação
     * @throws InvalidOrientationException caso seja uma orientação não especificada
     */
    Orientation(Character orientation) throws InvalidOrientationException {
        if (this.isOrientationValid(orientation)) {
            _currentOrientation = orientation;
        } else {
            throw new InvalidOrientationException("Invalid orientation given! " + orientation.toString());
        }
    }

    Character getCurrentOrientation() {
        return _currentOrientation;
    }

    /**
     * Validação da orientação
     * @param c, o caracter a ser validado
     * @return verdadeiro caso a orientação seja N, E, S, ou W.
     */
    private boolean isOrientationValid(Character c) {
        return _orientationList.contains(c);
    }

    /**
     * Lógica para virar a orientação no sentido horário
     */
    void turnRight() {
        Integer index = _orientationList.indexOf(_currentOrientation);
        if (index == (_orientationList.size() - 1)) {
            _currentOrientation = _orientationList.get(0);
        } else {
            _currentOrientation = _orientationList.get(index + 1);
        }
    }

    /**
     * Lógica para virar a orientação no sentido anti horário
     */
    void turnLeft() {
        Integer index = _orientationList.indexOf(_currentOrientation);
        if (index == 0) {
            _currentOrientation = _orientationList.get(3);
        } else {
            _currentOrientation = _orientationList.get(index - 1);
        }
    }
}
