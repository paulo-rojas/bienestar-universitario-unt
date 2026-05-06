package pe.edu.unitru.bienestar.patient.service;

import pe.edu.unitru.bienestar.patient.dto.PatientDto;
import pe.edu.unitru.bienestar.patient.dto.in.PatientCreateRequestDto;
import pe.edu.unitru.bienestar.patient.dto.in.PatientUpdateRequestDto;

public interface PatientService {

    PatientDto getPatientById(Long id);

    PatientDto createPatient(PatientCreateRequestDto dto);

    PatientDto updatePatient(Long id, PatientUpdateRequestDto dto);

    boolean deletePatientLogically(Long id);

}
