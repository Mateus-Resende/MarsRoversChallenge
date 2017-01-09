package com.mresende.marsrovers.core;


import com.mresende.marsrovers.exceptions.InvalidLimitsException;
import com.mresende.marsrovers.exceptions.InvalidMoveException;
import com.mresende.marsrovers.exceptions.InvalidOrientationException;
import com.mresende.marsrovers.exceptions.InvalidPositionException;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mresende on 07/01/17.
 *
 * Classe para o processamento do arquivo que contém as informações sobre o pouso dos Mars Rovers.
 */
class LandingProcessor {

    private static Integer[] UPPER_RIGHT_LIMIT;
    private Position _position;
    private File _inputFile;
    private static final Logger LOG = Logger.getLogger(LandingProcessor.class.getName());

    LandingProcessor(File file, Boolean debug) throws InvalidLimitsException {
        UPPER_RIGHT_LIMIT = new Integer[2];
        _inputFile = file;
        this.setLogLevel(debug);

        this.run();
    }

    /**
     * Método responsável pela leitura do arquivo e
     * gerenciamento se a linha que está sendo lida é de posição ou de movimentação
     * @throws InvalidLimitsException no caso dos limites da área de pouso não serem satisfatórios
     *
     */
    private void run() throws InvalidLimitsException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(_inputFile));
            // reading first line of the file (upper right limits)
            String line = bufferedReader.readLine();
            this.setUpperRightLimit(line);
            boolean isPositionLine = true;

            while ((line = bufferedReader.readLine()) != null) {
                // should only consider lines with characters
                if (line.isEmpty()) {
                    continue;
                }

                if (isPositionLine) {
                    // sets the position of the current mars rover
                    _position = new Position(line, LandingProcessor.UPPER_RIGHT_LIMIT);
                } else {
                    // move the current mars rover
                    _position.moveMarsRover(line, LandingProcessor.UPPER_RIGHT_LIMIT);
                    System.out.println(_position.toString());
                }

                // position and movement lines should be interleaved
                isPositionLine = !isPositionLine;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidOrientationException | InvalidMoveException | InvalidPositionException e) {
            LOG.severe(e.getMessage());
        }
    }

    /**
     * Desabilita os prints de logs
     * @param debug o parâmetro booleano dado no momento de execução do programa
     */
    private void setLogLevel(Boolean debug) {
        if (!debug) {
            LOG.setLevel(Level.SEVERE);
        }
    }

    /**
     * Configura o limit direito superior da área de pouso
     * @param limitString a string que foi recebida do arquivo
     * @throws InvalidLimitsException no caso dos limites serem menores que zero
     */
    private void setUpperRightLimit(String limitString) throws InvalidLimitsException {
        String[] limits = limitString.split(" ");
        Integer xLimit = Integer.parseInt(limits[0]);
        Integer yLimit = Integer.parseInt(limits[1]);

        if (this.isLimitValid(xLimit, yLimit)) {
            LandingProcessor.UPPER_RIGHT_LIMIT[0] = xLimit;
            LandingProcessor.UPPER_RIGHT_LIMIT[1] = yLimit;
        } else {
            throw new InvalidLimitsException("Limits of the landing area should be bigger than 0");
        }
    }

    /**
     * Validação para os limites dados
     * @param xlimit
     * @param ylimit
     * @return verdadeiro se os limites forem maiores que zero
     */
    private Boolean isLimitValid(Integer xlimit, Integer ylimit) {
        return xlimit > 0 && ylimit > 0;
    }


}
