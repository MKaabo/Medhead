package com.medhead.api.dao.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class Entity {
    @Id
    private long id;

}
