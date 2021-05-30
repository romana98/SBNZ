package project.recommendationandtroubleshooting.model.troubleshooting.cep;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
@Expires("7m")
public class TemperatureEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long currentTemperature;

    public TemperatureEvent() {
    }

    public TemperatureEvent(Long currentTemperature) {

        this.currentTemperature = currentTemperature;
    }

    public Long getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Long currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}
