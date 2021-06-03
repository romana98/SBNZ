package project.recommendationandtroubleshooting.mapper;

import project.recommendationandtroubleshooting.dto.BugDTO;
import project.recommendationandtroubleshooting.dto.DescriptionDTO;
import project.recommendationandtroubleshooting.dto.SolutionDTO;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;
import project.recommendationandtroubleshooting.model.troubleshooting.Solution;

import java.util.*;

public class BugMapper implements MapperInterface<Bug, BugDTO> {

    private final DescriptionMapper descriptionMapper;
    private final SolutionMapper solutionMapper;

    public BugMapper() {
        descriptionMapper = new DescriptionMapper();
        solutionMapper = new SolutionMapper();
    }


    @Override
    public Bug toEntity(BugDTO dto) {
        Set<Description> descriptionSet = new HashSet<>();
        Map<Integer, Solution> solutionMap = new HashMap<>();

        for (DescriptionDTO description : dto.getDescriptionDTO()) {
            descriptionSet.add(descriptionMapper.toEntity(description));
        }

        for (Map.Entry<Integer, SolutionDTO> solutionDTOEntry : dto.getSolutionDTOList().entrySet()) {
            solutionMap.put(solutionDTOEntry.getKey(), solutionMapper.toEntity(solutionDTOEntry.getValue()));
        }

        return new Bug(dto.getId(), descriptionSet, solutionMap);
    }

    @Override
    public BugDTO toDto(Bug entity) {
        List<DescriptionDTO> descriptionDTOS = new ArrayList<>();
        Map<Integer, SolutionDTO> solutionDTOMap = new HashMap<>();

        for (Description description : entity.getDescriptions()) {
            descriptionDTOS.add(descriptionMapper.toDto(description));
        }

        for (Map.Entry<Integer, Solution> solutionEntry : entity.getSolutions().entrySet()) {
            solutionDTOMap.put(solutionEntry.getKey(), solutionMapper.toDto(solutionEntry.getValue()));
        }

        return new BugDTO(entity.getId(), descriptionDTOS, solutionDTOMap);
    }
}
