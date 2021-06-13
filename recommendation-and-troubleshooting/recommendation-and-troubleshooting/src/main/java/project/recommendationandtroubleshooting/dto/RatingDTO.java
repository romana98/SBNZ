package project.recommendationandtroubleshooting.dto;

public class RatingDTO {

	private int configId;
	private double rate;
	
	public RatingDTO() {}

	public RatingDTO(int configId, double rate) {
		super();
		this.configId = configId;
		this.rate = rate;
	}

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
}
