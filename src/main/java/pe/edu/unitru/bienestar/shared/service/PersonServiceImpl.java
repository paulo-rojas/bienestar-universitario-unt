package pe.edu.unitru.bienestar.shared.service;

import org.springframework.stereotype.Service;

import pe.edu.unitru.bienestar.shared.domain.PersonEntity;
import pe.edu.unitru.bienestar.shared.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonEntity create(PersonEntity person) {
        return personRepository.save(person);
    }

    @Override
    public PersonEntity update(Long id, PersonEntity person) {
        person.setId(id);
        return personRepository.save(person);
    }

    @Override
    public PersonEntity findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return personRepository.existsById(id);
    }

}
