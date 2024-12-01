package be.pynklu.aoc2024.solver.day1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationSimilaritySolverTest {
    List<String> testInput = List.of(
            "3   4",
            "4   3",
            "2   5",
            "1   3",
            "3   9",
            "3   3"
    );

    @Autowired
    private LocationSimilaritySolver solver;

    @Test
    void findTotalDistance() {
        var similarity = solver.calculateSimilarity(testInput);
        assertEquals(31, similarity);
    }
}