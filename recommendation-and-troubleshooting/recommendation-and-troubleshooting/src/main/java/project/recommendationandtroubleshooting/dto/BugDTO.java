package project.recommendationandtroubleshooting.dto;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class BugDTO {

    private Integer id;

    @NotNull
    private List<DescriptionDTO> descriptionDTOList;

    @NotNull
    private Map<Integer, SolutionDTO> solutionDTOList;

    public BugDTO() {
    }

    public BugDTO(Integer id, List<DescriptionDTO> descriptionDTOList, Map<Integer, SolutionDTO> solutionDTOList) {
        this.id = id;
        this.descriptionDTOList = descriptionDTOList;
        this.solutionDTOList = solutionDTOList;
    }

    public BugDTO(List<DescriptionDTO> descriptionDTO, Map<Integer, SolutionDTO> solutionDTOList) {
        this.descriptionDTOList = descriptionDTO;
        this.solutionDTOList = solutionDTOList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<DescriptionDTO> getDescriptionDTO() {
        return descriptionDTOList;
    }

    public void setDescriptionDTO(List<DescriptionDTO> descriptionDTOList) {
        this.descriptionDTOList = descriptionDTOList;
    }

    public Map<Integer, SolutionDTO> getSolutionDTOList() {
        return solutionDTOList;
    }

    public void setSolutionDTOList(Map<Integer, SolutionDTO> solutionDTOList) {
        this.solutionDTOList = solutionDTOList;
    }
}
