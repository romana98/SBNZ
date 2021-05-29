package project.recommendationandtroubleshooting.model.recommendation;

import java.util.ArrayList;

public class InputRequirements {

    private Budget budget;
    private ConfigurationUsageType configurationUsageType;
    private ArrayList<ConfigurationCharacteristicType> configurationCharacteristicType = new ArrayList<>();
    private Mobility mobility;

    public InputRequirements() {

    }

    public InputRequirements(Budget budget, ConfigurationUsageType configurationUsageType, ArrayList<ConfigurationCharacteristicType> configurationCharacteristicType, Mobility mobility) {
        this.budget = budget;
        this.configurationUsageType = configurationUsageType;
        this.configurationCharacteristicType = configurationCharacteristicType;
        this.mobility = mobility;
    }

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

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public void setConfigurationUsageType(ConfigurationUsageType configurationUsageType) {
        this.configurationUsageType = configurationUsageType;
    }

    public void setConfigurationCharacteristicType(ArrayList<ConfigurationCharacteristicType> configurationCharacteristicType) {
        this.configurationCharacteristicType = configurationCharacteristicType;
    }

    public void setMobility(Mobility mobility) {
        this.mobility = mobility;
    }
}
