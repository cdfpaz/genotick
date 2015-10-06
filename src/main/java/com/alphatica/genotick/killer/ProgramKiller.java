package com.alphatica.genotick.killer;

import com.alphatica.genotick.population.Population;

public interface ProgramKiller {
    void killPrograms(Population population);

    void setSettings(ProgramKillerSettings killerSettings);
}
