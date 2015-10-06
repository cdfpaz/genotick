package com.alphatica.genotick.population;

import com.alphatica.genotick.processor.SimpleProcessor;

public class ProgramExecutorFactory {
    public static ProgramExecutor getDefaultProgramExecutor(ProgramExecutorSettings settings) {
        ProgramExecutor executor = SimpleProcessor.createProcessor();
        executor.setSettings(settings);
        return executor;
    }
}
