package project.recommendationandtroubleshooting.mapper;

import project.recommendationandtroubleshooting.dto.UserDTO;
import project.recommendationandtroubleshooting.model.Admin;

public class AdminMapper implements MapperInterface<Admin, UserDTO> {

    @Override
    public Admin toEntity(UserDTO dto) {
        return new Admin(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail());
    }

    public Admin toEntityWithPass(UserDTO dto) {
        return new Admin(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail());
    }

    @Override
    public UserDTO toDto(Admin entity) {
        return new UserDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPassword(), entity.isVerified());
    }
}
