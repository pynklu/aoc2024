package be.pynklu.aoc2024.solver.day6;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GuardRoverSolver implements Solver {
    private Guard guard;
    private Lab lab;

    @Override
    public String getSolution() {
        var input = readFileByLine("inputs/day6/part1.txt");
        parseInput(input);
        walkTheLab();
        var result = lab.countVisited();
        return String.valueOf(result);
    }

    void parseInput(List<String> input) {
        var positionMatrix = new ArrayList<List<Pos>>();
        for (int y = 0; y < input.size(); y++) {
            var chars = input.get(y).toCharArray();
            var row = new ArrayList<Pos>();
            positionMatrix.add(row);
            for (int x = 0; x < chars.length; x++) {
                var isObstacle = '#' == chars[x];
                var isEdge = (y == 0) || (y == input.size() - 1) || (x == 0) || (x == chars.length - 1);
                var pos = new Pos(isObstacle, isEdge);
                row.add(pos);
                if ('^' == chars[x]) {
                    this.guard = new Guard(new Coord(x, y));
                    pos.visit();
                }
            }
        }
        this.lab = new Lab(positionMatrix);
    }

    void walkTheLab() {
        boolean guardIsInTheArea = true;
        long count = 0;
        while(guardIsInTheArea) {
            guardIsInTheArea = guard.move(lab);
            count++;
        }
    }
}

class Guard {
    private Direction direction = Direction.N;
    private Coord coord;

    public Guard(Coord coord) { this.coord = coord; }

    private void turn() { this.direction = this.direction.turnRight(); }

    public boolean move(Lab lab) {
        var posToCheck = getWatchedPos(lab);
        if (posToCheck.isObstructed()) {
            turn();
        } else {
            posToCheck.visit();
            if(posToCheck.isEdge()) {
                return false;
            } else {
                this.coord = this.coord.move(direction);
            }
        } return true;
    }

    private Pos getWatchedPos(Lab lab) {
        return lab.getPos(this.coord.move(this.direction));
    }
}

record Lab(List<List<Pos>> map) {
    public Pos getPos(Coord coord) {
        return map.get(coord.y()).get(coord.x());
    }

    public long countVisited() {
        return map.stream()
                .mapToLong(row -> row.stream().filter(Pos::wasVisited).count())
                .sum();
    }
}

enum Direction {
    N, E, S, W;

    public Direction turnRight() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }
}

record Coord(int x, int y) {
    public Coord move(Direction direction) {
        return switch (direction) {
            case N -> new Coord(x, y - 1);
            case E -> new Coord(x + 1, y);
            case S -> new Coord(x, y + 1);
            case W -> new Coord(x - 1, y);
        };
    }
}

class Pos {
    private final boolean obstructed;
    private final boolean edge;
    private boolean visited = false;

    public Pos(boolean obstructed, boolean edge) {
        this.obstructed = obstructed;
        this.edge = edge;
    }
    public void visit() { this.visited = true; }

    public boolean isObstructed() { return obstructed; }
    public boolean isEdge() { return edge; }
    public boolean wasVisited() { return visited; }
}


