package com.simscale.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class SegmentedSieveGenerator implements PrimeGenerator {
    @Override
    public ArrayList<Integer> generate(int start, int end) {
        ArrayList<Integer> result = new ArrayList<>();

        // change start to 2 if it is less than 2.
        start = start < 2 ? 2 : start;
        // if it is a range such as (-100, 1), return empty list
        if (end < start) return result;

        int lowerLimit = 2;
        int upperLimit = (int) Math.sqrt(end);

        ArrayList<Integer> primes = new SieveGenerator().generate(lowerLimit, upperLimit);

        boolean[] isPrime = new boolean[end - start + 1];
        Arrays.fill(isPrime, true);

        for (int prime : primes) {
            int lowLimit = (int) (Math.floor(start / prime) * prime);
            if (lowLimit < start) lowLimit += prime;

            for (int j = lowLimit; j <= end; j += prime) {
                if (j != prime)
                    isPrime[j - start] = false;
            }
        }

        for (int i = start; i <= end; i++) {
            if (isPrime[i - start]) result.add(i);
        }

        return result;
    }
}