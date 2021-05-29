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

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Bug bug;

    public BugHistory() {

    }

    public BugHistory(Date dateOfBug, Bug bug) {
        this.dateOfBug = dateOfBug;
        this.bug = bug;
    }

    public BugHistory(Long id, Date dateOfBug, Bug bug) {
        this.id = id;
        this.dateOfBug = dateOfBug;
        this.bug = bug;
    }

    public Long getId() {
        return id;
    }

    public Date getDateOfBug() {
        return dateOfBug;
    }

    public Bug getBug() {
        return bug;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateOfBug(Date dateOfBug) {
        this.dateOfBug = dateOfBug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }
}
