package project.recommendationandtroubleshooting.mapper;

import project.recommendationandtroubleshooting.dto.SolutionDTO;
import project.recommendationandtroubleshooting.model.troubleshooting.Solution;

public class SolutionMapper implements MapperInterface<Solution, SolutionDTO> {

    @Override
    public Solution toEntity(SolutionDTO dto) {
        return new Solution(dto.getId(), dto.getSolution());
    }

    @Override
    public SolutionDTO toDto(Solution entity) {
        return new SolutionDTO(entity.getId(), entity.getSolution());
    }
}
