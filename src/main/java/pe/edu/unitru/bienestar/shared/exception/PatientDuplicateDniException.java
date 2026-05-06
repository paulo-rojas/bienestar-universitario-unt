package pe.edu.unitru.bienestar.shared.exception;

public class PatientDuplicateDniException extends RuntimeException {
    public PatientDuplicateDniException(String dni) {
        super("A patient with the same DNI already exists: " + dni);
    }

}
