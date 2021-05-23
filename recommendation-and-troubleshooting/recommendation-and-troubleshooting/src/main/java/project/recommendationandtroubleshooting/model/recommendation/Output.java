package project.recommendationandtroubleshooting.model.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
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

    public ArrayList<Configuration> getRecommendedConfigurations() {
        return recommendedConfigurations;
    }

    public String getBudget() {
        return budget;
    }

    public String getType() {
        return type;
    }

    public String getUsage() {
        return usage;
    }

    public String getCharacteristics() {
        return characteristics;
    }
}
