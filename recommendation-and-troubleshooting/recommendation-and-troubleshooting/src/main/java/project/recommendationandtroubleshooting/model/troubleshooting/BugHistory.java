package project.recommendationandtroubleshooting.model.troubleshooting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bug_histories")
public class BugHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_bug", nullable = false)
    private Date dateOfBug;

    public Long getId() {
        return id;
    }

    public Date getDateOfBug() {
        return dateOfBug;
    }

    public Bug getBug() {
        return bug;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Bug bug;
}
