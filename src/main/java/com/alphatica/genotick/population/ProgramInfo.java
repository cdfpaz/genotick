package com.alphatica.genotick.population;

import java.text.DecimalFormat;
import java.util.Comparator;

public class ProgramInfo {
    public static final Comparator<ProgramInfo> comparatorByAge = new AgeComparator();
    public static final Comparator<ProgramInfo> comparatorByAbsoluteWeight = new AbsoluteWeightComparator();
    private final ProgramName name;
    private final double weight;
    private final long lastChildOutcomes;
    private final long length;
    private final int totalPredictions;
    private DecimalFormat format = new DecimalFormat("0.00");
    private int totalOutcomes;
    private int bias;

    @Override
    public String toString() {
        return name.toString() + ": Outcomes: " + String.valueOf(totalPredictions) + " weight: " + format.format(weight) +
                " bias: " + String.valueOf(bias) + " length: " + String.valueOf(length);
    }

    public ProgramName getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getTotalPredictions() {
        return totalPredictions;
    }

    public ProgramInfo(Program program) {
        name = new ProgramName(program.getName().getName());
        weight = program.getWeight();
        lastChildOutcomes = program.getOutcomesAtLastChild();
        length = program.getLength();
        totalPredictions = program.getTotalPredictions();
        totalOutcomes = program.getTotalOutcomes();
        bias = program.getBias();
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean canBeParent(long minimumParentAge, long timeBetweenChildren) {
        if(totalPredictions < minimumParentAge)
            return false;
        long outcomesSinceLastChild = totalPredictions - lastChildOutcomes;
        return outcomesSinceLastChild >= timeBetweenChildren;
    }

    public int getTotalOutcomes() {
        return totalOutcomes;
    }

    public int getBias() {
        return bias;
    }
}
