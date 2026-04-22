package pe.edu.unitru.bienestar.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.unitru.bienestar.shared.domain.PersonEntity;

@Getter
@Setter
@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity person;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role role;

}
