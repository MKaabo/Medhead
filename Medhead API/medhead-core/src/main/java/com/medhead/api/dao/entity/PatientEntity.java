package com.medhead.api.dao.entity;

import com.medhead.api.dto.Specialization;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@jakarta.persistence.Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "patient")
public class PatientEntity extends Entity
{
    @NotEmpty
    private String name;
    private String info;
    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String position;

    private Specialization specialization;

    @AssertTrue
    private boolean isCoordinate()
    {
        return this.position.contains(";") && !this.position.contains(" ");
    }
    @AssertTrue
    private boolean isPhoneNumber()
    {
        if (this.phone != null)
        {
            char[] phoneToCharts = this.phone.toCharArray();
            for (Character c : phoneToCharts)
            {
                if (Character.isLetter(c))
                    return false;
            }
        }
        return true;
    }

}
