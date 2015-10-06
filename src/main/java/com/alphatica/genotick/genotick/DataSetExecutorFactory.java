package com.alphatica.genotick.genotick;

import com.alphatica.genotick.population.ProgramExecutor;

class DataSetExecutorFactory {
    public static DataSetExecutor getDefaultSetExecutor(ProgramExecutor e) {
        DataSetExecutor executor = new SimpleDataSetExecutor();
        executor.setExecutor(e);
        return executor;
    }
}
