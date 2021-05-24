package project.recommendationandtroubleshooting.model.troubleshooting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "solutions")
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "solution", unique = true, nullable = false)
    private String solution;

    public Solution(String solution){
        this.solution = solution;
    }

    public Long getId() {
        return id;
    }

    public String getSolution() {
        return solution;
    }
}
