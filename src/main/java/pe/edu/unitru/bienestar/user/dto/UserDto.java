package pe.edu.unitru.bienestar.user.dto;

import java.time.LocalDate;

import pe.edu.unitru.bienestar.shared.domain.Gender;
import pe.edu.unitru.bienestar.user.domain.Role;

public record UserDto(
        Long id,
        Long personId,
        String paternalSurname,
        String maternalSurname,
        String names,
        String dni,
        Gender gender,
        LocalDate birthDate,
        String phone,
        String username,
        String email,
        Role role
) {}