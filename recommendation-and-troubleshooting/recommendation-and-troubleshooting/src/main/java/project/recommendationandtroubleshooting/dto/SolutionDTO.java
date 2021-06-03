package project.recommendationandtroubleshooting.dto;

import javax.validation.constraints.NotBlank;

public class SolutionDTO {

    private Integer id;

    @NotBlank
    private String solution;

    public SolutionDTO() {
    }


    public SolutionDTO(String solution) {
        this.solution = solution;
    }

    public SolutionDTO(Integer id, String solution) {
        this.id = id;
        this.solution = solution;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
