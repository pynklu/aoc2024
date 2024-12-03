package be.pynklu.aoc2024.solver.day2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DankReportSolverTest {
    @Autowired
    DankReportSolver solver;

    List<String> inputs = List.of(
            "7 6 4 2 1",
            "1 2 7 8 9",
            "9 7 6 2 1",
            "1 3 2 4 5",
            "8 6 4 4 1",
            "1 3 6 7 9"
    );

    @Test
    void countSafeReports() {
        var count = solver.countSafeReports(inputs);
        assertEquals(4, count);
    }
}