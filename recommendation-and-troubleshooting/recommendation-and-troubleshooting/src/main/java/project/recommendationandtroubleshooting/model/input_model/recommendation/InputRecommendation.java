package project.recommendationandtroubleshooting.model.input_model.recommendation;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.recommendationandtroubleshooting.model.Configuration;

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
