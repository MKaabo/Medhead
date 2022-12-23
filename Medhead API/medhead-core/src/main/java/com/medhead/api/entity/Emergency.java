package com.medhead.api.entity;

import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.Id;
import javax.persistence.*;

@Data
public class Emergency
{
    @Id
    // TODOAutoGenerate
    private long id;
    @OneToMany(mappedBy = "emergencies", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup("emergency")
    private Hospital hospital;

}
