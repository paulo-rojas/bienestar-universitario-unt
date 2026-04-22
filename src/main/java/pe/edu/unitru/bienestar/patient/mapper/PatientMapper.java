package pe.edu.unitru.bienestar.patient.mapper;

import org.springframework.stereotype.Component;

import pe.edu.unitru.bienestar.patient.domain.PatientEntity;
import pe.edu.unitru.bienestar.patient.dto.PatientCreateRequestDto;
import pe.edu.unitru.bienestar.patient.dto.PatientDto;
import pe.edu.unitru.bienestar.shared.domain.Gender;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;
import pe.edu.unitru.bienestar.shared.dto.PersonCreateRequestDto;

@Component
public class PatientMapper {

    public PatientDto toDto(PatientEntity entity) {
        return new PatientDto(
            entity.getId(),
            entity.getPerson().getId(),
            entity.getPerson().getPaternalSurname(),
            entity.getPerson().getMaternalSurname(),
            entity.getPerson().getNames(),
            entity.getPerson().getDni(),
            entity.getPerson().getGender(),
            entity.getPatientType()
        );
    }

    public PersonEntity toPerson(PersonCreateRequestDto dto) {
        PersonEntity person = new PersonEntity();
        person.setPaternalSurname(dto.paternalSurname());
        person.setMaternalSurname(dto.maternalSurname());
        person.setNames(dto.names());
        person.setDni(dto.dni());
        person.setBirthDate(dto.birthDate());
        person.setPhone(dto.phone());
        person.setGender(Gender.valueOf(dto.gender()));
        return person;
    }

    public PatientEntity toEntity(PatientCreateRequestDto dto, PersonEntity person) {
        PatientEntity entity = new PatientEntity();
        entity.setPerson(person);
        entity.setPatientType(dto.patientType());
        return entity;
    }
}
