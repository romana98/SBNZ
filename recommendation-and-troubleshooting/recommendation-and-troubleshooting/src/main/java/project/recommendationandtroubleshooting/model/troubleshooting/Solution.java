package project.recommendationandtroubleshooting.model.troubleshooting;

import javax.persistence.*;

@Entity
@Table(name = "solutions")
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "solution", unique = true, nullable = false)
    private String solution;

    public Solution() {
    }

    public Solution(Integer id, String solution) {
        this.id = id;
        this.solution = solution;
    }

    public Solution(String solution) {
        this.solution = solution;
    }

    public Integer getId() {
        return id;
    }

    public String getSolution() {
        return solution;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return this.getSolution();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Solution)) {
            return false;
        }
        Solution s = (Solution) obj;
        return this.solution.equals(s.solution);
    }
}
