package be.pynklu.aoc2024.solver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public interface Solver {
    String getSolution();
    default List<String> readFileByLine(String fileName) {
        try {
            Path path = Path.of(getClass().getClassLoader().getResource(fileName).toURI());
            return Files.readAllLines(path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file: " + fileName, e);
        }
    }
}
