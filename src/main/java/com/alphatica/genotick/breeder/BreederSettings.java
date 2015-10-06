package com.alphatica.genotick.breeder;

public class BreederSettings {
    public final long outcomesBetweenBreeding;
    public final double inheritedWeightPercent;
    public final long minimumOutcomesToAllowBreeding;
    public final double randomPrograms;
    public final int dataMaximumOffset;

    public BreederSettings(long timeBetweenChildren, double inheritedWeightPercent, long minimumParentAge, double randomPrograms, int dataMaximumOffset) {
        this.outcomesBetweenBreeding = timeBetweenChildren;
        this.inheritedWeightPercent = inheritedWeightPercent;
        this.minimumOutcomesToAllowBreeding = minimumParentAge;
        this.randomPrograms = randomPrograms;
        this.dataMaximumOffset = dataMaximumOffset;
    }
}
