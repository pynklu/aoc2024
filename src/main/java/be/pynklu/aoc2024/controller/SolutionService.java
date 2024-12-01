package be.pynklu.aoc2024.controller;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolutionService {
    private final List<Solver> solvers;
    public SolutionService(List<Solver> solvers) {
        this.solvers = solvers;
    }

    public String solveAll() {
        return solvers.stream()
                .map(Solver::getSolution)
                .collect(Collectors.joining("\n"));
    }
}
