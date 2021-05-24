package project.recommendationandtroubleshooting.model.recommendation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "configurations")
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private Boolean considered = true;

    /*private int score = 0;

    public void increaseScore(int score) {
        this.score += score;
    }*/


    public Long getId() {
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

	@Override
	public String toString() {
		return "Configuration [id=" + id + ", price=" + price + ", type=" + type + ", CPU=" + CPU + ", GPU=" + GPU
				+ ", RAM=" + RAM + ", OS=" + OS + ", PSU=" + PSU + ", discType=" + discType + ", discSize=" + discSize
				+ ", motherboard=" + motherboard + ", screenSize=" + screenSize + ", screenResolution="
				+ screenResolution + ", musicCard=" + musicCard + ", touchscreen=" + touchscreen + ", microphone="
				+ microphone + ", camera=" + camera + ", ergonomic=" + ergonomic + "]";
	}

    /*public int getScore() {
        return score;
    }*/
    
    
}
