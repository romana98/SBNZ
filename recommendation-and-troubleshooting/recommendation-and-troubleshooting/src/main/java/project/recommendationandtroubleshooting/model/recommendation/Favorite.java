package project.recommendationandtroubleshooting.model.recommendation;


import javax.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Configuration configuration;

    @Column(name = "put_to_favorite", nullable = false)
    private Long putToFavorite;

    public Favorite() {
    }

    public Favorite(Configuration configuration, Long putToFavorite) {
        this.configuration = configuration;
        this.putToFavorite = putToFavorite;
    }

    public Favorite(Long id, Configuration configuration, Long putToFavorite) {
        this.id = id;
        this.configuration = configuration;
        this.putToFavorite = putToFavorite;
    }

    public Long getId() {
        return id;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Long getPutToFavorite() {
        return putToFavorite;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setPutToFavorite(Long putToFavorite) {
        this.putToFavorite = putToFavorite;
    }
}
