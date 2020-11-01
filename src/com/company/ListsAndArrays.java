package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ListsAndArrays {

    static List<String> stringList = new ArrayList();
    static String[] stringArray = new String[]{"steven", "marie", "kevin", "tom", "dik", "an"};

    public static void main(String[] args) {
        /**
         * Populating a list
         */
        // Fill up list
        stringList.addAll(Arrays.asList(stringArray));
        // Or
        stringList = Arrays.stream(stringArray).collect(Collectors.toList());

        /**
         * Comparing lists and arrays, using streams
         */
        // Sort and filter List
        stringList.stream()
                .sorted()
                .filter(s -> !s.contains("e"))
                .forEach(System.out::println);
        // Sort and filter Array
        Arrays.stream(stringArray)
                .sorted()
                .filter(s -> !s.contains("e"))
                .forEach(System.out::println);

        // Add element to List and print all elements
        stringList.add("some1");
        System.out.println();
        stringList.stream()
                .map(str -> str.concat(" "))
                .forEach(System.out::print);
        // Add element to Array and print all elements
        System.out.println();
        stringArray = Arrays.copyOf(stringArray, stringArray.length+1);
        stringArray[stringArray.length-1]= "some1";
        System.out.print(String.join(" ", stringArray));
        System.out.println();

        // Reduce list to one string
        System.out.println();
        System.out.print("As a single string: " + stringList.stream()
                .map(str -> str.concat(" "))
                .reduce("", (String::concat)));
        // Reduce array to one string
        System.out.println();
        System.out.print("As a single string: " + Arrays.stream(stringArray)
                .map(str -> str.concat(" "))
                .reduce("", (String::concat)));

        /**
         * Re-using streams
         */
        // To chars, can only use streams once before they close()
        IntStream charStream = stringList.stream()
                .reduce("",String::concat)
                .chars();
        Stream<Character> characterStream = charStream.mapToObj(c-> (char)c);
        System.out.println();
        characterStream.forEach(System.out::println);

        // To re-use streams, keep the supplier
        Supplier<IntStream> supplier = () -> stringList.stream()
                .reduce("",String::concat)
                .chars();
        supplier.get()
                .forEach(System.out::println);
        supplier.get()
                .mapToObj(c->(char)c)
                .forEach(System.out::println);
    }
}
