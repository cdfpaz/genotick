package com.alphatica.genotick.genotick;

import com.alphatica.genotick.population.Population;
import com.alphatica.genotick.population.ProgramName;

import java.util.HashMap;
import java.util.List;

public interface TimePointExecutor {
    TimePointResult execute(TimePoint timePoint, List<ProgramData> programDataList);

    void setSettings(Population population, DataSetExecutor dataSetExecutor);

    void savePopulation(String savedPopulation);
}
