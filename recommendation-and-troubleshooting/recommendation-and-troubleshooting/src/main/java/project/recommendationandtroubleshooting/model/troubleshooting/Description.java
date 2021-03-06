package project.recommendationandtroubleshooting.model.troubleshooting;

import javax.persistence.*;

@Entity
@Table(name = "descriptions")
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "problem_description", unique = true, nullable = false)
    private String problemDescription;

    public Description() {

    }

    public Description(Integer id, String problemDescription) {
        this.id = id;
        this.problemDescription = problemDescription;
    }

    public Description(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Integer getId() {
        return id;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Description)) {
            return false;
        }
        Description d = (Description) obj;

        return this.problemDescription.equals(d.problemDescription);
    }
}
