package com.alphatica.genotick.genotick;

import com.alphatica.genotick.data.DataSetName;

import java.util.ArrayList;
import java.util.List;

class DataSetResult {
    private final List<ProgramResult> list;
    private final DataSetName name;

    DataSetResult(DataSetName name) {
        list = new ArrayList<>();
        this.name = name;
    }

    public void addResult(ProgramResult programResult) {
        list.add(programResult);
    }

    public Prediction getCumulativePrediction() {
        double weightUp = 0;
        double weightDown = 0;
        double weightOut = 0;
        double direction = 0;
        int countUp = 0;
        int countDown = 0;
        int countOut = 0;
        for(ProgramResult result: list) {
            if(result.getWeight().isNaN())
                continue;
            switch(result.getPrediction()) {
                case UP: weightUp+= result.getWeight(); countUp++; break;
                case DOWN: weightDown += result.getWeight(); countDown++; break;
                default: weightOut += result.getWeight(); countOut++; break;
            }
            direction = weightUp - weightDown;
        }
        Debug.d("Up:",weightUp,countUp,"Down:",weightDown,countDown,"Out:",weightOut,countOut);
        return Prediction.getPrediction(direction);
    }

    public DataSetName getName() {
        return name;
    }

    public ProgramResult[] listProgramResults() {
        return list.toArray(new ProgramResult[list.size()]);
    }
}
