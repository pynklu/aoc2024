package be.pynklu.aoc2024.solver.day6;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GuardRoverSolverTest {
    @Autowired
    GuardRoverSolver solver;

    @Test
    void parseInput() {
        var input = solver.readFileByLine("inputs/day6/input1.txt");
        solver.parseInput(input);
        solver.walkTheLab();
        //var actual = solver.countVisited();
        //assertEquals(41, actual);
    }
}