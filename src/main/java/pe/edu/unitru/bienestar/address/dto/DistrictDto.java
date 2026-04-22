package pe.edu.unitru.bienestar.address.dto;

public record DistrictDto(
        Long id,
        String name,
        Long provinceId,
        Long departmentId
) {
}
