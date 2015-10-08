package com.alphatica.genotick.population;

import java.io.Serializable;
import java.util.Comparator;

class AgeComparator implements Comparator<ProgramInfo>, Serializable {

    @SuppressWarnings("unused")
    private static final long serialVersionUID = 8272762774160538039L;

    @Override
    public int compare(ProgramInfo programInfo1, ProgramInfo programInfo2) {
        return programInfo1.getTotalOutcomes() - programInfo2.getTotalOutcomes();
    }
}
