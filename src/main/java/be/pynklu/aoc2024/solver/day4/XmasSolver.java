package be.pynklu.aoc2024.solver.day4;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class XmasSolver implements Solver {
    private int width;
    private int height;
    private List<String> grid;

    @Override
    public String getSolution() {
        var input = readFileByLine("inputs/day4/input.txt");
        var count = countXmas(input);
        return String.valueOf(count);
    }

    public long countXmas(List<String> input) {
        this.height = input.size();
        this.width = input.getFirst().length();
        this.grid = input;

        long xmasCounter = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                var letter = input.get(y).charAt(x);
                if (letter == 'X') {
                    var coord = new Coord(x, y);
                    xmasCounter += getDirectionsToCheck(x, y).stream()
                            .filter(d -> isThereXmasSpiritInThisDirection(coord, d))
                            .count();
                }
            }
        }
        return xmasCounter;
    }

    List<Direction> getDirectionsToCheck(int x, int y) {
        var validDirections = new ArrayList<Direction>();
        var notCloseToTop = y >= 3;
        var notCloseToRight = x < width - 3;
        var notCloseToBottom = y < height - 3;
        var notCloseToLeft = x >= 3;

        if (notCloseToTop) validDirections.add(Direction.N);
        if (notCloseToTop && notCloseToRight) validDirections.add(Direction.NE);
        if (notCloseToRight) validDirections.add(Direction.E);
        if (notCloseToBottom && notCloseToRight) validDirections.add(Direction.SE);
        if (notCloseToBottom) validDirections.add(Direction.S);
        if (notCloseToBottom && notCloseToLeft) validDirections.add(Direction.SW);
        if (notCloseToLeft) validDirections.add(Direction.W);
        if (notCloseToTop && notCloseToLeft) validDirections.add(Direction.NW);

        return validDirections;
    }

    private boolean isThereXmasSpiritInThisDirection(Coord coord, Direction dir) {
        int found = 0;
        var toCheck = new char[]{'M', 'A', 'S'};
        while(found < 3) {
            coord = coord.move(dir);
            if(!checkSpot(coord, toCheck[found])) break;
            found++;
        }
        return found == 3;
    }

    private boolean checkSpot(Coord spot, char letter) {
        return letter == grid.get(spot.y()).charAt(spot.x());
    }

    record Coord(int x, int y) {
        Coord move(Direction dir) {
            return switch (dir) {
                case N -> new Coord(x, y - 1);
                case NE -> new Coord(x + 1, y - 1);
                case E -> new Coord(x + 1, y);
                case SE -> new Coord(x + 1, y + 1);
                case S -> new Coord(x, y + 1);
                case SW -> new Coord(x - 1, y + 1);
                case W -> new Coord(x - 1, y);
                case NW -> new Coord(x - 1, y - 1);
            };
        }
    }
}

enum Direction { N, NE, E, SE, S, SW, W, NW }
