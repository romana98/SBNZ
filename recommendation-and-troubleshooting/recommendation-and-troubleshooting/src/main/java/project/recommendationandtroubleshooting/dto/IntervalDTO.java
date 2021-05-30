package project.recommendationandtroubleshooting.dto;

import java.util.Date;

public class IntervalDTO {
	
	private String minDate;
	private String maxDate;
	
	public IntervalDTO() {
	}

	public IntervalDTO(String minDate, String maxDate) {
		this.minDate = minDate;
		this.maxDate = maxDate;
	}

	/*public IntervalDTO(Date minDate, Date maxDate) {
		super();
		this.minDate = minDate;
		this.maxDate = maxDate;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}*/

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}
}
