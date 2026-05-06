package pe.edu.unitru.bienestar.address.dto.out;

public record ProvinceDetailResponseDto(
        Long id,
        String name,
        Long departmentId,
        String departmentName
) {
}
