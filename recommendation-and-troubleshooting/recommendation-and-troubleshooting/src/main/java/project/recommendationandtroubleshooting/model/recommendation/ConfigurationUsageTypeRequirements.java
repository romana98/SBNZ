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
@Getter
@Setter
@Table(name = "usage_requirements")
public class ConfigurationUsageTypeRequirements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usageId", referencedColumnName = "id")
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
    private Set<String> touchscreen = new HashSet<>();

    @ElementCollection
    private Set<String> microphone = new HashSet<>();

    @ElementCollection
    private Set<String> camera = new HashSet<>();

    @ElementCollection
    private Set<String> ergonomic = new HashSet<>();

}
