package project.recommendationandtroubleshooting.model.recommendation;

import java.util.HashSet;
import java.util.Set;

public class Recommendations {

    private Set<ConfigurationClass> configurations = new HashSet<>();

    public Recommendations() {
    }

    public Recommendations(Set<ConfigurationClass> configurations) {
        this.configurations = configurations;
    }

    public void addConfiguration(ConfigurationClass configuration) {
        this.configurations.add(configuration);
    }

    public Set<ConfigurationClass> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Set<ConfigurationClass> configurations) {
        this.configurations = configurations;
    }
}
