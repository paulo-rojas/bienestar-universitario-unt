package pe.edu.unitru.bienestar.address.dto.out;

public record AddressResponseDto(
    Long id,
    Long personId,
    String streetType,
    String streetName,
    String number,
    String block,
    String lot,
    String urbanization,
    String reference,
    Long districtId,
    String districtName,
    Long provinceId,
    String provinceName,
    Long departmentId,
    String departmentName
) {}
