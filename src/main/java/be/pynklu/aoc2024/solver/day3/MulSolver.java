package be.pynklu.aoc2024.solver.day3;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class MulSolver implements Solver {
    @Override
    public String getSolution() {
        var input = this.readFile("inputs/day3/part1.txt");
        return String.valueOf(multiplyAll(input));
    }


    private final Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
    long multiplyAll(String muls){
        var matcher = pattern.matcher(muls);
        return matcher.results()
                .mapToLong(r -> multNumberStrings(r.group(1), r.group(2)))
                .sum();
    }

    private long multNumberStrings(String a, String b) {
        return (long) Integer.parseInt(a) * Integer.parseInt(b);
    }
}
