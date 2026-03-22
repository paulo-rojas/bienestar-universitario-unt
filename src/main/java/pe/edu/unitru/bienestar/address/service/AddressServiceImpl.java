package pe.edu.unitru.bienestar.address.service;

import org.springframework.stereotype.Service;

import pe.edu.unitru.bienestar.address.domain.AddressEntity;
import pe.edu.unitru.bienestar.address.domain.DistrictEntity;
import pe.edu.unitru.bienestar.address.dto.AddressCreateRequestDto;
import pe.edu.unitru.bienestar.address.dto.AddressDto;
import pe.edu.unitru.bienestar.address.mapper.AddressMapper;
import pe.edu.unitru.bienestar.address.repository.AddressRepository;
import pe.edu.unitru.bienestar.address.repository.DistrictRepository;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;
import pe.edu.unitru.bienestar.shared.service.PersonService;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final DistrictRepository districtRepository;
    private final PersonService personService;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository,
                              DistrictRepository districtRepository,
                              PersonService personService,
                              AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.districtRepository = districtRepository;
        this.personService = personService;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDto getByPersonId(Long personId) {
        return addressRepository.findAll().stream()
                .filter(a -> a.getPerson().getId().equals(personId))
                .findFirst()
                .map(addressMapper::toDto)
                .orElse(null);
    }

    @Override
    public AddressDto create(AddressCreateRequestDto dto) {
        PersonEntity person = personService.findById(dto.personId());
        DistrictEntity district = districtRepository.findById(dto.districtId()).orElse(null);
        AddressEntity entity = addressMapper.toEntity(dto, person, district);
        return addressMapper.toDto(addressRepository.save(entity));
    }

    @Override
    public AddressDto update(Long personId, AddressCreateRequestDto dto) {
        return addressRepository.findAll().stream()
                .filter(a -> a.getPerson().getId().equals(personId))
                .findFirst()
                .map(existing -> {
                    DistrictEntity district = districtRepository.findById(dto.districtId()).orElse(null);
                    existing.setStreetType(dto.streetType());
                    existing.setStreetName(dto.streetName());
                    existing.setNumber(dto.number());
                    existing.setBlock(dto.block());
                    existing.setLot(dto.lot());
                    existing.setUrbanization(dto.urbanization());
                    existing.setReference(dto.reference());
                    existing.setDistrict(district);
                    existing.setProvince(district.getProvince());
                    existing.setDepartment(district.getProvince().getDepartment());
                    return addressMapper.toDto(addressRepository.save(existing));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long personId) {
        addressRepository.findAll().stream()
                .filter(a -> a.getPerson().getId().equals(personId))
                .findFirst()
                .ifPresent(a -> addressRepository.deleteById(a.getId()));
    }

}
