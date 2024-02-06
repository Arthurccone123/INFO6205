package edu.neu.coe.info6205.sort.elementary;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;


public class CompareTest {

    private static Integer[] generateRandomArray(int n) {
        Random rnd = new Random();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(n);
        }
        return arr;
    }

    private static Integer[] generateOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static Integer[] generatePartiallyOrderedArray(int n) {
        Integer[] arr = generateOrderedArray(n);
        Random rnd = new Random();
        for (int i = 0; i < n / 10; i++) { 
            int j = rnd.nextInt(n);
            int k = rnd.nextInt(n);
            int temp = arr[j];
            arr[j] = arr[k];
            arr[k] = temp;
        }
        return arr;
    }

    private static Integer[] generateReverseOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
        return arr;
    }
    public static void benchmarkSort(String description, Function<Integer[], Integer[]> sortFunction, int n) {
        Integer[] randomArray = generateRandomArray(n);
        Integer[] orderedArray = generateOrderedArray(n);
        Integer[] partiallyOrderedArray = generatePartiallyOrderedArray(n);
        Integer[] reverseOrderedArray = generateReverseOrderedArray(n);

        benchmark(description + " - Random", sortFunction, randomArray);
        benchmark(description + " - Ordered", sortFunction, orderedArray);
        benchmark(description + " - Partially Ordered", sortFunction, partiallyOrderedArray);
        benchmark(description + " - Reverse Ordered", sortFunction, reverseOrderedArray);
    }

    public static void benchmark(String description, Function<Integer[], Integer[]> sortFunction, Integer[] array) {
        long startTime = System.currentTimeMillis();
        sortFunction.apply(array);
        long endTime = System.currentTimeMillis();
        System.out.println(description + ": " + (endTime - startTime) + " ms");
    }

}

