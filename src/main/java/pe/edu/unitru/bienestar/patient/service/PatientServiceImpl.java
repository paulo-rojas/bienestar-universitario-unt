package pe.edu.unitru.bienestar.patient.service;

import org.springframework.stereotype.Service;

import pe.edu.unitru.bienestar.patient.domain.PatientEntity;
import pe.edu.unitru.bienestar.patient.dto.PatientDto;
import pe.edu.unitru.bienestar.patient.dto.PatientCreateRequestDto;
import pe.edu.unitru.bienestar.patient.mapper.PatientMapper;
import pe.edu.unitru.bienestar.patient.repository.PatientRepository;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;
import pe.edu.unitru.bienestar.shared.service.PersonService;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PersonService personService;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientRepository patientRepository,
                              PersonService personService,
                              PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.personService = personService;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientDto getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDto)
                .orElse(null);
    }

    @Override
    public PatientDto createPatient(PatientCreateRequestDto dto) {
        PersonEntity person = personService.create(patientMapper.toPerson(dto));
        PatientEntity patient = patientRepository.save(patientMapper.toEntity(dto, person));
        return patientMapper.toDto(patient);
    }

    @Override
    public PatientDto updatePatient(Long id, PatientCreateRequestDto dto) {
        return patientRepository.findById(id).map(existing -> {
            PersonEntity person = existing.getPerson();
            person.setPaternalSurname(dto.paternalSurname());
            person.setMaternalSurname(dto.maternalSurname());
            person.setNames(dto.names());
            person.setDni(dto.dni());
            person.setGender(dto.gender());
            personService.update(person.getId(), person);
            existing.setPatientType(dto.patientType());
            return patientMapper.toDto(patientRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
