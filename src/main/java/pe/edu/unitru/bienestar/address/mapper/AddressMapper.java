package pe.edu.unitru.bienestar.address.mapper;

import org.springframework.stereotype.Component;

import pe.edu.unitru.bienestar.address.domain.AddressEntity;
import pe.edu.unitru.bienestar.address.domain.DistrictEntity;
import pe.edu.unitru.bienestar.address.dto.AddressCreateRequestDto;
import pe.edu.unitru.bienestar.address.dto.AddressDto;
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
}
