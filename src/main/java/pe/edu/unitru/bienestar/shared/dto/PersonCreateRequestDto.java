package pe.edu.unitru.bienestar.shared.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import pe.edu.unitru.bienestar.shared.domain.Gender;

public record PersonCreateRequestDto(
        @NotBlank String paternalSurname,
        @NotBlank String maternalSurname,
        @NotBlank String names,
        @NotBlank @Pattern(regexp = "\\d{8}") String dni,
        @NotNull Gender gender,
        @NotNull @Past LocalDate birthDate, // yyyy-MM-dd
        String phone
) {
}
