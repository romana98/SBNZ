package project.recommendationandtroubleshooting.model.troubleshooting.cep;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
@Expires("3m")
public class RAMEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long currentMemoryUsage;

    public RAMEvent() {
    }

    public RAMEvent(Long currentMemoryUsage) {

        this.currentMemoryUsage = currentMemoryUsage;
    }

    public Long getCurrentMemoryUsage() {
        return currentMemoryUsage;
    }

    public void setCurrentMemoryUsage(Long currentMemoryUsage) {
        this.currentMemoryUsage = currentMemoryUsage;
    }
}
