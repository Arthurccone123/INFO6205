package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * CONSIDER tidy it up a bit.
 */
class ParSort {

    public static int cutoff = 10000;

    public static void sort(int[] array, int from, int to) {
        if (to - from < cutoff) Arrays.sort(array, from, to);
        else {
            // FIXME next few lines should be removed from public repo.
        	int mid = from + (to - from) / 2;
            CompletableFuture<int[]> parsort1 = parsort(array, from, mid);
            CompletableFuture<int[]> parsort2 = parsort(array, mid, to);
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
                int[] result = new int[xs1.length + xs2.length];
                int i = 0;
                int j = 0;
                int k = 0;
                while (i < xs1.length && j < xs2.length) {
                    result[k++] = xs1[i] <= xs2[j] ? xs1[i++] : xs2[j++];
                }
                while (i < xs1.length) {
                    result[k++] = xs1[i++];
                }
                while (j < xs2.length) {
                    result[k++] = xs2[j++];
                }
                return result;
            });

            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));
//            System.out.println("# threads: "+ ForkJoinPool.commonPool().getRunningThreadCount());
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                	int[] partialArray = Arrays.copyOfRange(array, from, to);
                    sort(partialArray, 0, partialArray.length); 
                    return partialArray;
                }
        );
    }
}