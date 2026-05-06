package pe.edu.unitru.bienestar.shared.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import pe.edu.unitru.bienestar.shared.exception.PatientNotFoundException;
import pe.edu.unitru.bienestar.shared.exception.PatientDuplicateDniException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlePatientNotFoundException(PatientNotFoundException ex) {

        log.warn("Patient not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(PatientDuplicateDniException.class)
    public ResponseEntity<Map<String,String>> handlePatientDuplicateDniException(PatientDuplicateDniException ex) {

        log.warn("Patient with duplicate DNI: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
    }
}
