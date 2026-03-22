package pe.edu.unitru.bienestar.address.service;

import pe.edu.unitru.bienestar.address.dto.AddressCreateRequestDto;
import pe.edu.unitru.bienestar.address.dto.AddressDto;

public interface AddressService {

    AddressDto getByPersonId(Long personId);

    AddressDto create(AddressCreateRequestDto dto);

    AddressDto update(Long personId, AddressCreateRequestDto dto);

    void delete(Long personId);

}
