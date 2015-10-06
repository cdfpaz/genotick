package com.alphatica.genotick.population;

import com.alphatica.genotick.genotick.Prediction;
import com.alphatica.genotick.genotick.ProgramData;

public interface ProgramExecutor {
    byte totalRegisters = 4;

    Prediction executeProgram(ProgramData programData, Program program);

    void setSettings(ProgramExecutorSettings settings);
}
