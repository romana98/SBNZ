package project.recommendationandtroubleshooting.dto;

import java.util.Date;

public class IntervalDTO {
	
	private Date minDate;
	private Date maxDate;
	
	public IntervalDTO() {
	}

	public IntervalDTO(Date minDate, Date maxDate) {
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
	}

	
}
