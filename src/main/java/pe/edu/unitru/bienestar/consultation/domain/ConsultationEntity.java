package pe.edu.unitru.bienestar.consultation.domain;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.unitru.bienestar.consultation.triage.domain.TriageEntity;
import pe.edu.unitru.bienestar.staff.domain.StaffEntity;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "consultations")
public class ConsultationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // private MedicalHistoryEntity medicalHistory

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "triage_id", referencedColumnName = "id")
    private TriageEntity triage;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private StaffEntity staff;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_rest_id", referencedColumnName = "id")
    private MedicalRestEntity medicalRest;

    @Column
    private LocalDate date;

    @Column
    private String anamnesis;

    @Column
    private String physicalExaminations;

    @Column
    private String diagnosis;

    @Column
    private String treatments;

    @Column
    private String prescriptions;

    @Column
    private String chief_complaint; // motivo de consulta
}
