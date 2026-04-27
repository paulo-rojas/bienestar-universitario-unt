package pe.edu.unitru.bienestar.address.service;

import pe.edu.unitru.bienestar.address.dto.*;

import java.util.List;

public interface AddressService {

    AddressDto getByPersonId(Long personId);

    AddressDto create(AddressCreateRequestDto dto);

    AddressDto update(Long personId, AddressCreateRequestDto dto);

    void delete(Long personId);

    List<DepartmentDto> getDepartments();

    List<ProvinceDto> getProvincesByDepartamentId(Long departmentId);

    List<DistrictDto> getDistrictsByProvinceId(Long provinceId);

}
