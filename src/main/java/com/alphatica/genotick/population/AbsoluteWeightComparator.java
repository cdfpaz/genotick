package com.alphatica.genotick.population;

import java.io.Serializable;
import java.util.Comparator;

class AbsoluteWeightComparator implements Comparator<ProgramInfo>, Serializable {

    @SuppressWarnings("unused")
    private static final long serialVersionUID = 4466317313399016583L;

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
