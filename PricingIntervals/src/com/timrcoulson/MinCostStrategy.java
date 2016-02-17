package com.timrcoulson;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MinCostStrategy {
    private static List<Interval> buildIntervals(LinkedList<Interval> intervals, IntervalType intervalType, LinkedList<IntervalType> intervalTypes, Integer start, Integer end) {
        Interval interval = intervalType.buildInterval(start);

        if (interval.getEnd() >= end) {
            if (!intervalTypes.isEmpty() && compareSlotTypes(intervalType, intervalTypes.getFirst(), interval.getStart(), end)) {
                intervalType = intervalTypes.pop();
                return buildIntervals(intervals, intervalType, intervalTypes, intervals.getLast().getEnd(), end);
            }
            intervals.add(interval);
            return intervals;
        }

        intervals.add(interval);
        return buildIntervals(intervals, intervalType, intervalTypes, intervals.getLast().getEnd(), end);
    }

    private static boolean compareSlotTypes(IntervalType a, IntervalType b, Integer start, Integer end) {
        Float effectiveCostDensityA = effectiveCostDensity(a.buildInterval(start), end);
        Float effectiveCostDensityB = effectiveCostDensity(b.buildInterval(start), end);

        return effectiveCostDensityB < effectiveCostDensityA;
    }

    private static Float effectiveCostDensity(Interval i, Integer end) {
        Integer overlap = 0;
        if (i.getEnd() > end) {
            overlap = i.getEnd() - end;
        }
        return i.getCost() / (i.getLength() - overlap);
    }

    public static List<Interval> getOptimalIntervals(LinkedList<IntervalType> intervalTypes, Integer start, Integer end) {
        Collections.sort(intervalTypes);
        LinkedList<Interval> intervals = new LinkedList<>();
        IntervalType firstIntervalType = intervalTypes.pop();
        return buildIntervals(intervals, firstIntervalType, intervalTypes, start, end);
    }
}