package pe.edu.unitru.bienestar.shared.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import pe.edu.unitru.bienestar.address.domain.AddressEntity;


@Getter
@Setter
@Entity
@Table(name = "persons")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String paternalSurname;

    @Column(length = 100)
    private String maternalSurname;

    @Column(length = 100)
    private String names;

    @Column(unique = true, length = 8)
    private String dni;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Column(length = 12)
    private String phone;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    private AddressEntity address;

}
