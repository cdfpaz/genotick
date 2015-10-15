package com.alphatica.genotick.ui;

import com.alphatica.genotick.genotick.Debug;

public class UserInputOutputFactory {
    private static final String INPUT_STRING = "input";

    public static UserInput getUserInput(Parameters parameters) {
        String input = parameters.getValue(INPUT_STRING);
        parameters.removeKey(INPUT_STRING);
        if(input == null)
            return new DefaultInputs();
        switch(input) {
            case "default": return new DefaultInputs();
            case "random": return new RandomParametersInput();
            case "console": return tryConsoleInput();
        }
        return null;
    }

    private static UserInput tryConsoleInput() {
        UserInput input;
        try {
            input = new ConsoleInput();
            return input;
        } catch (RuntimeException ex) {
            Debug.d("Unable to get Console Input. Resorting to Default Input");
            return new DefaultInputs();
        }
    }


    public static UserOutput getUserOutput() {
        return new ConsoleOutput();
    }
}
