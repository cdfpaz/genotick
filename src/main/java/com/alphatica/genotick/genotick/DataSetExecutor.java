package com.alphatica.genotick.genotick;

import com.alphatica.genotick.population.Program;
import com.alphatica.genotick.population.ProgramExecutor;

public interface DataSetExecutor {
    ProgramResult execute(ProgramData programData, Program program);

    void setExecutor(ProgramExecutor e);
}
