package project.recommendationandtroubleshooting.model.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "configuration_usages")
public class ConfigurationUsageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "usage", unique = true, nullable = false)
    String usage;

    public Long getId() {
        return id;
    }

    public String getUsage() {
        return usage;
    }
}
