package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StreamsPerformance {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        List<Integer> doubled = new ArrayList<>();

        /**
         * Imperative
         */
        for (int i = 0; i < numbers.size(); i++) {
            if(numbers.get(i) %2 == 0){
                doubled.add(numbers.get(i)*2);
            }
        }
        System.out.println(doubled);
        doubled.clear();
        // Or
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                doubled.add(number * 2);
            }
        }
        System.out.println(doubled);
        doubled.clear();

        /**
         * Declarative
         */
        System.out.println(
            numbers.stream()
                    .filter(e -> e%2 == 0)
                    .map(e -> e*2)
                    .collect(toList())
        );


        /**
         * Performance comparison
         */
        List<Integer> test = new ArrayList<>(numbers);
        for (int i = 0; i < 100000; i++) {
            test.add(i+11);
        }

        // STREAM
        long startTime0 = System.currentTimeMillis();
        System.out.println(
                test.stream()
                        .filter(e -> e%2 == 0)
                        .map(e -> e*2)
                        .collect(toList())
        );
        long endTime0 = System.currentTimeMillis();
        System.out.println("Time taken: "+ (endTime0 - startTime0)
                + " milli seconds" ); // 35ms

        // PARALLEL STREAM
        long startTime1 = System.currentTimeMillis();
        System.out.println(
                test.parallelStream()
                        .filter(e -> e%2 == 0)
                        .map(e -> e*2)
                        .collect(toList())
        );
        long endTime1 = System.currentTimeMillis();
        System.out.println("Time taken: "+ (endTime1 - startTime1)
                + " milli seconds" ); // 29ms

        // IMPERATIVE
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < test.size(); i++) {
            if(test.get(i) %2 == 0){
                doubled.add(test.get(i)*2);
            }
        }
        System.out.println(doubled);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Time taken: "+ (endTime2 - startTime2)
                + " milli seconds" ); // 26ms
    }
}
