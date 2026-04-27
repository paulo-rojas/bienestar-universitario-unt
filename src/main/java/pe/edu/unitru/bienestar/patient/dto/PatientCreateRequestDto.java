package pe.edu.unitru.bienestar.patient.dto;

import pe.edu.unitru.bienestar.patient.domain.PatientType;
import pe.edu.unitru.bienestar.shared.dto.PersonCreateRequestDto;

public record PatientCreateRequestDto(
    PersonCreateRequestDto person,
    PatientType patientType
) {}
