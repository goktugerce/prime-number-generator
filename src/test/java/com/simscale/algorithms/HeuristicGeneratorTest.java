package com.simscale.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeuristicGeneratorTest {
    private HeuristicGenerator generator = new HeuristicGenerator();

    @Test
    public void generate() throws Exception {
        System.out.println("Testing for numbers between 1 and 10");

        ArrayList<Integer> actual = generator.generate(1, 10);

        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 3, 5, 7));

        assertEquals(expected, actual);
    }

    @Test
    public void generate2() throws Exception {
        System.out.println("Testing for numbers between 1365 and 1431");

        ArrayList<Integer> actual = generator.generate(1365, 1431);

        List<Integer> expected = new ArrayList<>(Arrays.asList(1367, 1373, 1381, 1399, 1409, 1423, 1427, 1429));

        assertEquals(expected, actual);
    }

    @Test
    public void generate3() throws Exception {
        System.out.println("Testing for both negative and positive numbers");

        ArrayList<Integer> actual = generator.generate(-20, 20);

        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));

        assertEquals(expected, actual);
    }

    @Test
    public void generate4() throws Exception {
        System.out.println("Testing negative numbers and empty result");

        ArrayList<Integer> actual = generator.generate(-20, 0);

        List<Integer> expected = new ArrayList<>(Collections.emptyList());

        assertEquals(expected, actual);
    }
}