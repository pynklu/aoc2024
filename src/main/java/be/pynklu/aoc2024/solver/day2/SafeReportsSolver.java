package be.pynklu.aoc2024.solver.day2;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Gatherers;

import static java.util.Arrays.stream;

@Component
public class SafeReportsSolver implements Solver {
    @Override
    public String getSolution() {
        var inputStrings = readFileByLine("inputs/day2/part1.txt");
        return String.valueOf(countSafeReports(inputStrings));
    }
    long countSafeReports(List<String> reports) {
        return reports.stream()
                .map(this::toIntArr)
                .filter(this::isSafeReport)
                .count();
    }
    private List<Integer> toIntArr(String report) {
        return stream(report.split("\\s+"))
                .map(Integer::valueOf)
                .toList();
    }
    private boolean isSafeReport(List<Integer> report) {
        List<List<Integer>> list = report.stream()
                .gather(Gatherers.windowSliding(2))
                .toList();
        boolean ascending = false;
        for (int i = 0; i < list.size(); i++) {
            var pair = list.get(i);
            var seq = new Sequence(pair.getFirst(), pair.getLast());
            if(i == 0) ascending = seq.ascending();
            if(!seq.gradual() || seq.ascending() != ascending) return false;
        } return true;
    }

    record Sequence(Integer first, Integer last) {
        boolean ascending() { return first < last; }
        boolean gradual() {
            var diff = Math.abs(first - last);
            return 0 < diff && diff < 4;
        }
    }
}

