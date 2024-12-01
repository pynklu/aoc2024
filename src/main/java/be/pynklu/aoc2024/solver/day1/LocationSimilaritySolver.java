package be.pynklu.aoc2024.solver.day1;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntToLongFunction;
import java.util.stream.Collectors;

@Component
public class LocationSimilaritySolver implements Solver {
    @Override
    public String getSolution() {
        var inputStrings = readFileByLine("inputs/day1/part2.txt");
        return String.valueOf(calculateSimilarity(inputStrings));
    }

    long calculateSimilarity(List<String> inputStrings) {
        var lists = inputStrings
                .stream()
                .map((s -> s.split("\\s+")))
                .collect(
                    Collectors.teeing(
                        Collectors.mapping(arr -> arr[0], Collectors.toList()),
                        Collectors.mapping(arr -> arr[1], Collectors.toList()),
                        (arr1, arr2) -> List.of(toIntArray(arr1),toIntArray(arr2))
                ));
        IntToLongFunction getDistance = number -> {
            var occurrences = Arrays.stream(lists.getLast()).filter(num -> num == number).count();
            return number * occurrences;
        };
        return Arrays.stream(lists.getFirst()).mapToLong(getDistance).sum();
    }

    int[] toIntArray(List<String> strings) {
        return strings.stream().mapToInt(Integer::parseInt).toArray();
    }
}
