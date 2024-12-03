package be.pynklu.aoc2024.solver.day3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MulSolverTest {
    @Autowired
    MulSolver solver;

    @Autowired
    ConditionalMulSolver conditionalSolver;

    String testInput = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
    String doDontInput = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";

    @Test
    void multiplyAll() {
        var sum = solver.multiplyAll(testInput);
        assertEquals(161, sum);
    }

    @Test
    void conditionalMultiply() {
        var sum = conditionalSolver.multiplyAll(doDontInput);
        assertEquals(48, sum);
    }
}