package pe.edu.unitru.bienestar.address.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private PersonEntity person;

    @Column(nullable = false, length = 20)
    private String streetType;          // AVENIDA, CALLE, JIRON, PASAJE, etc.

    @Column(nullable = false, length = 150)
    private String streetName;          // nombre de la vía

    @Column(length = 10)
    private String number;              // número (si aplica)

    @Column(length = 10)
    private String block;               // manzana (si aplica)

    @Column(length = 10)
    private String lot;                 // lote (si aplica)

    @Column(length = 100)
    private String urbanization;        // urbanización o sector

    @Column(length = 255)
    private String reference;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private DistrictEntity district;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private ProvinceEntity province;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;
}
