package project.recommendationandtroubleshooting.model.recommendation;

import javax.persistence.*;

@Entity
@Table(name = "configuration_characteristics")
public class ConfigurationCharacteristicType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "characteristic", unique = true, nullable = false)
    private String characteristic;

    public Long getId() {
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

    public ConfigurationCharacteristicType(Long id, String characteristic) {
        this.id = id;
        this.characteristic = characteristic;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }
}
