package project.recommendationandtroubleshooting.model.troubleshooting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Description description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "solutionId")
    private Set<Solution> solutions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Description getDescription() {
        return description;
    }

    public Set<Solution> getSolutions() {
        return solutions;
    }
}
