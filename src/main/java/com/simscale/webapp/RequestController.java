package com.simscale.webapp;

import com.simscale.algorithms.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/primes")
public class RequestController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody
    List<Integer> getPrimes(@RequestBody Request r) {
        java.util.Date currentDate = new java.util.Date();
        java.text.SimpleDateFormat databaseDateFormat =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String requestTime = databaseDateFormat.format(currentDate);

        ArrayList<Integer> result = new ArrayList<>();
        int start = r.getStart();
        int end = r.getEnd();
        String algorithmType = r.getAlgorithmType();

        if (end < start)
            throw new IllegalArgumentException("End number must be larger than start number.");

        long timeBefore = System.currentTimeMillis();
        if (Objects.equals(algorithmType, "trial")) {
            ArrayList<Integer> primes = new TrialDivisionGenerator().generate(start, end);
            result.addAll(primes);
        } else if (Objects.equals(algorithmType, "sieve")) {
            ArrayList<Integer> primes = new SieveGenerator().generate(start, end);
            result.addAll(primes);
        } else if (Objects.equals(algorithmType, "segmented")) {
            ArrayList<Integer> primes = new SegmentedSieveGenerator().generate(start, end);
            result.addAll(primes);
        } else if (Objects.equals(algorithmType, "miller")) {
            ArrayList<Integer> primes = new MillerRabinGenerator().generate(start, end);
            result.addAll(primes);
        } else if (Objects.equals(algorithmType, "heuristic")) {
            ArrayList<Integer> primes = new HeuristicGenerator().generate(start, end);
            result.addAll(primes);
        } else {
            throw new UnsupportedOperationException("Algorithm type is not supported. You can choose one of: 'trial', 'heuristic', 'miller', 'sieve', 'segmented'");
        }

        long timeAfter = System.currentTimeMillis();
        long elapsedTime = timeAfter - timeBefore;
        jdbcTemplate.update("INSERT INTO requests(timestamp, start,end, time_elapsed, algorithm_type, number_of_primes) VALUES(?,?,?,?,?,?)",
                requestTime, start, end, elapsedTime, algorithmType, result.size());

        return result;
    }
}
