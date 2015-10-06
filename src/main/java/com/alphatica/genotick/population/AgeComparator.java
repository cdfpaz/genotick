package com.alphatica.genotick.population;

import java.util.Comparator;

public class AgeComparator implements Comparator<ProgramInfo> {

    @Override
    public int compare(ProgramInfo programInfo1, ProgramInfo programInfo2) {
        return programInfo1.getTotalOutcomes() - programInfo2.getTotalOutcomes();
    }
}
