package com.alphatica.genotick.genotick;

import com.alphatica.genotick.population.Population;

class TimePointExecutorFactory {
    public static TimePointExecutor getDefaultExecutor(Population population, DataSetExecutor dataSetExecutor) {
        TimePointExecutor executor = new SimpleTimePointExecutor();
        executor.setSettings(population,dataSetExecutor);
        return executor;
    }


}
