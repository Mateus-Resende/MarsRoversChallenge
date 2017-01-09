package com.mresende.marsrovers.core;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import com.mresende.marsrovers.exceptions.InvalidLimitsException;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by mresende on 07/01/17.
 *
 * Main class that will receive the file where the commands are located and if it should run in debug mode or not (default false)
 */
public class Main {

    private static Logger LOG =java.util.logging.Logger.getLogger(Main.class.getName());

    @Parameter(names = {"-f", "--file"}, converter = FileConverter.class, description = "Location of input _file with rovers' landing commands")
    private File _file;

    @Parameter(names = {"-d", "--debug"}, description = "Debug mode")
    private boolean _debug = false;

    public static void main(String[] args) {
        Main main = new Main();
        new JCommander(main, args);
        main.start();
    }

    private void start() {
        try {
            new LandingProcessor(_file, _debug);
        } catch (InvalidLimitsException e) {
            LOG.severe(e.getMessage());
        }
    }

}
