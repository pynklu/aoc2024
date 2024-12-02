package be.pynklu.aoc2024.solver.day1;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationDistanceSolver implements Solver {

    @Override
    public String getSolution() {
        var inputStrings = readFileByLine("inputs/day1/part1.txt");
        return String.valueOf(findTotalDistance(inputStrings));
    }

    int findTotalDistance(List<String> inputStrings) {
        var firstList = inputStrings.stream().map((s -> s.split("\\s+"))).map(a -> a[0]).mapToInt(Integer::parseInt).sorted().toArray();
        var secondList = inputStrings.stream().map((s -> s.split("\\s+"))).map(a -> a[1]).mapToInt(Integer::parseInt).sorted().toArray();
        int total = 0;
        for (int i = 0; i < firstList.length; i++) {
            total += Math.abs(secondList[i] - firstList[i]);
        }
        return total;
    }
}
