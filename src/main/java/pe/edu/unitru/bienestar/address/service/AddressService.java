package pe.edu.unitru.bienestar.address.service;

import pe.edu.unitru.bienestar.address.dto.*;
import pe.edu.unitru.bienestar.address.dto.in.AddressCreateRequestDto;
import pe.edu.unitru.bienestar.address.dto.out.AddressResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.DepartmentResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.DistrictDetailResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.DistrictSummaryResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.ProvinceDetailResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.ProvinceSummaryResponseDto;

import java.util.List;

public interface AddressService {

    AddressResponseDto getByPersonId(Long personId);

    AddressResponseDto create(AddressCreateRequestDto dto);

    AddressResponseDto update(Long personId, AddressCreateRequestDto dto);

    void delete(Long personId);

    List<DepartmentResponseDto> getDepartments();

    DepartmentResponseDto getDepartmentById(Long departmentId);

    List<ProvinceSummaryResponseDto> getProvincesByDepartamentId(Long departmentId);

    ProvinceDetailResponseDto getProvinceById(Long provinceId);

    List<DistrictSummaryResponseDto> getDistrictsByProvinceId(Long provinceId);

    DistrictDetailResponseDto getDistrictById(Long districtId);

}
