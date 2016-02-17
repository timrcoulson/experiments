package com.timrcoulson;

/**
 * Created by timcoulson on 17/02/16.
 */
public class Interval {
    private final Float density;
    private final Integer end;
    private final Float cost;
    private final Integer length;
    private final Integer start;

    public Interval(Integer start, Integer length, Float cost) {
        this.length = length;
        this.start = start;
        this.end = start + length;
        this.cost = cost;
        this.density = (cost / length);
    }

    public Integer getLength() {
        return length;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Float getCost() {
        return cost;
    }

    public Float getCostDensity() {
        return density;
    }
}