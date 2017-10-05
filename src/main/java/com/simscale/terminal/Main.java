package com.simscale.terminal;

import com.simscale.algorithms.*;

import java.util.ArrayList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        try {
            String algorithmType = args[0];
            int start = Integer.parseInt(args[1]);
            int end = Integer.parseInt(args[2]);

            if (end < start) throw new IllegalArgumentException("End number must be larger than start number.");

            if (Objects.equals(algorithmType, "trial")) {
                ArrayList<Integer> primes = new TrialDivisionGenerator().generate(start, end);
                System.out.println(primes);
            } else if (Objects.equals(algorithmType, "heuristic")) {
                ArrayList<Integer> primes = new HeuristicGenerator().generate(start, end);
                System.out.println(primes);
            } else if (Objects.equals(algorithmType, "miller")) {
                ArrayList<Integer> primes = new MillerRabinGenerator().generate(start, end);
                System.out.println(primes);
            } else if (Objects.equals(algorithmType, "sieve")) {
                ArrayList<Integer> primes = new SieveGenerator().generate(start, end);
                System.out.println(primes);
            } else if (Objects.equals(algorithmType, "segmented")) {
                ArrayList<Integer> primes = new SegmentedSieveGenerator().generate(start, end);
                System.out.println(primes);
            } else {
                throw new UnsupportedOperationException();
            }

        } catch (UnsupportedOperationException e) {
            System.err.println("Algorithm type is not supported. You can choose one of: 'trial', 'heuristic', 'miller', 'sieve', 'segmented'");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("You must enter three arguments with following order: algorithm type, start, end");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
