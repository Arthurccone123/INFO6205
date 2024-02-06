package edu.neu.coe.info6205.sort.elementary;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        int[] ns = {200, 400, 800, 1600, 3200}; 

        for (int n : ns) {
            CompareTest.benchmarkSort("Insertion Sort with n=" + n, array -> {
                InsertionSort<Integer> sorter = new InsertionSort<>();
                sorter.sort(array, 0, array.length);
                return array;
            }, n);
            System.out.println(" ");
        }
    }
}