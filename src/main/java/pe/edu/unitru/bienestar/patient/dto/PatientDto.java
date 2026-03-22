package pe.edu.unitru.bienestar.patient.dto;

import pe.edu.unitru.bienestar.patient.domain.PatientType;
import pe.edu.unitru.bienestar.shared.domain.Gender;

public record PatientDto(
    Long id,
    Long personId,
    String paternalSurname,
    String maternalSurname,
    String names,
    String dni,
    Gender gender,
    PatientType patientType
) {}
