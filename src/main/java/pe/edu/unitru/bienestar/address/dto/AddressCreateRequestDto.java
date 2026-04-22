package pe.edu.unitru.bienestar.address.dto;

public record AddressCreateRequestDto(
    Long personId,
    String streetType,
    String streetName,
    String number,
    String block,
    String lot,
    String urbanization,
    String reference,
    Long districtId
) {}
