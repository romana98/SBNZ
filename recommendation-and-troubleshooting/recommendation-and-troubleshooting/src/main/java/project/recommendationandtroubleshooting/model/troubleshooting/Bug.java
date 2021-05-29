package project.recommendationandtroubleshooting.model.troubleshooting;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "descriptionId")
    private Set<Description> descriptions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "order_solution_mapping",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "solutionId", referencedColumnName = "id")})
    private Map<Integer, Solution> solutions = new HashMap<>();

    public Bug() {

    }

    public Bug(Long id, Set<Description> descriptions, Map<Integer, Solution> solutions) {
        this.id = id;
        this.descriptions = descriptions;
        this.solutions = solutions;
    }

    public Bug(Set<Description> descriptions, Map<Integer, Solution> solutions) {
        this.descriptions = descriptions;
        this.solutions = solutions;
    }

    public Long getId() {
        return id;
    }

    public Set<Description> getDescriptions() {
        return descriptions;
    }

    public Map<Integer, Solution> getSolutions() {
        return solutions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescriptions(Set<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public void setSolutions(Map<Integer, Solution> solutions) {
        this.solutions = solutions;
    }

    /*
    public List<Solution> getSortedSolutions(){
        List<Solution> solutions = new ArrayList<>();
        solutions.addAll(this.solutions);
        solutions.sort(Comparator.comparing(Solution::getSolution));
        return solutions;
    }
    */
}
