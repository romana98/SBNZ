package project.recommendationandtroubleshooting.model.troubleshooting;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Set<String> descriptions;

    @ElementCollection
    private Set<String> solutions = new HashSet<>();

    public Bug(Set<String> descriptions, Set<String> solutions) {
        this.descriptions = descriptions;
        this.solutions = solutions;
    }

    public Long getId() {
        return id;
    }

    public Set<String> getDescriptions() {
        return descriptions;
    }

    public Set<String> getSolutions() {
        return solutions;
    }

    public List<String> getSortedSolutions(){
        List<String> solutions = new ArrayList<>();
        solutions.addAll(this.solutions);
        Collections.sort(solutions);
        return solutions;

    }
}
