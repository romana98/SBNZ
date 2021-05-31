package project.recommendationandtroubleshooting.mapper;

import project.recommendationandtroubleshooting.dto.UserDTO;
import project.recommendationandtroubleshooting.model.Admin;

public class AdminMapper implements MapperInterface<Admin, UserDTO> {

    @Override
    public Admin toEntity(UserDTO dto) {
        return new Admin(dto.getId(), dto.getEmail(), dto.getUsername(), dto.getPassword());
    }

    @Override
    public UserDTO toDto(Admin entity) {
        return new UserDTO(entity.getId(), entity.getUsername(), entity.getEmail());
    }
}
