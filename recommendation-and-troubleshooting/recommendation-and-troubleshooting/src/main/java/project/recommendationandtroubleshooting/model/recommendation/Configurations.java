package project.recommendationandtroubleshooting.model.recommendation;

import java.util.ArrayList;
import java.util.List;

public class Configurations {

    private List<ConfigurationClass> configurations = new ArrayList<>();

    public Configurations() {

    }

    public Configurations(List<ConfigurationClass> configurations) {
        this.configurations = configurations;
    }

    public List<ConfigurationClass> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<ConfigurationClass> configurations) {
        this.configurations = configurations;
    }

    public void addConfiguration(ConfigurationClass configuration) {
        this.configurations.add(configuration);
    }
}
