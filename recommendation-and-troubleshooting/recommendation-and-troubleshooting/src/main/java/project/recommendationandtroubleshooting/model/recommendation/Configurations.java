package project.recommendationandtroubleshooting.model.recommendation;

import java.util.ArrayList;
import java.util.List;

public class Configurations {

    private List<Configuration> configurations = new ArrayList<>();

    public Configurations() {

    }

    public Configurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }

    public void addConfiguration(Configuration configuration) {
        this.configurations.add(configuration);
    }
}
