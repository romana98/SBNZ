package project.recommendationandtroubleshooting.model.recommendation;

import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "configurations")
public class ConfigurationClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ConfigurationType type;

    @Column(name = "cpu", nullable = false)
    private String CPU; //intel i7

    @Column(name = "gpu", nullable = false)
    private String GPU;

    @Column(name = "ram", nullable = false)
    private String RAM;

    @Column(name = "os", nullable = false)
    private String OS;

    @Column(name = "psu", nullable = false)
    private String PSU; //napajanje

    @Column(name = "disc_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DiscType discType; //hdd ssd

    @Column(name = "disc_size", nullable = false)
    private String discSize;

    @Column(name = "motherboard", nullable = false)
    private String motherboard;

    @Column(name = "screen_size", nullable = false)
    private String screenSize;

    @Column(name = "screen_resolution", nullable = false)
    private String screenResolution;

    @Column(name = "music_card", nullable = false)
    private String musicCard;

    @Column(name = "touch_screen", nullable = false)
    private boolean touchscreen;

    @Column(name = "microphone", nullable = false)
    private boolean microphone;

    @Column(name = "camera", nullable = false)
    private boolean camera;

    @Column(name = "ergonomic", nullable = false)
    private boolean ergonomic;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ratingId")
    private Set<Rating> ratings;

    @Column(name = "considered", nullable = false)
    private Boolean considered = true;

    public ConfigurationClass() {

    }

    public ConfigurationClass(Long price, ConfigurationType type, String CPU, String GPU, String RAM, String OS, String PSU, DiscType discType, String discSize, String motherboard, String screenSize, String screenResolution, String musicCard, boolean touchscreen, boolean microphone, boolean camera, boolean ergonomic, Set<Rating> ratings, Boolean considered) {
        this.price = price;
        this.type = type;
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
        this.ratings = ratings;
        this.considered = considered;
    }

    public ConfigurationClass(Integer id, Long price, ConfigurationType type, String CPU, String GPU, String RAM, String OS, String PSU, DiscType discType, String discSize, String motherboard, String screenSize, String screenResolution, String musicCard, boolean touchscreen, boolean microphone, boolean camera, boolean ergonomic, Set<Rating> ratings, Boolean considered) {
        this.id = id;
        this.price = price;
        this.type = type;
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
        this.ratings = ratings;
        this.considered = considered;
    }

    public Integer getId() {
        return id;
    }

    public Long getPrice() {
        return price;
    }

    public ConfigurationType getType() {
        return type;
    }

    public String getCPU() {
        return CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public String getRAM() {
        return RAM;
    }

    public String getOS() {
        return OS;
    }

    public String getPSU() {
        return PSU;
    }

    public DiscType getDiscType() {
        return discType;
    }

    public String getDiscSize() {
        return discSize;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public String getMusicCard() {
        return musicCard;
    }

    public boolean isTouchscreen() {
        return touchscreen;
    }

    public boolean isMicrophone() {
        return microphone;
    }

    public boolean isCamera() {
        return camera;
    }

    public boolean isErgonomic() {
        return ergonomic;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public Boolean getConsidered() {
        return considered;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setType(ConfigurationType type) {
        this.type = type;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public void setPSU(String PSU) {
        this.PSU = PSU;
    }

    public void setDiscType(DiscType discType) {
        this.discType = discType;
    }

    public void setDiscSize(String discSize) {
        this.discSize = discSize;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public void setMusicCard(String musicCard) {
        this.musicCard = musicCard;
    }

    public void setTouchscreen(boolean touchscreen) {
        this.touchscreen = touchscreen;
    }

    public void setMicrophone(boolean microphone) {
        this.microphone = microphone;
    }

    public void setCamera(boolean camera) {
        this.camera = camera;
    }

    public void setErgonomic(boolean ergonomic) {
        this.ergonomic = ergonomic;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setConsidered(Boolean considered) {
        this.considered = considered;
    }

    @Override
    public String toString() {
        return "Configuration [id=" + id + ", price=" + price + ", type=" + type + ", CPU=" + CPU + ", GPU=" + GPU
                + ", RAM=" + RAM + ", OS=" + OS + ", PSU=" + PSU + ", discType=" + discType + ", discSize=" + discSize
                + ", motherboard=" + motherboard + ", screenSize=" + screenSize + ", screenResolution="
                + screenResolution + ", musicCard=" + musicCard + ", touchscreen=" + touchscreen + ", microphone="
                + microphone + ", camera=" + camera + ", ergonomic=" + ergonomic + "]";
    }

}
