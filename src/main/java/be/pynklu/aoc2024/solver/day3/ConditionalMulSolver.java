package be.pynklu.aoc2024.solver.day3;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

@Component
@Primary
public class ConditionalMulSolver implements Solver {
    @Override
    public String getSolution() {
        var input = this.readFile("inputs/day3/part1.txt");
        return String.valueOf(multiplyAll(input));
    }

    private final Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|(do\\(\\))|(don't\\(\\))");
    long multiplyAll(String muls){
        var matcher = pattern.matcher(muls);
        AtomicBoolean didgeridoo = new AtomicBoolean(true);
        return matcher.results()
                .mapToLong(r -> {
                    if(r.group(3) != null) {
                        didgeridoo.set(true);
                    } else if (r.group(4) != null) {
                        didgeridoo.set(false);
                    } else if (didgeridoo.get()) {
                        return multNumberStrings(r.group(1), r.group(2));
                    } return 0;
                }).sum();
    }

    private long multNumberStrings(String a, String b) {
        return (long) Integer.parseInt(a) * Integer.parseInt(b);
    }
}