package project.recommendationandtroubleshooting.dto;

import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;

public class AddConfigurationDTO {

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
    
    public AddConfigurationDTO() {
    }

	public AddConfigurationDTO(Long price, ConfigurationType type, String cPU, String gPU, String rAM, String oS,
			String pSU, DiscType discType, String discSize, String motherboard, String screenSize,
			String screenResolution, String musicCard, boolean touchscreen, boolean microphone, boolean camera,
			boolean ergonomic) {
		super();
		this.price = price;
		this.type = type;
		this.CPU = cPU;
		this.GPU = gPU;
		this.RAM = rAM;
		this.OS = oS;
		this.PSU = pSU;
		this.discType = discType;
		this.discSize = discSize;
		this.motherboard = motherboard;
		this.screenSize = screenSize;
		this.screenResolution = screenResolution;
		this.musicCard = musicCard;
		this.touchscreen = touchscreen;
		this.microphone = microphone;
		this.camera = camera;
		this.ergonomic = ergonomic;
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
	
	

    
}
