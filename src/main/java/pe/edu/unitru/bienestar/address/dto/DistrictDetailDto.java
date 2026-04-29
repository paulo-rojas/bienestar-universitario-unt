package pe.edu.unitru.bienestar.address.dto;

public record DistrictDetailDto(
        Long id,
        String name,
        Long provinceId,
        String provinceName,
        Long departmentId,
        String departmentName
) {
}
