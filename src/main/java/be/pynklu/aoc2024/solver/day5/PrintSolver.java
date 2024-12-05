package be.pynklu.aoc2024.solver.day5;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class PrintSolver implements Solver {
    Map<Integer, List<Integer>> prevNumbersFor;
    @Override
    public String getSolution() {
        return "";
    }

    public List<String> parseOrdersAndReturnInput(List<String> inputs) {

    }

    private Pattern rulePattern = Pattern.compile("(/d+)");
    public void parseOrderingRule(String rule) {
        var matcher = rulePattern.matcher(rule);
        var lowerNum = Integer.parseInt(matcher.group(0));
        var upperNum = Integer.parseInt(matcher.group(1));

    }
}
