package pe.edu.unitru.bienestar.user.service;

import org.springframework.stereotype.Service;

import pe.edu.unitru.bienestar.shared.domain.Gender;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;
import pe.edu.unitru.bienestar.shared.service.PersonService;
import pe.edu.unitru.bienestar.user.domain.UserEntity;
import pe.edu.unitru.bienestar.user.dto.UserCreateRequestDto;
import pe.edu.unitru.bienestar.user.dto.UserDto;
import pe.edu.unitru.bienestar.user.mapper.UserMapper;
import pe.edu.unitru.bienestar.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PersonService personService;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           PersonService personService,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.personService = personService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    @Override
    public UserDto createUser(UserCreateRequestDto dto) {

        PersonEntity person;
        if (personService.existsByDni(dto.person().dni())) {
            person = personService.findByDni(dto.person().dni());
        } else {
            person = personService.create(userMapper.toPerson(dto.person()));
        }

        UserEntity user = userRepository.save(userMapper.toEntity(dto, person));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserCreateRequestDto dto) {
        return userRepository.findById(id).map(existing -> {
            PersonEntity person = existing.getPerson();
            person.setPaternalSurname(dto.person().paternalSurname());
            person.setMaternalSurname(dto.person().maternalSurname());
            person.setNames(dto.person().names());
            person.setDni(dto.person().dni());
            person.setBirthDate(dto.person().birthDate());
            person.setPhone(dto.person().phone());
            person.setGender(dto.person().gender());
            personService.update(person.getId(), person);
            existing.setUsername(dto.username());
            existing.setPassword(dto.password());
            existing.setEmail(dto.email());
            existing.setRole(dto.role());
            return userMapper.toDto(userRepository.save(existing));
        }).orElse(null);
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
