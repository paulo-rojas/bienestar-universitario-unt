package pe.edu.unitru.bienestar.shared.service;

import pe.edu.unitru.bienestar.shared.domain.PersonEntity;

public interface PersonService {

    PersonEntity create(PersonEntity person);

    PersonEntity update(Long id, PersonEntity person);

    PersonEntity findById(Long id);

    void deleteById(Long id);

    boolean existsById(Long id);

}
