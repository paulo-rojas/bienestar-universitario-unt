package pe.edu.unitru.bienestar.shared.dto;

import java.time.LocalDate;

public record PersonCreateRequestDto(
        String paternalSurname,
        String maternalSurname,
        String names,
        String dni,
        String gender,
        LocalDate birthDate,
        String phone
) {
}
