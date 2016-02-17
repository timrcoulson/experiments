package com.timrcoulson;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running test...");

        Random rand = new Random();

        // Running once to get it in cache.
        LinkedList<IntervalType> it = new LinkedList<>();
        it.add(new IntervalType(rand.nextFloat(), rand.nextInt(100)));
        MinCostStrategy.getOptimalIntervals(it, 0, 1000);

        System.out.println("Varying slot number");
        Integer limit = 1000000;
        Integer i = 1;
        while(i < limit) {
            Integer j = 0;
                System.out.print(i + ",");
            it = new LinkedList<>();
            while(j < i) {
                it.add(new IntervalType(rand.nextFloat(), rand.nextInt(100)));
                j++;
            }
            long start = System.nanoTime();
            MinCostStrategy.getOptimalIntervals(it, 0, 1000);
            long stop = System.nanoTime();
            System.out.print(stop - start + ",\n");
            i = 2 * i;
        }

        System.out.println("Varying length...");
        limit = 100000;
        i = 64;
        int j = 0;
        it = new LinkedList<>();
        while(j < 100) {
            it.add(new IntervalType(rand.nextFloat(), rand.nextInt(100)));
            j++;
        }

        while(i < limit) {
            System.out.print(i + ",");
            long start = System.nanoTime();
            MinCostStrategy.getOptimalIntervals(it, 0, i);
            long stop = System.nanoTime();
            System.out.print(stop - start + ",\n");
            i = 2 * i;
        }
    }
}
