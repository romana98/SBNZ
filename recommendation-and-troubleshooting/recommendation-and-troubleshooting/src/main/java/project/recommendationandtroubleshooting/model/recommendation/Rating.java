package project.recommendationandtroubleshooting.model.recommendation;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rate", nullable = false)
    private Double rate;

    public Rating() {

    }

    public Rating(Double rate) {
        this.rate = rate;
    }

    public Rating(Integer id, Double rate) {
        this.id = id;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public Double getRate() {
        return rate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}

