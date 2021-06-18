package project.recommendationandtroubleshooting.model.recommendation;

import javax.persistence.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "configuration_usages_requirements")
public class ConfigurationUsageTypeRequirements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usage_id", referencedColumnName = "id")
    private ConfigurationUsageType usage;

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
    private Set<Boolean> touchscreen = new HashSet<>();

    @ElementCollection
    private Set<Boolean> microphone = new HashSet<>();

    @ElementCollection
    private Set<Boolean> camera = new HashSet<>();

    @ElementCollection
    private Set<Boolean> ergonomic = new HashSet<>();

    public ConfigurationUsageTypeRequirements() {

    }

    public ConfigurationUsageTypeRequirements(ConfigurationUsageType usage, Set<String> CPU, Set<String> GPU, Set<String> RAM, Set<String> OS, Set<String> PSU, Set<String> discType, Set<String> discSize, Set<String> motherboard, Set<String> screenSize, Set<String> screenResolution, Set<String> musicCard, Set<Boolean> touchscreen, Set<Boolean> microphone, Set<Boolean> camera, Set<Boolean> ergonomic) {
        this.usage = usage;
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

    public ConfigurationUsageTypeRequirements(Integer id, ConfigurationUsageType usage, Set<String> CPU, Set<String> GPU, Set<String> RAM, Set<String> OS, Set<String> PSU, Set<String> discType, Set<String> discSize, Set<String> motherboard, Set<String> screenSize, Set<String> screenResolution, Set<String> musicCard, Set<Boolean> touchscreen, Set<Boolean> microphone, Set<Boolean> camera, Set<Boolean> ergonomic) {
        this.id = id;
        this.usage = usage;
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

    public ConfigurationUsageType getUsage() {
        return usage;
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

    public Set<Boolean> getTouchscreen() {
        return touchscreen;
    }

    public Set<Boolean> getMicrophone() {
        return microphone;
    }

    public Set<Boolean> getCamera() {
        return camera;
    }

    public Set<Boolean> getErgonomic() {
        return ergonomic;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsage(ConfigurationUsageType usage) {
        this.usage = usage;
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

    public void setTouchscreen(Set<Boolean> touchscreen) {
        this.touchscreen = touchscreen;
    }

    public void setMicrophone(Set<Boolean> microphone) {
        this.microphone = microphone;
    }

    public void setCamera(Set<Boolean> camera) {
        this.camera = camera;
    }

    public void setErgonomic(Set<Boolean> ergonomic) {
        this.ergonomic = ergonomic;
    }
    
    public Map<String, Set<String>> getRequirements() {
        Map<String, Set<String>> attributes = new HashMap<>();
        attributes.put("CPU", this.CPU);
        attributes.put("GPU", this.GPU);
        attributes.put("RAM", this.RAM);
        attributes.put("OS", this.OS);
        attributes.put("PSU", this.PSU);
        attributes.put("discType", this.discType);
        attributes.put("discSize", this.discSize);
        attributes.put("motherboard", this.motherboard);
        attributes.put("screenSize", this.screenSize);
        attributes.put("screenResolution", this.screenResolution);
        attributes.put("musicCard", this.musicCard);
        return attributes;

    }
}
