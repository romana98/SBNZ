package project.recommendationandtroubleshooting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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


}
