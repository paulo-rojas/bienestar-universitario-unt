package pe.edu.unitru.bienestar.patient.service;

import pe.edu.unitru.bienestar.patient.dto.PatientDto;
import pe.edu.unitru.bienestar.patient.dto.PatientCreateRequestDto;

public interface PatientService {

    PatientDto getPatientById(Long id);

    PatientDto createPatient(PatientCreateRequestDto dto);

    PatientDto updatePatient(Long id, PatientCreateRequestDto dto);

    boolean deletePatient(Long id);

}
