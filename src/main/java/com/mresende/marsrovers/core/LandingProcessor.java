package com.mresende.marsrovers.core;


import com.mresende.marsrovers.exceptions.InvalidOrientationException;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mresende on 07/01/17.
 */
class LandingProcessor {

    private static Integer[] UPPER_RIGHT_LIMIT;

    private Position _position;

    private File _inputFile;

    private static final Logger LOG = Logger.getLogger(LandingProcessor.class.getName());

    LandingProcessor(File file, Boolean debug) {
        UPPER_RIGHT_LIMIT = new Integer[2];
        _inputFile = file;
        this.setLogLevel(debug);

        try {
            this.run();
        } catch (FileNotFoundException e) {
            LOG.severe(e.getMessage());
            e.printStackTrace();
        }
    }

    private void run() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(_inputFile));

        try {
            // reading first line of the file (upper right limits)
            String line = bufferedReader.readLine();
            this.setUpperRightLimit(line);
            boolean isPositionLine = true;

            while ((line = bufferedReader.readLine()) != null) {

                if (isPositionLine) {
                    _position = new Position(line);
                } else {
                    _position.moveMarsRover(line, LandingProcessor.UPPER_RIGHT_LIMIT);
                    System.out.println(_position.toString());
                }

                isPositionLine = !isPositionLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidOrientationException e) {
            LOG.severe(e.getMessage());
            e.printStackTrace();
        }
    }

    private void setLogLevel(Boolean debug) {
        if (!debug) {
            LOG.setLevel(Level.OFF);
        }
    }

    private void setUpperRightLimit(String limitString) {
        String[] limits = limitString.split(" ");
        Integer xLimit = Integer.parseInt(limits[0]);
        Integer yLimit = Integer.parseInt(limits[1]);

        if (this.isLimitValid(xLimit, yLimit)) {
            LandingProcessor.UPPER_RIGHT_LIMIT[0] = xLimit;
            LandingProcessor.UPPER_RIGHT_LIMIT[1] = yLimit;
        }
    }

    private Boolean isLimitValid(Integer xlimit, Integer ylimit) {
        return xlimit > 0 && ylimit > 0;
    }


}
