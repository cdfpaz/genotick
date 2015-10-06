package com.alphatica.genotick.population;

import java.util.Comparator;

public class AbsoluteWeightComparator implements Comparator<ProgramInfo> {

    @Override
    public int compare(ProgramInfo programInfo1, ProgramInfo programInfo2) {
        double diff = Math.abs(programInfo1.getWeight()) - Math.abs(programInfo2.getWeight());
        if (diff > 0)
            return 1;
        else if (diff < 0)
            return -1;
        else
            return 0;
    }
}
