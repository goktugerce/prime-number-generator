package com.simscale.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Generates prime numbers using Sieve of Eratosthenes algorithm.
 * For more information: https://www.wikiwand.com/en/Sieve_of_Eratosthenes#Pseudocode
 * Runs in O(N^2) time.
 */
public class SieveGenerator implements PrimeGenerator {
    private boolean[] isPrime;

    @Override
    public ArrayList<Integer> generate(int start, int end) {
        ArrayList<Integer> result = new ArrayList<>();

        // change start to 2 if it is less than 2.
        start = start < 2 ? 2 : start;
        // if it is a range such as (-100, 1), return empty list
        if (end < start) return result;

        isPrime = new boolean[end + 1];
        Arrays.fill(isPrime, true);

        markComposites(end);

        for (int i = start; i <= end; i++) {
            if (isPrime[i]) result.add(i);
        }

        return result;
    }

    /**
     * Change composite numbers to false flags in the array,
     * using Sieve of Eratosthenes algorithm. Runs in O(n^2)
     *
     * @param end the largest number in the rangeF
     */
    private void markComposites(int end) {
        int STARTING_POSITION = 2;

        for (int i = STARTING_POSITION; i * i <= end; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= end; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}