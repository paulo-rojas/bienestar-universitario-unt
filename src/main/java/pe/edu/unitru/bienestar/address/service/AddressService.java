package pe.edu.unitru.bienestar.address.service;

import pe.edu.unitru.bienestar.address.dto.*;

import java.util.List;

public interface AddressService {

    AddressDto getByPersonId(Long personId);

    AddressDto create(AddressCreateRequestDto dto);

    AddressDto update(Long personId, AddressCreateRequestDto dto);

    void delete(Long personId);

    List<DepartmentDto> getDepartments();

    DepartmentDto getDepartmentById(Long departmentId);

    List<ProvinceSummaryDto> getProvincesByDepartamentId(Long departmentId);

    ProvinceDetailDto getProvinceById(Long provinceId);

    List<DistrictSummaryDto> getDistrictsByProvinceId(Long provinceId);

    DistrictDetailDto getDistrictById(Long districtId);

}
