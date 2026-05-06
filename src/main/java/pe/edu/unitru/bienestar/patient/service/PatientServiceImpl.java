package pe.edu.unitru.bienestar.patient.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.edu.unitru.bienestar.patient.domain.PatientEntity;
import pe.edu.unitru.bienestar.patient.dto.PatientDto;
import pe.edu.unitru.bienestar.patient.dto.in.PatientCreateRequestDto;
import pe.edu.unitru.bienestar.patient.dto.in.PatientUpdateRequestDto;
import pe.edu.unitru.bienestar.patient.mapper.PatientMapper;
import pe.edu.unitru.bienestar.patient.repository.PatientRepository;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;
import pe.edu.unitru.bienestar.shared.exception.PatientNotFoundException;
import pe.edu.unitru.bienestar.shared.exception.PatientDuplicateDniException;
import pe.edu.unitru.bienestar.shared.service.PersonService;

@Slf4j
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
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    @Override
    public PatientDto createPatient(PatientCreateRequestDto dto) {
        if (patientRepository.existsByPersonDni(dto.patientInfo().dni())) {
            throw new PatientDuplicateDniException(dto.patientInfo().dni());
        }
        PersonEntity person = personService.findByDni(dto.patientInfo().dni());
        if (person == null) {
            person = personService.create(patientMapper.toPerson(dto.patientInfo()));
        }
        PatientEntity patient = patientRepository.save(patientMapper.toEntity(dto, person));
        log.info("Patient created with ID: {}", patient.getId());
        return patientMapper.toDto(patient);
    }

    @Override
    public PatientDto updatePatient(Long id, PatientUpdateRequestDto dto) {
        PatientEntity existing = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        PersonEntity person = existing.getPerson();
        person.setPaternalSurname(dto.patientInfo().paternalSurname());
        person.setMaternalSurname(dto.patientInfo().maternalSurname());
        person.setNames(dto.patientInfo().names());
        person.setDni(dto.patientInfo().dni());
        person.setBirthDate(dto.patientInfo().birthDate());
        person.setPhone(dto.patientInfo().phone());
        person.setGender(dto.patientInfo().gender());
        personService.update(person.getId(), person);
        
        existing.setPatientType(dto.patientType());
        
        return patientMapper.toDto(patientRepository.save(existing));
    }

    @Override
    public boolean deletePatientLogically(Long id) {
        if (patientRepository.existsById(id)) {
            PatientEntity patient = patientRepository.findById(id).orElse(null);
            if (patient != null) {
                patient.setActive(false);
                patientRepository.save(patient);
                log.info("Patient marked as inactive with ID: {}", id);
            }
            return true;
        }
        throw new PatientNotFoundException(id);
    }
}