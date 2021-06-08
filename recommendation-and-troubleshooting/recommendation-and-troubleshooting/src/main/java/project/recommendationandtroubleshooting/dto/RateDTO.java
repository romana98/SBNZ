package project.recommendationandtroubleshooting.dto;

public class RateDTO {
	private Double minRate;
	private Double maxRate;
	public RateDTO(Double minRate, Double maxRate) {
		super();
		this.minRate = minRate;
		this.maxRate = maxRate;
	}
	
	public RateDTO() {}

	public Double getMinRate() {
		return minRate;
	}
	
	

	@Override
	public String toString() {
		return "RateDTO [minRate=" + minRate + ", maxRate=" + maxRate + "]";
	}

	public void setMinRate(Double minRate) {
		this.minRate = minRate;
	}

	public Double getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(Double maxRate) {
		this.maxRate = maxRate;
	}
	
	

}
