package project.recommendationandtroubleshooting.dto;

import javax.validation.constraints.NotBlank;

public class DescriptionDTO {

    private Integer id;

    @NotBlank
    private String problemDescription;

    public DescriptionDTO() {

    }

    public DescriptionDTO(Integer id, String problemDescription) {
        this.id = id;
        this.problemDescription = problemDescription;
    }

    public DescriptionDTO(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }
}
