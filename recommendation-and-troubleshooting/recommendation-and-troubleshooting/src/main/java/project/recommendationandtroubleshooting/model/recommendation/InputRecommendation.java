package project.recommendationandtroubleshooting.model.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InputRecommendation {

    private Budget budget;
    private ConfigurationUsageType configurationUsageType;
    private ConfigurationCharacteristicType configurationCharacteristicType;
    private Mobility mobility;
}
