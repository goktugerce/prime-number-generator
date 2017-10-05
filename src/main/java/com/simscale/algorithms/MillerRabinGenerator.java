package com.simscale.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Implementation of Miller-Rabin Primality Test algorithm.
 * Works in O(k log^3(n))
 * <p>
 * Source: https://en.wikipedia.org/wiki/Miller–Rabin_primality_test
 */
public class MillerRabinGenerator implements PrimeGenerator {
    private static final int MAX_ITERATIONS = 20;
    private Random rand = new Random();

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
     * Check number's primality.
     *
     * @param num to test for primality
     * @return if number is prime or not
     */
    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2 || num == 3) return true;
        // return false for even numbers
        if (num % 2 == 0) return false;

        return passesMillerRabinTest(num);
    }

    /**
     * Put number in Miller-Rabin test.
     *
     * @param num to test for primality
     * @return if number is prime or not.F
     */
    private boolean passesMillerRabinTest(int num) {
        // write number as num - 1 = (2^r)*d
        int r = 0;
        int d = num - 1;

        while (d % 2 == 0) {
            r += 1;
            d /= 2;
        }

        OuterLoop:
        for (int k = 0; k < MAX_ITERATIONS; k++) {
            int a = generateRandomNumberForTest(num);

            // calculate a^d mod num
            BigInteger xBig = BigInteger.valueOf(a).modPow(BigInteger.valueOf(d), BigInteger.valueOf(num));
            int x = xBig.intValue();

            if (x == 1 || x == num - 1)
                continue;

            for (int iterNum = 0; iterNum < r - 1; iterNum++) {
                x = (int) (Math.pow(x, 2) % num);

                if (x == 1)
                    return false;
                if (x == num - 1)
                    continue OuterLoop;
            }

            return false;
        }

        return true;
    }

    /**
     * Generate a random number in range [2, num - 2]
     *
     * @param num to be used
     * @return random integer between 2 and num - 2.
     */
    private int generateRandomNumberForTest(int num) {
        return rand.nextInt((num - 2) + 1 - 2) + 2;
    }

}