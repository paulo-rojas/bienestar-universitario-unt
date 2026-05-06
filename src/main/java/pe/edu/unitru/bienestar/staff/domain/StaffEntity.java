package pe.edu.unitru.bienestar.staff.domain;


import jakarta.persistence.*;
import lombok.Data;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;

@Data
@Entity
@Table(name = "staff")
public class StaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity person;

    @Enumerated(EnumType.STRING)
    @Column
    private StaffRole role;

    @Enumerated(EnumType.STRING)
    @Column
    private MedicalSpeciality speciality;

    @Column
    private String cmp;


}
