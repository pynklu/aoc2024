package be.pynklu.aoc2024.solver.day2;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.stream;

@Component
public class DankReportSolver implements Solver {
    @Override
    public String getSolution() {
        var inputStrings = readFileByLine("inputs/day2/part1.txt");
        return String.valueOf(countSafeReports(inputStrings));
    }
    long countSafeReports(List<String> reports) {
        return reports.stream()
                .map(this::toIntArr)
                .filter(report -> isSafeReport(report, true))
                .count();
    }

    private boolean isSafeReport(int[] report, boolean damped) {
        Direction d = null;
        for (int i = 0; i < report.length-1; i++) {
            var seq = new Sequence(report[i], report[i+1]);
            d = d == null ? seq.direction() : d;
            var notGradual = !seq.gradual();
            var directionSwitch = seq.direction() != d;
            if(notGradual || directionSwitch) {
                return damped && (
                        isSafeReport(copyWithoutIndex(report, i), false)
                        || isSafeReport(copyWithoutIndex(report, i+1), false)
                        || (directionSwitch && isSafeReport(copyWithoutIndex(report, i-1), false))
                );
            }
        } return true;
    }

    private int[] toIntArr(String report) {
        return stream(report.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
    private int[] copyWithoutIndex(int[] arr, int indexToRemove) {
        var out = new int[arr.length - 1];
        var j = 0;
        for (int i = 0; i < arr.length; i++) {
            if(i != indexToRemove) {
                out[j] = arr[i];
                j++;
            }
        } return out;
    }

    record Sequence(int first, int last) {
        Direction direction() {
            return first < last ? Direction.UP : Direction.DOWN;
        }
        boolean gradual() {
            var diff = Math.abs(first - last);
            return 0 < diff && diff < 4;
        }
    }
    enum Direction { UP, DOWN }
}


