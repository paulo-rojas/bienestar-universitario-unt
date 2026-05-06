package pe.edu.unitru.bienestar.consultation.triage.domain;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.unitru.bienestar.staff.domain.StaffEntity;

@Table(name = "triages")
@Entity
@Data
public class TriageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double weight_kg;
    private Double height_m;
    private String bmi;
    private Double blood_pressure_mmhg;
    private Double heart_rate_bpm;
    private Double spo2_pct;
    private Double temperatura_c;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private StaffEntity staff;

}
