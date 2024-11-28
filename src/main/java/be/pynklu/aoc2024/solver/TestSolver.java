package be.pynklu.aoc2024.solver;

import org.springframework.stereotype.Component;

@Component
public class TestSolver implements Solver {
    @Override
    public String getSolution() {
        return "Dit is een testoplossing";
    }
}
