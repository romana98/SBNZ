package project.recommendationandtroubleshooting.model.troubleshooting;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bug_histories")
public class BugHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_bug", nullable = false)
    private Date dateOfBug;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "solution_id", referencedColumnName = "id")
    private Solution solution;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Bug bug;

    public BugHistory() {

    }

    public BugHistory(Date dateOfBug, Solution solution, Bug bug) {
        this.dateOfBug = dateOfBug;
        this.solution = solution;
        this.bug = bug;
    }

    public BugHistory(Long id, Date dateOfBug, Solution solution, Bug bug) {
        this.id = id;
        this.dateOfBug = dateOfBug;
        this.solution = solution;
        this.bug = bug;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfBug() {
        return dateOfBug;
    }

    public void setDateOfBug(Date dateOfBug) {
        this.dateOfBug = dateOfBug;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    @Override
    public String toString() {
        return solution.toString();
    }
}
