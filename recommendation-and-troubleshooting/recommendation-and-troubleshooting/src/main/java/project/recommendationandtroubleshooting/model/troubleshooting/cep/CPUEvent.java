package project.recommendationandtroubleshooting.model.troubleshooting.cep;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
@Expires("10s")
public class CPUEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long maxCapacityUsage;
    private Long currentCapacityUsage;

    public CPUEvent() {

    }

    public CPUEvent(Long maxCapacityUsage, Long currentCapacityUsage) {
        this.maxCapacityUsage = maxCapacityUsage;
        this.currentCapacityUsage = currentCapacityUsage;
    }

    public Long getMaxCapacityUsage() {
        return maxCapacityUsage;
    }

    public void setMaxCapacityUsage(Long maxCapacityUsage) {
        this.maxCapacityUsage = maxCapacityUsage;
    }

    public Long getCurrentCapacityUsage() {
        return currentCapacityUsage;
    }

    public void setCurrentCapacityUsage(Long currentCapacityUsage) {
        this.currentCapacityUsage = currentCapacityUsage;
    }
}
