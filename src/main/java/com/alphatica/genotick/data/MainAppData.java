package com.alphatica.genotick.data;

import com.alphatica.genotick.genotick.ProgramData;
import com.alphatica.genotick.genotick.TimePoint;

import java.util.ArrayList;
import java.util.List;

public class MainAppData {
    private final List<DataSet> sets;

    public MainAppData() {
        sets = new ArrayList<>();
    }

    public void addDataSet(DataSet set) {
        sets.add(set);
    }

    public List<ProgramData> prepareProgramDataList(TimePoint timePoint) {
        List<ProgramData> list = new ArrayList<>();
        for (DataSet set : sets) {
            ProgramData programData = set.getProgramData(timePoint);
            if (programData.isEmpty())
                continue;
            list.add(programData);
        }
        return list;
    }

    public Double getActualChange(DataSetName name, TimePoint timePoint) {
        for(DataSet set: sets) {
            if(!set.getName().equals(name))
                continue;
            return set.calculateFutureChange(timePoint);
        }
        return Double.NaN;
    }

    public DataSet[] listSets() {
        return sets.toArray(new DataSet[sets.size()]);
    }
}
