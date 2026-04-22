package pe.edu.unitru.bienestar.address.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.unitru.bienestar.address.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/departments")
    public ResponseEntity<?> getDepartments(){
        return ResponseEntity.ok(addressService.getDepartments());
    }

    @GetMapping("/provinces")
    public ResponseEntity<?> getProvincesByDepartment(@RequestParam Long departmentId){
        return ResponseEntity.ok(addressService.getProvincesByDepartamentId(departmentId));
    }

    @GetMapping("/districts")
    public ResponseEntity<?> getDistrictsByProvince(@RequestParam Long provinceId){
        return ResponseEntity.ok(addressService.getDistrictsByProvinceId(provinceId));
    }
}
