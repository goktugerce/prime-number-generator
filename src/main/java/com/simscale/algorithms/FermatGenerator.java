package com.simscale.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Implementation of Fermat's algorithm for primality test.
 * Works in O(k × log^2(n) × log log n × log log log n)
 * <p>
 * Source: https://en.wikipedia.org/wiki/Fermat_primality_test
 */
public class FermatGenerator implements PrimeGenerator {
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
     * Test number's primality by using Fermat test.
     *
     * @param num number to test for primality
     * @return if the number is prime or not
     */
    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        BigInteger numInBigInt = convertToBigInteger(num);
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            if (!passesFermatTest(numInBigInt))
                return false;
        }

        return true;
    }

    /**
     * Generate number and test for primality by using Fermat test.
     *
     * @param numInBigInt number to test for primality
     * @return if number passes primality test
     */
    private boolean passesFermatTest(BigInteger numInBigInt) {
        BigInteger randomNumber = generateRandomNumber(numInBigInt);

        return isValidInFermatTest(numInBigInt, randomNumber);
    }

    /**
     * Check if the number is valid in fermat test.
     * a ^ num mod num should be 0 to pass the fermat test.
     *
     * @param num original number to test for primality
     * @param a   the random number
     * @return if number passes fermat test
     */
    private boolean isValidInFermatTest(BigInteger num, BigInteger a) {
        return a.modPow(num, num).compareTo(a) == 0;
    }

    /**
     * Converts the integer to big integer
     *
     * @param num to convert to big integer
     * @return the number in big integer
     */
    private BigInteger convertToBigInteger(int num) {
        return BigInteger.valueOf(num);
    }

    /**
     * Generate random Big Integer in range [1, number-1]
     *
     * @param numInBigInt number to check for primality
     * @return a random big integer
     */
    private BigInteger generateRandomNumber(BigInteger numInBigInt) {
        BigInteger randomNumber = new BigInteger(numInBigInt.bitLength(), rand);
        // taking mod ensures the range is [0, number - 2]
        randomNumber = randomNumber.mod(numInBigInt.subtract(BigInteger.ONE));
        // add 1 so the range will be [1, number - 1]
        randomNumber = randomNumber.add(BigInteger.ONE);

        return randomNumber;
    }
}