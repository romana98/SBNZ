package project.recommendationandtroubleshooting.model.recommendation;

import java.util.HashSet;
import java.util.Set;

public class SetString {
	
	private Set<String> setString = new HashSet<String>();
	
	public SetString() {}

	public SetString(Set<String> setString) {
		super();
		this.setString = setString;
	}

	public Set<String> getSetString() {
		return setString;
	}

	public void setSetString(Set<String> setString) {
		this.setString = setString;
	}
	
	

}
