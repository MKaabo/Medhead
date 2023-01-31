package com.medhead.api.dao.entity;

import com.medhead.api.dto.Specialization;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@jakarta.persistence.Entity
@NoArgsConstructor
@Data
@Table(name = "doctor")
public class DoctorEntity extends Entity {
    private String name;
    @Enumerated
    private Specialization specialization;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<AppointmentEntity> appointments;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    @NotEmpty
    private HospitalEntity hospital;
    @Column(name = "is_available")
    private boolean isAvailable;

}
