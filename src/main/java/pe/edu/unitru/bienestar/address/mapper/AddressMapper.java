package pe.edu.unitru.bienestar.address.mapper;

import org.springframework.stereotype.Component;

import pe.edu.unitru.bienestar.address.domain.AddressEntity;
import pe.edu.unitru.bienestar.address.domain.DepartmentEntity;
import pe.edu.unitru.bienestar.address.domain.DistrictEntity;
import pe.edu.unitru.bienestar.address.domain.ProvinceEntity;
import pe.edu.unitru.bienestar.address.dto.*;
import pe.edu.unitru.bienestar.address.dto.in.AddressCreateRequestDto;
import pe.edu.unitru.bienestar.address.dto.out.AddressResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.DepartmentResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.DistrictDetailResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.DistrictSummaryResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.ProvinceDetailResponseDto;
import pe.edu.unitru.bienestar.address.dto.out.ProvinceSummaryResponseDto;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;

@Component
public class AddressMapper {

    public AddressResponseDto toDto(AddressEntity entity) {
        return new AddressResponseDto(
            entity.getId(),
            entity.getPerson().getId(),
            entity.getStreetType(),
            entity.getStreetName(),
            entity.getNumber(),
            entity.getBlock(),
            entity.getLot(),
            entity.getUrbanization(),
            entity.getReference(),
            entity.getDistrict().getId(),
            entity.getDistrict().getName(),
            entity.getProvince().getId(),
            entity.getProvince().getName(),
            entity.getDepartment().getId(),
            entity.getDepartment().getName()
        );
    }

    public AddressEntity toEntity(AddressCreateRequestDto dto, PersonEntity person, DistrictEntity district) {
        AddressEntity entity = new AddressEntity();
        entity.setPerson(person);
        entity.setStreetType(dto.streetType());
        entity.setStreetName(dto.streetName());
        entity.setNumber(dto.number());
        entity.setBlock(dto.block());
        entity.setLot(dto.lot());
        entity.setUrbanization(dto.urbanization());
        entity.setReference(dto.reference());
        entity.setDistrict(district);
        entity.setProvince(district.getProvince());
        entity.setDepartment(district.getProvince().getDepartment());
        return entity;
    }

    public static DepartmentResponseDto deparmentToDto(DepartmentEntity entity){
        return new DepartmentResponseDto(entity.getId(), entity.getName());
    }

    public static ProvinceDetailResponseDto provinceToDetailDto(ProvinceEntity entity){
        return new ProvinceDetailResponseDto(entity.getId(), entity.getName(), entity.getDepartment().getId(), entity.getDepartment().getName());
    }

    public static ProvinceSummaryResponseDto provinceToSummaryDto(ProvinceEntity entity){
        return new ProvinceSummaryResponseDto(entity.getId(), entity.getName());
    }

    public static DistrictDetailResponseDto districtToDetailDto(DistrictEntity entity){
        return new DistrictDetailResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getProvince().getId(),
                entity.getProvince().getName(),
                entity.getDepartment().getId(),
                entity.getDepartment().getName());
    }

    public static DistrictSummaryResponseDto districtToSummaryDto(DistrictEntity entity){
        return new DistrictSummaryResponseDto(entity.getId(), entity.getName());
    }
}
