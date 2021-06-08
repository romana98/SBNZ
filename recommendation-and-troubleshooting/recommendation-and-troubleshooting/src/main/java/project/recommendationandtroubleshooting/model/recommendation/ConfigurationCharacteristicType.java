package project.recommendationandtroubleshooting.model.recommendation;

import javax.persistence.*;

@Entity
@Table(name = "configuration_characteristics")
public class ConfigurationCharacteristicType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "characteristic", unique = true, nullable = false)
    private String characteristic;

    public Integer getId() {
        return id;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public ConfigurationCharacteristicType() {

    }

    public ConfigurationCharacteristicType(String characteristic) {
        this.characteristic = characteristic;
    }

    public ConfigurationCharacteristicType(Integer id, String characteristic) {
        this.id = id;
        this.characteristic = characteristic;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

	@Override
	public String toString() {
		return "ConfigurationCharacteristicType [id=" + id + ", characteristic=" + characteristic + "]";
	}
    
    
}
