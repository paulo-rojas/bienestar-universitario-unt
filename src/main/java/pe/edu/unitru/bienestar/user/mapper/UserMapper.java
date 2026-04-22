package pe.edu.unitru.bienestar.user.mapper;

import org.springframework.stereotype.Component;

import pe.edu.unitru.bienestar.shared.domain.Gender;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;
import pe.edu.unitru.bienestar.shared.dto.PersonCreateRequestDto;
import pe.edu.unitru.bienestar.user.domain.UserEntity;
import pe.edu.unitru.bienestar.user.dto.UserCreateRequestDto;
import pe.edu.unitru.bienestar.user.dto.UserDto;

@Component
public class UserMapper {

    public UserDto toDto(UserEntity entity) {
        return new UserDto(
                entity.getId(),
                entity.getPerson().getId(),
                entity.getPerson().getPaternalSurname(),
                entity.getPerson().getMaternalSurname(),
                entity.getPerson().getNames(),
                entity.getPerson().getDni(),
                entity.getPerson().getGender(),
                entity.getPerson().getBirthDate(),
                entity.getPerson().getPhone(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getRole()
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

    public UserEntity toEntity(UserCreateRequestDto dto, PersonEntity person) {
        UserEntity entity = new UserEntity();
        entity.setPerson(person);
        entity.setUsername(dto.username());
        entity.setPassword(dto.password());
        entity.setEmail(dto.email());
        entity.setRole(dto.role());
        return entity;
    }
}