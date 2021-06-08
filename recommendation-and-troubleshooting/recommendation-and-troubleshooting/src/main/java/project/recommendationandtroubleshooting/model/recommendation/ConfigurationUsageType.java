package project.recommendationandtroubleshooting.model.recommendation;

import javax.persistence.*;

@Entity
@Table(name = "configuration_usages")
public class ConfigurationUsageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "usage", unique = true, nullable = false)
    String usage;

    public ConfigurationUsageType() {

    }

    public ConfigurationUsageType(String usage) {
        this.usage = usage;
    }

    public ConfigurationUsageType(Integer id, String usage) {
        this.id = id;
        this.usage = usage;
    }

    public Integer getId() {
        return id;
    }

    public String getUsage() {
        return usage;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

	@Override
	public String toString() {
		return "ConfigurationUsageType [id=" + id + ", usage=" + usage + "]";
	}
    
    
}
