package com.alphatica.genotick.breeder;

import com.alphatica.genotick.mutator.Mutator;

public class ProgramBreederFactory {
    public static ProgramBreeder getDefaultBreeder(BreederSettings breederSettings, Mutator mutator) {
        ProgramBreeder breeder = SimpleBreeder.getInstance();
        breeder.setSettings(breederSettings,mutator);
        return breeder;
    }
}
