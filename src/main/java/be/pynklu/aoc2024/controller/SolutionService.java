package be.pynklu.aoc2024.controller;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolutionService {
    private final List<Solver> solvers;
    private final Solver todaySolver;
    public SolutionService(List<Solver> solvers, Solver todaySolver) {
        this.solvers = solvers;
        this.todaySolver = todaySolver;
    }

    public String solveAll() {
        return solvers.stream()
                .map(Solver::getSolution)
                .collect(Collectors.joining("\n"));
    }

    public String solveToday() {
        return todaySolver.getSolution();
    }
}
