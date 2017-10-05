package com.simscale.algorithms;

import java.util.ArrayList;

/**
 * A classic prime number generation algorithm working in O(n*log(n)).
 * Checks if numbers between limits are prime by dividing it by
 * integers up to half of those numbers.
 */
public class TrialDivisionGenerator implements PrimeGenerator {
    @Override
    public ArrayList<Integer> generate(int start, int end) {
        ArrayList<Integer> result = new ArrayList<>();

        // change start to 2 if it is less than 2.
        start = start < 2 ? 2 : start;
        // if it is a range such as (-100, 1), return empty list
        if (end < start) return result;

        for (int i = start; i <= end; i++) {
            if (isPrime(i)) result.add(i);
        }

        return result;
    }

    /**
     * Check if number is prime by dividing the number to integers up
     * to the number's sqrt. The reason is that an integer cannot be
     * divided by any integer between n/2 and n.
     * <p>
     * Works in O(logN) complexity.
     *
     * @param num to test for primality
     * @return if number is prime
     */
    private boolean isPrime(int num) {
        int upperBoundary = findUpperBoundary(num);

        if (num < 2) return false;

        for (int i = 2; i <= upperBoundary; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }

    private int findUpperBoundary(int num) {
        return (int) Math.sqrt(num);
    }
}