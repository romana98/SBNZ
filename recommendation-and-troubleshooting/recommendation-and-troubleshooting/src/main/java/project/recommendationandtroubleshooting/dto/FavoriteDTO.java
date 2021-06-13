package project.recommendationandtroubleshooting.dto;

public class FavoriteDTO {
	private int configId;
	private boolean value;
	
	public FavoriteDTO() {}

	public FavoriteDTO(int configId, boolean value) {
		super();
		this.configId = configId;
		this.value = value;
	}

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
	
	

}
