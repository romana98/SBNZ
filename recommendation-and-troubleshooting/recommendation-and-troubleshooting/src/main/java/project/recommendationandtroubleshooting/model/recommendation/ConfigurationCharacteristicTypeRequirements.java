package project.recommendationandtroubleshooting.model.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "characteristic_requirements")
public class ConfigurationCharacteristicTypeRequirements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usageId", referencedColumnName = "id")
    private ConfigurationCharacteristicType characteristic;

    @ElementCollection
    private Set<String> CPU = new HashSet<>();

    @ElementCollection
    private Set<String> GPU = new HashSet<>();

    @ElementCollection
    private Set<String> RAM = new HashSet<>();

    @ElementCollection
    private Set<String> OS = new HashSet<>();

    @ElementCollection
    private Set<String> PSU = new HashSet<>();

    @ElementCollection
    private Set<String> discType = new HashSet<>();

    @ElementCollection
    private Set<String> discSize = new HashSet<>();

    @ElementCollection
    private Set<String> motherboard = new HashSet<>();

    @ElementCollection
    private Set<String> screenSize = new HashSet<>();

    @ElementCollection
    private Set<String> screenResolution = new HashSet<>();

    @ElementCollection
    private Set<String> musicCard = new HashSet<>();

    @ElementCollection
    private Set<String> touchscreen = new HashSet<>();

    @ElementCollection
    private Set<String> microphone = new HashSet<>();

    @ElementCollection
    private Set<String> camera = new HashSet<>();

    @ElementCollection
    private Set<String> ergonomic = new HashSet<>();


    public Long getId() {
        return id;
    }

    public ConfigurationCharacteristicType getCharacteristic() {
        return characteristic;
    }

    public Set<String> getCPU() {
        return CPU;
    }

    public Set<String> getGPU() {
        return GPU;
    }

    public Set<String> getRAM() {
        return RAM;
    }

    public Set<String> getOS() {
        return OS;
    }

    public Set<String> getPSU() {
        return PSU;
    }

    public Set<String> getDiscType() {
        return discType;
    }

    public Set<String> getDiscSize() {
        return discSize;
    }

    public Set<String> getMotherboard() {
        return motherboard;
    }

    public Set<String> getScreenSize() {
        return screenSize;
    }

    public Set<String> getScreenResolution() {
        return screenResolution;
    }

    public Set<String> getMusicCard() {
        return musicCard;
    }

    public Set<String> getTouchscreen() {
        return touchscreen;
    }

    public Set<String> getMicrophone() {
        return microphone;
    }

    public Set<String> getCamera() {
        return camera;
    }

    public Set<String> getErgonomic() {
        return ergonomic;
    }
}
