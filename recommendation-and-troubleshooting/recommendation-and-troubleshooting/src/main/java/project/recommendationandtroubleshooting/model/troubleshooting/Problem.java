package project.recommendationandtroubleshooting.model.troubleshooting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Problem {

    private Set<Description> problems = new HashSet<>();

    private Set<Solution> triedSolutions = new HashSet<>();

    private Solution currentSolution;

}
