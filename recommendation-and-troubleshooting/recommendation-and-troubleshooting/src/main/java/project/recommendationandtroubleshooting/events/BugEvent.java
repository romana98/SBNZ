package project.recommendationandtroubleshooting.events;

  
import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import project.recommendationandtroubleshooting.model.troubleshooting.Bug;


//primjer za event
@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("5m")
public class BugEvent implements Serializable {

	private static final long serialVersionUID = 1L;
    private Date executionTime;
    private Bug bug;
    
    public BugEvent() {
	}

	public BugEvent(Date executionTime, Bug bug) {
		this.executionTime = executionTime;
		this.bug = bug;
	}

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	public Bug getReview() {
		return bug;
	}

	public void setReview(Bug bug) {
		this.bug = bug;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
