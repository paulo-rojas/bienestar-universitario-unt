package pe.edu.unitru.bienestar.user.service;

import pe.edu.unitru.bienestar.user.dto.UserCreateRequestDto;
import pe.edu.unitru.bienestar.user.dto.UserDto;

public interface UserService {

    UserDto getUserById(Long id);

    UserDto createUser(UserCreateRequestDto dto);

    UserDto updateUser(Long id, UserCreateRequestDto dto);

    boolean deleteUser(Long id);

}
