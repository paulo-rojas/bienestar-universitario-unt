package pe.edu.unitru.bienestar.address.mapper;

import org.springframework.stereotype.Component;

import pe.edu.unitru.bienestar.address.domain.AddressEntity;
import pe.edu.unitru.bienestar.address.domain.DepartmentEntity;
import pe.edu.unitru.bienestar.address.domain.DistrictEntity;
import pe.edu.unitru.bienestar.address.domain.ProvinceEntity;
import pe.edu.unitru.bienestar.address.dto.*;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;

@Component
public class AddressMapper {

    public AddressDto toDto(AddressEntity entity) {
        return new AddressDto(
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

    public static DepartmentDto deparmentToDto(DepartmentEntity entity){
        return new DepartmentDto(entity.getId(), entity.getName());
    }

    public static ProvinceDetailDto provinceToDetailDto(ProvinceEntity entity){
        return new ProvinceDetailDto(entity.getId(), entity.getName(), entity.getDepartment().getId(), entity.getDepartment().getName());
    }

    public static ProvinceSummaryDto provinceToSummaryDto(ProvinceEntity entity){
        return new ProvinceSummaryDto(entity.getId(), entity.getName());
    }

    public static DistrictDetailDto districtToDetailDto(DistrictEntity entity){
        return new DistrictDetailDto(
                entity.getId(),
                entity.getName(),
                entity.getProvince().getId(),
                entity.getProvince().getName(),
                entity.getDepartment().getId(),
                entity.getDepartment().getName());
    }

    public static DistrictSummaryDto districtToSummaryDto(DistrictEntity entity){
        return new DistrictSummaryDto(entity.getId(), entity.getName());
    }
}
