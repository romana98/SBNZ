package project.recommendationandtroubleshooting.model.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InputRecommendation {

    private Budget budget;
    private ConfigurationUsageType configurationUsageType;
    private ArrayList<ConfigurationCharacteristicType> configurationCharacteristicType = new ArrayList<ConfigurationCharacteristicType>();
    private Mobility mobility;
}
