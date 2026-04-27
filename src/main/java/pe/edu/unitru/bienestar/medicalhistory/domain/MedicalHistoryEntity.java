package pe.edu.unitru.bienestar.medicalhistory.domain;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.unitru.bienestar.patient.domain.PatientEntity;

import java.time.LocalDate;

@Entity
@Table(name = "medical_histories")
@Data
public class MedicalHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patient;

    @Column
    private String recordCode;

    @Column
    private LocalDate registerDate;
}
