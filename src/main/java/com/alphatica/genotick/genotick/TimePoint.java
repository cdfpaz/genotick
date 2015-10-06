package com.alphatica.genotick.genotick;


import java.io.Serializable;

public class TimePoint implements Comparable<TimePoint>, Serializable {
    public static final long serialVersionUID = -7090441293630751301L;

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
    public int compareTo(TimePoint timePoint) {
        return (int)(this.value - timePoint.value);
    }

    public void increment() {
        value++;
    }

    public long getValue() {
        return value;
    }
}
