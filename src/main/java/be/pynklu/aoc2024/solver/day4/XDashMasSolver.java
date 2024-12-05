package be.pynklu.aoc2024.solver.day4;

import be.pynklu.aoc2024.solver.Solver;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class XDashMasSolver implements Solver {
    private List<String> grid;

    @Override
    public String getSolution() {
        var input = readFileByLine("inputs/day4/input.txt");
        var count = countXmas(input);
        return String.valueOf(count);
    }

    public long countXmas(List<String> input) {
        this.grid = input;

        long xmasCounter = 0;
        for (int y = 1; y < input.size() -1; y++) {
            for (int x = 1; x < input.getFirst().length() -1; x++) {
                var letter = input.get(y).charAt(x);
                if(letter == 'A' && isMASinAnX(x, y)) xmasCounter++;
            }
        }
        return xmasCounter;
    }

    boolean isMASinAnX(int x, int y) {
        var tl = grid.get(y - 1).charAt(x - 1);
        var br = grid.get(y + 1).charAt(x + 1);

        var tr = grid.get(y - 1).charAt(x + 1);
        var bl = grid.get(y + 1).charAt(x - 1);
        return (('S' == tl && 'M' == br) || ('M' == tl && 'S' == br))
                && (('S' == tr && 'M' == bl) || ('M' == tr && 'S' == bl));
    }
}



