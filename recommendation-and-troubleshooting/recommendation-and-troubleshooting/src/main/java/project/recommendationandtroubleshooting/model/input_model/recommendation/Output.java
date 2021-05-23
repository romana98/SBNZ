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
public class Output {


    private ArrayList<Configuration> recommendedConfigurations = new ArrayList<Configuration>();
    private String budget;
    private String type;
    private String usage;
    private String characteristics;
    
    public void addWinner(Configuration configuration) {
    	this.recommendedConfigurations.add(configuration);
    }
}
