package com.alphatica.genotick.ui;

public class UserInputOutputFactory {
    public static final String INPUT_STRING = "input";

    public static UserInput getUserInput(Parameters parameters) {
        String input = parameters.getValue(INPUT_STRING);
        parameters.removeKey(INPUT_STRING);
        if(input == null)
            return new ConsoleInput();
        switch(input) {
            case "default": return new DefaultInputs();
            case "random": return new RandomParametersInput();
            case "console": return new ConsoleInput();
        }
        return null;
    }


    public static UserOutput getUserOutput() {
        return new ConsoleOutput();
    }
}
