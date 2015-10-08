package com.alphatica.genotick.genotick;

public class TimePoint implements Comparable<TimePoint> {


    private long value;
    public TimePoint(long i) {
        this.value = i;
    }

    public TimePoint(TimePoint startTimePoint) {
        this(startTimePoint.value);
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    @Override
    public int compareTo(@SuppressWarnings("NullableProblems") TimePoint timePoint) {
        return (int)(this.value - timePoint.value);
    }

    public void increment() {
        value++;
    }

    public long getValue() {
        return value;
    }
}
