package project.recommendationandtroubleshooting.model.troubleshooting.cep;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
@Expires("5m")
public class TemperatureEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long maxTemperature;
    private Long currentTemperature;

    public TemperatureEvent() {
    }

    public TemperatureEvent(Long maxTemperature, Long currentTemperature) {
        this.maxTemperature = maxTemperature;
        this.currentTemperature = currentTemperature;
    }

    public Long getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Long maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Long getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Long currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}
