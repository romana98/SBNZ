package project.recommendationandtroubleshooting.model.recommendation;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private ConfigurationClass configuration;

    @Column(name = "put_to_favorite", nullable = false)
    private Long putToFavorite;

    @Column(name = "date_of_favorite", nullable = false)
    private Date dateOfFavorite;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    public Favorite() {
    }

    public Favorite(ConfigurationClass configuration, Long putToFavorite, Date dateOfFavorite) {
        this.configuration = configuration;
        this.putToFavorite = putToFavorite;
        this.dateOfFavorite = dateOfFavorite;
        this.active = true;
    }

    public Favorite(Integer id, ConfigurationClass configuration, Long putToFavorite, Date dateOfFavorite) {
        this.id = id;
        this.configuration = configuration;
        this.putToFavorite = putToFavorite;
        this.dateOfFavorite = dateOfFavorite;
    }

    public Favorite(Integer id, ConfigurationClass configuration, Long putToFavorite, Date dateOfFavorite, boolean active) {
        this.id = id;
        this.configuration = configuration;
        this.putToFavorite = putToFavorite;
        this.dateOfFavorite = dateOfFavorite;
        this.active = active;
    }

    public Favorite(ConfigurationClass configuration, Long putToFavorite, Date dateOfFavorite, boolean active) {
        this.configuration = configuration;
        this.putToFavorite = putToFavorite;
        this.dateOfFavorite = dateOfFavorite;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public ConfigurationClass getConfiguration() {
        return configuration;
    }

    public Long getPutToFavorite() {
        return putToFavorite;
    }

    public Date getDateOfFavorite() {
        return dateOfFavorite;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setConfiguration(ConfigurationClass configuration) {
        this.configuration = configuration;
    }

    public void setPutToFavorite(Long putToFavorite) {
        this.putToFavorite = putToFavorite;
    }

    public void setDateOfFavorite(Date dateOfFavorite) {
        this.dateOfFavorite = dateOfFavorite;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void incrementPutToFavorite() {
    	this.putToFavorite += 1L;
    }
}
