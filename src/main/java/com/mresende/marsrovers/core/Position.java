package com.mresende.marsrovers.core;


import com.mresende.marsrovers.exceptions.InvalidMoveException;
import com.mresende.marsrovers.exceptions.InvalidOrientationException;
import com.mresende.marsrovers.exceptions.InvalidPositionException;

/**
 * Created by mresende on 08/01/17.
 *
 * Classe responsável pelo gerenciamento da posição geral do Mars Rover durante o pouso
 */
class Position {

    private Integer _xPos;
    private Integer _yPos;
    private Orientation _orientation;

    /**
     * O construtor precisa de uma posição e dos limites da área de pouso, para validação.
     * @param line as coordenadas iniciais do Mars Rover
     * @param upperRightLimits limite superior direito da área de pouso
     * @throws InvalidOrientationException Caso a orientação fornecida para a posição inicial seja inválida
     * @throws InvalidPositionException Caso as coordenadas sejam inválidas
     */
    Position(String line, Integer[] upperRightLimits) throws InvalidOrientationException, InvalidPositionException {
        String[] positionArray = line.split(" ");
        Integer x = Integer.parseInt(positionArray[0]);
        Integer y = Integer.parseInt(positionArray[1]);

        if (this.isPositionValid(x, y, upperRightLimits[0], upperRightLimits[1])) {
            _xPos = x;
            _yPos = y;
            _orientation = new Orientation(positionArray[2].charAt(0));
        } else {
            throw new InvalidPositionException("The initial position of Mars Rover should be inside the landing area");
        }

    }

    /**
     * Validação das coordenadas em relação à área de pouso
     * @param x coordenada
     * @param y coordenada
     * @param xLimit limite
     * @param yLimit limite
     * @return verdadeiro caso as coordenadas estejam dentro dos limites
     */
    private boolean isPositionValid(Integer x, Integer y, Integer xLimit, Integer yLimit) {
        return x <= xLimit && x >= 0 && y <= yLimit && y >= 0;
    }

    /**
     * Escrita da posição do Mars Rover
     * @return as coordenadas e a orientação concatenados
     */
    @Override
    public String toString() {
        return _xPos.toString() + " " + _yPos.toString() + " " + _orientation.getCurrentOrientation();
    }

    /**
     * Lógica para movimentação do Mars Rover utilizando a linha de comandos dada
     * @param line string com comandos
     * @param upperRightLimits limites da área de pouso
     * @throws InvalidMoveException caso o comando não seja nenhum dos especificados
     */
    void moveMarsRover(String line, Integer[] upperRightLimits) throws InvalidMoveException {
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

                default:
                    throw new InvalidMoveException("This is not a valid movement");
            }
        }
    }

    /**
     * Gerenciamento da movimentação
     * Caso a nave chegue na borda, o movimento não ocorrerá, ela ficará na mesma posição
     * @param upperRightLimits limites da área de pouso
     */
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

}
