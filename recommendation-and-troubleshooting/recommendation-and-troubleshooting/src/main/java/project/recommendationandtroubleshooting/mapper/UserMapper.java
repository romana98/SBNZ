package project.recommendationandtroubleshooting.mapper;

import project.recommendationandtroubleshooting.dto.UserDTO;
import project.recommendationandtroubleshooting.model.User;

public class UserMapper implements MapperInterface<User, UserDTO> {

    @Override
    public User toEntity(UserDTO dto) {
        return new User(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail());
    }

    public User toEntityWithPass(UserDTO dto) {
        return new User(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail());
    }

    @Override
    public UserDTO toDto(User entity) {
        return new UserDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.isVerified());
    }
}