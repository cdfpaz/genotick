package com.alphatica.genotick.breeder;

import com.alphatica.genotick.mutator.Mutator;
import com.alphatica.genotick.population.Population;

public interface ProgramBreeder {

    void breedPopulation(Population population);

    void setSettings(BreederSettings breederSettings, Mutator mutator);

}
