package com.timrcoulson;

/**
 * Created by timcoulson on 17/02/16.
 */
public class IntervalType implements Comparable<IntervalType> {
    private Float price;
    private Integer length;

    public IntervalType(Float price, Integer length) {
        this.price = price;
        this.length = length;
    }

    public Interval buildInterval(Integer start) {
        return new Interval(start, length, price);
    }

    @Override
    public int compareTo(IntervalType it) {
        Interval thisInterval = buildInterval(0);
        Interval anotherInterval = it.buildInterval(0);

        if (thisInterval.getCostDensity() > anotherInterval.getCostDensity()) {
            return 1;
        }
        return -1;
    }
}
