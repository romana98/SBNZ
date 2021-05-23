package project.recommendationandtroubleshooting.model.recommendation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class InputRecommendation {

    private Budget budget;
    private ConfigurationUsageType configurationUsageType;
    private ArrayList<ConfigurationCharacteristicType> configurationCharacteristicType = new ArrayList<ConfigurationCharacteristicType>();
    private Mobility mobility;

    public Budget getBudget() {
        return budget;
    }

    public ConfigurationUsageType getConfigurationUsageType() {
        return configurationUsageType;
    }

    public ArrayList<ConfigurationCharacteristicType> getConfigurationCharacteristicType() {
        return configurationCharacteristicType;
    }

    public Mobility getMobility() {
        return mobility;
    }
}
