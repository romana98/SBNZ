package project.recommendationandtroubleshooting.model.troubleshooting;

import java.util.HashSet;
import java.util.Set;

public class Problem {

    private Set<Description> problems = new HashSet<>();

    private Set<Solution> triedSolutions = new HashSet<>();

    private Solution currentSolution;

    private boolean solutionWorked = false;

    public Problem() {

    }

    public Problem(Set<Description> problems, Set<Solution> triedSolutions, Solution currentSolution, boolean solutionWorked) {
        this.problems = problems;
        this.triedSolutions = triedSolutions;
        this.currentSolution = currentSolution;
        this.solutionWorked = solutionWorked;
    }

    public Problem(Set<Description> problems) {
        this.problems = problems;
    }

    public void addSolution(Solution solution) {
        this.triedSolutions.add(solution);
    }

    public Set<Description> getProblems() {
        return problems;
    }

    public Set<Solution> getTriedSolutions() {
        return triedSolutions;
    }

    public Solution getCurrentSolution() {
        return currentSolution;
    }

    public boolean isSolutionWorked() {
        return solutionWorked;
    }

    public void setProblems(Set<Description> problems) {
        this.problems = problems;
    }

    public void setTriedSolutions(Set<Solution> triedSolutions) {
        this.triedSolutions = triedSolutions;
    }

    public void setCurrentSolution(Solution currentSolution) {
        this.currentSolution = currentSolution;
    }

    public void setSolutionWorked(boolean solutionWorked) {
        this.solutionWorked = solutionWorked;
    }

    public Set<Solution> moveCurrentSolution() {
        if (this.currentSolution != null) {
            this.triedSolutions.add(this.currentSolution);
            this.currentSolution = null;
        }
        return this.triedSolutions;
    }
}
