package pe.edu.unitru.bienestar.address.dto.out;

public record DistrictDetailResponseDto(
        Long id,
        String name,
        Long provinceId,
        String provinceName,
        Long departmentId,
        String departmentName
) {
}
