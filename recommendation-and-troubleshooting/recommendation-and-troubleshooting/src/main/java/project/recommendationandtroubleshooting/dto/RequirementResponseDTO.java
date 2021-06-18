package project.recommendationandtroubleshooting.dto;

public class RequirementResponseDTO {
	
	private Integer usageOrCharId;
	private String usageOrChar;
	private String attribute;
	private String value;
	private String type;
	
	public RequirementResponseDTO() {}

	public RequirementResponseDTO(Integer usageOrCharId, String usageOrChar, String attribute, String value, String type) {
		super();
		this.usageOrCharId = usageOrCharId;
		this.usageOrChar = usageOrChar;
		this.attribute = attribute;
		this.value = value;
		this.type = type;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getUsageOrCharId() {
		return usageOrCharId;
	}

	public void setUsageOrCharId(Integer usageOrCharId) {
		this.usageOrCharId = usageOrCharId;
	}

	public String getUsageOrChar() {
		return usageOrChar;
	}

	public void setUsageOrChar(String usageOrChar) {
		this.usageOrChar = usageOrChar;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
