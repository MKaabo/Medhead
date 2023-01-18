package com.medhead.api.dao.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "emergency")
public class Emergency
{
    @Id
    private long id;
    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Hospital hospital;

}
