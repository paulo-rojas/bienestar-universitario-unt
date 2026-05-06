package pe.edu.unitru.bienestar.patient.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity person;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PatientType patientType;

    @Column(length = 100)
    private String workArea;

    @Column(length = 100)
    private String program;

    @Column(length = 100)
    private String secureNumber;

    // CONTACTO
    @Column(length = 100)
    private String contactName;
    @Column(length = 12)
    private String contactPhone;
    @Column(length = 100)
    private String contactRelationship;

    private Boolean active = true;

}
