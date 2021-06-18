package project.recommendationandtroubleshooting.dto;

public class UsersByRateDTO {
	
	private Long ones;
	private Long twos;
	private Long threes;
	private Long fours;
	private Long fives;
	
	public UsersByRateDTO() {}

	public UsersByRateDTO(Long ones, Long twos, Long threes, Long fours, Long fives) {
		super();
		this.ones = ones;
		this.twos = twos;
		this.threes = threes;
		this.fours = fours;
		this.fives = fives;
	}

	public Long getOnes() {
		return ones;
	}

	public void setOnes(Long ones) {
		this.ones = ones;
	}

	public Long getTwos() {
		return twos;
	}

	public void setTwos(Long twos) {
		this.twos = twos;
	}

	public Long getThrees() {
		return threes;
	}

	public void setThrees(Long threes) {
		this.threes = threes;
	}

	public Long getFours() {
		return fours;
	}

	public void setFours(Long fours) {
		this.fours = fours;
	}

	public Long getFives() {
		return fives;
	}

	public void setFives(Long fives) {
		this.fives = fives;
	}
	
	
}
