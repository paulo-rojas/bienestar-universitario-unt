package pe.edu.unitru.bienestar.consultation.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "medical_rests")
@Data
public class MedicalRestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column
    private Integer days;

    @Column
    private String reason;

    @Column
    private String diagnosis;

    @Column
    private String notes;

    @Column
    private String receipt;

    @Column
    private String number;

    @Enumerated(EnumType.STRING)
    @Column
    private MedicalRestStatus status;
}
