package project.recommendationandtroubleshooting.model.troubleshooting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Problem {

    private Set<Description> problems = new HashSet<>();

    private Set<Solution> triedSolutions = new HashSet<>();

    private Solution currentSolution;

    public Set<Description> getProblems() {
        return problems;
    }

    public Set<Solution> getTriedSolutions() {
        return triedSolutions;
    }

    public Solution getCurrentSolution() {
        return currentSolution;
    }
}
