package project.recommendationandtroubleshooting.model.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
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
    
    @Column(name = "date_of_favorite", nullable = false)
    private Date dateOfFavorite;

    public Long getId() {
        return id;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Long getPutToFavorite() {
        return putToFavorite;
    }

	public Date getDateOfFavorite() {
		return dateOfFavorite;
	}
    
    
}
