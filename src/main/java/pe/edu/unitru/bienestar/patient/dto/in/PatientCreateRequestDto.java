package pe.edu.unitru.bienestar.patient.dto.in;

import pe.edu.unitru.bienestar.patient.domain.PatientType;
import pe.edu.unitru.bienestar.shared.dto.PersonCreateRequestDto;

public record PatientCreateRequestDto(
    PersonCreateRequestDto patientInfo,
    PatientType patientType
) {}
