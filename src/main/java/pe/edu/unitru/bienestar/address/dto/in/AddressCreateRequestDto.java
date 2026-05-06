package pe.edu.unitru.bienestar.address.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressCreateRequestDto(
    @NotNull(message = "El ID de persona es requerido")
    Long personId,
    
    @NotBlank(message = "El tipo de vía es requerido")
    @Size(max = 20, message = "El tipo de vía no puede exceder 20 caracteres")
    String streetType,
    
    @NotBlank(message = "El nombre de la vía es requerido")
    @Size(max = 150, message = "El nombre de la vía no puede exceder 150 caracteres")
    String streetName,
    
    @Size(max = 10, message = "El número no puede exceder 10 caracteres")
    String number,
    
    @Size(max = 10, message = "La manzana no puede exceder 10 caracteres")
    String block,
    
    @Size(max = 10, message = "El lote no puede exceder 10 caracteres")
    String lot,
    
    @Size(max = 100, message = "La urbanización no puede exceder 100 caracteres")
    String urbanization,
    
    @Size(max = 255, message = "La referencia no puede exceder 255 caracteres")
    String reference,
    
    @NotNull(message = "El ID del distrito es requerido")
    Long districtId
) {}
