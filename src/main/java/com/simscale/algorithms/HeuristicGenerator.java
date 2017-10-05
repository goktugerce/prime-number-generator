package com.simscale.algorithms;

import java.util.ArrayList;

/**
 * Tests number's primality by combining Fermat test
 * and Miller-Rabin test, since they give probable primes.
 */
public class HeuristicGenerator implements PrimeGenerator {
    @Override
    public ArrayList<Integer> generate(int start, int end) {
        ArrayList<Integer> fermatResult = new FermatGenerator().generate(start, end);
        ArrayList<Integer> millerRabinResult = new MillerRabinGenerator().generate(start, end);

        ArrayList<Integer> commonNumbers = new ArrayList<>(fermatResult);
        commonNumbers.retainAll(millerRabinResult);

        return commonNumbers;
    }
}