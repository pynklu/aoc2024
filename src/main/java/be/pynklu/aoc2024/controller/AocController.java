package be.pynklu.aoc2024.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aoc")
public class AocController {
    private final SolutionService solutionService;
    public AocController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @GetMapping("/")
    public String index() {
        return "Howdy";
    }

    @GetMapping("/all")
    public String allSolutions() {
        return solutionService.solveAll();
    }

    @GetMapping("/today")
    public String today() {
        return solutionService.solveToday();
    }
}
