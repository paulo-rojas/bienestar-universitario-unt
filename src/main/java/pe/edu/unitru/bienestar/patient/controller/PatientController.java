package pe.edu.unitru.bienestar.patient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.unitru.bienestar.patient.dto.PatientDto;
import pe.edu.unitru.bienestar.address.dto.AddressCreateRequestDto;
import pe.edu.unitru.bienestar.address.service.AddressService;
import pe.edu.unitru.bienestar.patient.dto.PatientCreateRequestDto;
import pe.edu.unitru.bienestar.patient.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final AddressService addressService;

    public PatientController(PatientService patientService, AddressService addressService) {
        this.patientService = patientService;
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        PatientDto patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientCreateRequestDto dto) {
        return ResponseEntity.ok(patientService.createPatient(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientCreateRequestDto dto) {
        PatientDto updated = patientService.updatePatient(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (patientService.deletePatient(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ADDRESS ENDOPOINTS
    @GetMapping("/{id}/address")
    public ResponseEntity<?> getAddressByPatientId(@PathVariable Long id) {
        PatientDto patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(addressService.getByPersonId(patient.personId()));
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<?> createAddressByPatientId(@PathVariable Long id, @RequestBody AddressCreateRequestDto dto) {
        PatientDto patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(addressService.create(new AddressCreateRequestDto(
                    patient.personId(),
                    dto.streetType(),
                    dto.streetName(),
                    dto.number(),
                    dto.block(),
                    dto.lot(),
                    dto.urbanization(),
                    dto.reference(),
                    dto.districtId()
            )));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}/address")
    public ResponseEntity<?> updateAddressByPatientId(@PathVariable Long id, @RequestBody AddressCreateRequestDto dto) {
        PatientDto patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(addressService.update(patient.personId(), dto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
