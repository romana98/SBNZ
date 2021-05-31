package project.recommendationandtroubleshooting.mapper;

import project.recommendationandtroubleshooting.dto.UserDTO;
import project.recommendationandtroubleshooting.model.User;

public class UserMapper implements MapperInterface<User, UserDTO> {

    @Override
    public User toEntity(UserDTO dto) {
        return new User(dto.getId(), dto.getEmail(), dto.getUsername(), dto.getPassword());
    }

    @Override
    public UserDTO toDto(User entity) {
        return new UserDTO(entity.getId(), entity.getUsername(), entity.getEmail());
    }
}