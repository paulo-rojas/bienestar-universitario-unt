package pe.edu.unitru.bienestar.address.dto;

public record ProvinceDetailDto(
        Long id,
        String name,
        Long departmentId,
        String departmentName
) {
}
