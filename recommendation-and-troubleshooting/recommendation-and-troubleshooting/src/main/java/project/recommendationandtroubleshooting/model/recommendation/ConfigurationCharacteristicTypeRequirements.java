package project.recommendationandtroubleshooting.model.recommendation;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Table(name = "configuration_characteristics_requirements")
public class ConfigurationCharacteristicTypeRequirements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "characteristic_id", referencedColumnName = "id")
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

    public ConfigurationCharacteristicTypeRequirements() {

    }

    public ConfigurationCharacteristicTypeRequirements(ConfigurationCharacteristicType characteristic, Set<String> CPU, Set<String> GPU, Set<String> RAM, Set<String> OS, Set<String> PSU, Set<String> discType, Set<String> discSize, Set<String> motherboard, Set<String> screenSize, Set<String> screenResolution, Set<String> musicCard, Set<String> touchscreen, Set<String> microphone, Set<String> camera, Set<String> ergonomic) {
        this.characteristic = characteristic;
        this.CPU = CPU;
        this.GPU = GPU;
        this.RAM = RAM;
        this.OS = OS;
        this.PSU = PSU;
        this.discType = discType;
        this.discSize = discSize;
        this.motherboard = motherboard;
        this.screenSize = screenSize;
        this.screenResolution = screenResolution;
        this.musicCard = musicCard;
        this.touchscreen = touchscreen;
        this.microphone = microphone;
        this.camera = camera;
        this.ergonomic = ergonomic;
    }

    public ConfigurationCharacteristicTypeRequirements(Integer id, ConfigurationCharacteristicType characteristic, Set<String> CPU, Set<String> GPU, Set<String> RAM, Set<String> OS, Set<String> PSU, Set<String> discType, Set<String> discSize, Set<String> motherboard, Set<String> screenSize, Set<String> screenResolution, Set<String> musicCard, Set<String> touchscreen, Set<String> microphone, Set<String> camera, Set<String> ergonomic) {
        this.id = id;
        this.characteristic = characteristic;
        this.CPU = CPU;
        this.GPU = GPU;
        this.RAM = RAM;
        this.OS = OS;
        this.PSU = PSU;
        this.discType = discType;
        this.discSize = discSize;
        this.motherboard = motherboard;
        this.screenSize = screenSize;
        this.screenResolution = screenResolution;
        this.musicCard = musicCard;
        this.touchscreen = touchscreen;
        this.microphone = microphone;
        this.camera = camera;
        this.ergonomic = ergonomic;
    }

    public Integer getId() {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCharacteristic(ConfigurationCharacteristicType characteristic) {
        this.characteristic = characteristic;
    }

    public void setCPU(Set<String> CPU) {
        this.CPU = CPU;
    }

    public void setGPU(Set<String> GPU) {
        this.GPU = GPU;
    }

    public void setRAM(Set<String> RAM) {
        this.RAM = RAM;
    }

    public void setOS(Set<String> OS) {
        this.OS = OS;
    }

    public void setPSU(Set<String> PSU) {
        this.PSU = PSU;
    }

    public void setDiscType(Set<String> discType) {
        this.discType = discType;
    }

    public void setDiscSize(Set<String> discSize) {
        this.discSize = discSize;
    }

    public void setMotherboard(Set<String> motherboard) {
        this.motherboard = motherboard;
    }

    public void setScreenSize(Set<String> screenSize) {
        this.screenSize = screenSize;
    }

    public void setScreenResolution(Set<String> screenResolution) {
        this.screenResolution = screenResolution;
    }

    public void setMusicCard(Set<String> musicCard) {
        this.musicCard = musicCard;
    }

    public void setTouchscreen(Set<String> touchscreen) {
        this.touchscreen = touchscreen;
    }

    public void setMicrophone(Set<String> microphone) {
        this.microphone = microphone;
    }

    public void setCamera(Set<String> camera) {
        this.camera = camera;
    }

    public void setErgonomic(Set<String> ergonomic) {
        this.ergonomic = ergonomic;
    }
}
