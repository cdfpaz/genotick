package com.alphatica.genotick.genotick;

import com.alphatica.genotick.population.Population;

import java.util.List;

public interface TimePointExecutor {
    TimePointResult execute(TimePoint timePoint, List<ProgramData> programDataList);

    void setSettings(Population population, DataSetExecutor dataSetExecutor);

    void savePopulation(@SuppressWarnings("SameParameterValue") String savedPopulation);
}
