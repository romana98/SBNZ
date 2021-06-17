package project.recommendationandtroubleshooting.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;

public class ConfigurationResponseDTO implements Comparable<ConfigurationResponseDTO> {

	private Integer id;

    private Long price;

    private ConfigurationType type;

    private String CPU; 

    private String GPU;

    private String RAM;

    private String OS;

    private String PSU; 

    private DiscType discType; 

    private String discSize;

    private String motherboard;

    private String screenSize;

    private String screenResolution;

    private String musicCard;

    private boolean touchscreen;

    private boolean microphone;

    private boolean camera;

    private boolean ergonomic;
    
    private Double averageRating;
    
    private boolean favorite;
    
    public ConfigurationResponseDTO() {}
    
    public ConfigurationResponseDTO(ConfigurationClass c, Double average, boolean favorite) {
    	this.id = c.getId();
    	this.price = c.getPrice();
    	this.type = c.getType();
    	this.CPU = c.getCPU();
    	this.GPU = c.getGPU();
    	this.RAM = c.getRAM();
    	this.OS = c.getOS();
    	this.PSU = c.getPSU();
    	this.discType = c.getDiscType();
    	this.discSize = c.getDiscSize();
    	this.motherboard = c.getMotherboard();
    	this.screenSize = c.getScreenSize();
    	this.screenResolution = c.getScreenResolution();
    	this.musicCard = c.getMusicCard();
    	this.touchscreen = c.isTouchscreen();
    	this.microphone = c.isMicrophone();
    	this.camera = c.isCamera();
    	this.ergonomic = c.isErgonomic();
    	this.averageRating = average;
    	this.favorite = favorite;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public ConfigurationType getType() {
		return type;
	}

	public void setType(ConfigurationType type) {
		this.type = type;
	}

	public String getCPU() {
		return CPU;
	}

	public void setCPU(String cPU) {
		CPU = cPU;
	}

	public String getGPU() {
		return GPU;
	}

	public void setGPU(String gPU) {
		GPU = gPU;
	}

	public String getRAM() {
		return RAM;
	}

	public void setRAM(String rAM) {
		RAM = rAM;
	}

	public String getOS() {
		return OS;
	}

	public void setOS(String oS) {
		OS = oS;
	}

	public String getPSU() {
		return PSU;
	}

	public void setPSU(String pSU) {
		PSU = pSU;
	}

	public DiscType getDiscType() {
		return discType;
	}

	public void setDiscType(DiscType discType) {
		this.discType = discType;
	}

	public String getDiscSize() {
		return discSize;
	}

	public void setDiscSize(String discSize) {
		this.discSize = discSize;
	}

	public String getMotherboard() {
		return motherboard;
	}

	public void setMotherboard(String motherboard) {
		this.motherboard = motherboard;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getScreenResolution() {
		return screenResolution;
	}

	public void setScreenResolution(String screenResolution) {
		this.screenResolution = screenResolution;
	}

	public String getMusicCard() {
		return musicCard;
	}

	public void setMusicCard(String musicCard) {
		this.musicCard = musicCard;
	}

	public boolean isTouchscreen() {
		return touchscreen;
	}

	public void setTouchscreen(boolean touchscreen) {
		this.touchscreen = touchscreen;
	}

	public boolean isMicrophone() {
		return microphone;
	}

	public void setMicrophone(boolean microphone) {
		this.microphone = microphone;
	}

	public boolean isCamera() {
		return camera;
	}

	public void setCamera(boolean camera) {
		this.camera = camera;
	}

	public boolean isErgonomic() {
		return ergonomic;
	}

	public void setErgonomic(boolean ergonomic) {
		this.ergonomic = ergonomic;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
    
    
    @Override
    public int compareTo(ConfigurationResponseDTO c) {
      return getId().compareTo(c.getId());
    }
}
