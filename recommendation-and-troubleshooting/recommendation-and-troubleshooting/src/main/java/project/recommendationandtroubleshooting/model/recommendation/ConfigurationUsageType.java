package project.recommendationandtroubleshooting.model.recommendation;

import javax.persistence.*;

@Entity
@Table(name = "configuration_usages")
public class ConfigurationUsageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "usage", unique = true, nullable = false)
    String usage;

    public ConfigurationUsageType() {

    }

    public ConfigurationUsageType(String usage) {
        this.usage = usage;
    }

    public ConfigurationUsageType(Long id, String usage) {
        this.id = id;
        this.usage = usage;
    }

    public Long getId() {
        return id;
    }

    public String getUsage() {
        return usage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
}
