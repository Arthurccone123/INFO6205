package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UFClient {

    public static int count(int n) {
        UF_HWQUPC uf = new UF_HWQUPC(n);
        int connections = 0;
        Random random = new Random();

        while (uf.components() > 1) {
            int p = random.nextInt(n);
            int q = random.nextInt(n);
            if (!uf.connected(p, q)) {
                uf.union(p, q);
            }
            connections++;
        }

        return connections;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a number as an argument.");
            return;
        }
        int n = Integer.parseInt(args[0]);
        System.out.println("Total connections needed for " + n + " sites: " + count(n));
    }
}
