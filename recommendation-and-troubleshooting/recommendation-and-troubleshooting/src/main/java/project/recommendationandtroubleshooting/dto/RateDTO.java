package project.recommendationandtroubleshooting.dto;

public class RateDTO {
	private double minRate;
	private double maxRate;
	public RateDTO(double minRate, double maxRate) {
		super();
		this.minRate = minRate;
		this.maxRate = maxRate;
	}
	
	public RateDTO() {}

	public double getMinRate() {
		return minRate;
	}
	
	

	@Override
	public String toString() {
		return "RateDTO [minRate=" + minRate + ", maxRate=" + maxRate + "]";
	}

	public void setMinRate(double minRate) {
		this.minRate = minRate;
	}

	public double getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(double maxRate) {
		this.maxRate = maxRate;
	}
	
	

}
