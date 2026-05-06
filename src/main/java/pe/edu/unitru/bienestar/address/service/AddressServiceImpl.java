package pe.edu.unitru.bienestar.address.service;

import org.springframework.stereotype.Service;

import pe.edu.unitru.bienestar.address.domain.AddressEntity;
import pe.edu.unitru.bienestar.address.domain.DistrictEntity;
import pe.edu.unitru.bienestar.address.dto.*;
import pe.edu.unitru.bienestar.address.dto.in.AddressCreateRequestDto;
import pe.edu.unitru.bienestar.address.dto.out.AddressResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.DepartmentResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.DistrictDetailResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.DistrictSummaryResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.ProvinceDetailResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.ProvinceSummaryResponseDto;
import pe.edu.unitru.bienestar.address.mapper.AddressMapper;
import pe.edu.unitru.bienestar.address.repository.AddressRepository;
import pe.edu.unitru.bienestar.address.repository.DepartmentRepository;
import pe.edu.unitru.bienestar.address.repository.DistrictRepository;
import pe.edu.unitru.bienestar.address.repository.ProvinceRepository;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;
import pe.edu.unitru.bienestar.shared.service.PersonService;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final DistrictRepository districtRepository;
    private final PersonService personService;
    private final AddressMapper addressMapper;
    private final DepartmentRepository departmentRepository;
    private final ProvinceRepository provinceRepository;

    public AddressServiceImpl(AddressRepository addressRepository,
                              DistrictRepository districtRepository,
                              PersonService personService,
                              AddressMapper addressMapper, DepartmentRepository departmentRepository, ProvinceRepository provinceRepository) {
        this.addressRepository = addressRepository;
        this.districtRepository = districtRepository;
        this.personService = personService;
        this.addressMapper = addressMapper;
        this.departmentRepository = departmentRepository;
        this.provinceRepository = provinceRepository;
    }

    @Override
    public AddressResponseDto getByPersonId(Long personId) {
        return addressRepository.findAll().stream()
                .filter(a -> a.getPerson().getId().equals(personId))
                .findFirst()
                .map(addressMapper::toDto)
                .orElse(null);
    }

    @Override
    public AddressResponseDto create(AddressCreateRequestDto dto) {
        PersonEntity person = personService.findById(dto.personId());
        DistrictEntity district = districtRepository.findById(dto.districtId()).orElse(null);
        AddressEntity entity = addressMapper.toEntity(dto, person, district);
        return addressMapper.toDto(addressRepository.save(entity));
    }

    @Override
    public AddressResponseDto update(Long personId, AddressCreateRequestDto dto) {
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

    @Override
    public List<DepartmentResponseDto> getDepartments() {
        return departmentRepository.findAll().stream().map(AddressMapper::deparmentToDto).toList();
    }

    @Override
    public DepartmentResponseDto getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .map(AddressMapper::deparmentToDto)
                .orElseThrow(() -> new RuntimeException("Department with id = " + departmentId + " not found"));
    }

    @Override
    public List<ProvinceSummaryResponseDto> getProvincesByDepartamentId(Long departmentId) {
        return provinceRepository.getAllByDepartmentId(departmentId).stream().map(AddressMapper::provinceToSummaryDto).toList();
    }

    @Override
    public ProvinceDetailResponseDto getProvinceById(Long provinceId) {
        return provinceRepository.findById(provinceId)
                .map(AddressMapper::provinceToDetailDto)
                .orElseThrow(() -> new RuntimeException("Province with id = " + provinceId + "not found"));
    }

    @Override
    public List<DistrictSummaryResponseDto> getDistrictsByProvinceId(Long provinceId) {
        return districtRepository.getAllByProvinceId(provinceId).stream().map(AddressMapper::districtToSummaryDto).toList();
    }

    @Override
    public DistrictDetailResponseDto getDistrictById(Long districtId) {
        return districtRepository.findById(districtId)
                .map(AddressMapper::districtToDetailDto)
                .orElseThrow(() -> new RuntimeException("Distrit with id = " + districtId + "not found"));
    }

}
