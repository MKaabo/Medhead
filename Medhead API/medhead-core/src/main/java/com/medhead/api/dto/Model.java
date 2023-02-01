package com.medhead.api.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Model
{
    @Id
    protected long id;
}
