package com.mresende.marsrovers.core;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

import java.io.File;

/**
 * Created by mresende on 07/01/17.
 */
public class Main {

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
        new LandingProcessor(_file, _debug);
    }

}
