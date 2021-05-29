package project.recommendationandtroubleshooting.model.recommendation;

import java.util.HashSet;
import java.util.Set;

public class Recommendations {

    private Set<Configuration> configurations = new HashSet<>();

    public Recommendations() {
    }

    public Recommendations(Set<Configuration> configurations) {
        this.configurations = configurations;
    }

    public void addConfiguration(Configuration configuration) {
        this.configurations.add(configuration);
    }

    public Set<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Set<Configuration> configurations) {
        this.configurations = configurations;
    }
}
