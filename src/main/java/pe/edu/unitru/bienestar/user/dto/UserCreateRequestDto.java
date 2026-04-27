package pe.edu.unitru.bienestar.user.dto;

import pe.edu.unitru.bienestar.shared.dto.PersonCreateRequestDto;
import pe.edu.unitru.bienestar.user.domain.Role;

public record UserCreateRequestDto(
        PersonCreateRequestDto person,
        String username,
        String password,
        String email,
        Role role
) {
}
