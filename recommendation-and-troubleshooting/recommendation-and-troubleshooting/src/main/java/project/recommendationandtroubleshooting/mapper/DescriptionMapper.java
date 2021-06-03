package project.recommendationandtroubleshooting.mapper;

import project.recommendationandtroubleshooting.dto.DescriptionDTO;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;

public class DescriptionMapper implements MapperInterface<Description, DescriptionDTO> {

    @Override
    public Description toEntity(DescriptionDTO dto) {
        return new Description(dto.getId(), dto.getProblemDescription());
    }

    @Override
    public DescriptionDTO toDto(Description entity) {
        return new DescriptionDTO(entity.getId(), entity.getProblemDescription());
    }
}
