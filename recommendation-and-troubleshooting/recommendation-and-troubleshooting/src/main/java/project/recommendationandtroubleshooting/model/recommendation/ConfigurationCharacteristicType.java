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
@Table(name = "configuration_characteristics")
public class ConfigurationCharacteristicType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "characteristic", unique = true, nullable = false)
    String characteristic;

    public Long getId() {
        return id;
    }

    public String getCharacteristic() {
        return characteristic;
    }
}
