package project.recommendationandtroubleshooting.model.troubleshooting.cep;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
@Expires("15h")
public class DiscEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long maxMemoryUsage;
    private Long currentMemoryUsage;

    public DiscEvent() {

    }

    public DiscEvent(Long maxMemoryUsage, Long currentMemoryUsage) {
        this.maxMemoryUsage = maxMemoryUsage;
        this.currentMemoryUsage = currentMemoryUsage;
    }

    public Long getMaxMemoryUsage() {
        return maxMemoryUsage;
    }

    public void setMaxMemoryUsage(Long maxMemoryUsage) {
        this.maxMemoryUsage = maxMemoryUsage;
    }

    public Long getCurrentMemoryUsage() {
        return currentMemoryUsage;
    }

    public void setCurrentMemoryUsage(Long currentMemoryUsage) {
        this.currentMemoryUsage = currentMemoryUsage;
    }
}
