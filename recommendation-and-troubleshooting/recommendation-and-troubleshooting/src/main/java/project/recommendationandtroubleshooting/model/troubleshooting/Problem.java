package project.recommendationandtroubleshooting.model.troubleshooting;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Problem {

    private Set<String> problems = new HashSet<>();

    private Set<String> triedSolutions = new HashSet<>();

    private String currentSolution;

    private boolean solutionWorked = false;

    public Problem(Set<String> problems) {
        this.problems = problems;
    }

    public void addSolution(String solution) {
        this.triedSolutions.add(solution);
    }

    public Set<String> getProblems() {
        return problems;
    }

    public Set<String> getTriedSolutions() {
        return triedSolutions;
    }

    public String getCurrentSolution() {
        return currentSolution;
    }

    public boolean isSolutionWorked() {
        return solutionWorked;
    }

    public Set<String> moveCurrentSolution() {
        if(this.currentSolution != null){
            this.triedSolutions.add(this.currentSolution);
            this.currentSolution = null;
        }
        return this.triedSolutions;
    }
}
