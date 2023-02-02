package com.medhead.api.unit;
import com.medhead.api.dto.Hospital;
import com.medhead.api.services.HospitalService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalServiceTest
{
    @Autowired
    private HospitalService hospitalService;
    private Hospital hospital;
    private Hospital hospitalTest;
    @Test
    void contextLoads()
    {
        assertThat(hospitalService).isNotNull();
    }

    @BeforeEach
    public void populateHospitalTable()
    {
        hospital = new Hospital("Hopital", "4.58;125,9", 10, 10);
        hospital.setId(1);
        hospitalService.add(hospital);
    }

    @Test
    public void testFindById_validateHospital()
    {
        hospitalTest = hospitalService.findHospitalById(1);
        assertThat(hospitalTest).isNotNull();
        assertThat(hospitalTest.getId()).isEqualTo(1).isEqualTo(hospital.getId());
        assertThat(hospitalTest.getName()).isEqualTo(hospital.getName());
        assertThat(hospitalTest.getBedsAvailable()).isEqualTo(hospital.getBedsAvailable());
        assertThat(hospitalTest.getPosition()).isEqualTo(hospital.getPosition());
        assertThat(hospitalTest.getTotalBeds()).isEqualTo(hospital.getTotalBeds());
        assertThat(hospitalTest.getBedsAvailable()).isEqualTo(hospital.getBedsAvailable());
    }

    @Test
    public void testFindById_ResourceNotFound()
    {
        assertThat(hospitalService.findHospitalById(-1)).isNull();

        if (hospitalService.findHospitalById(258) == null)
            assertThat(hospitalService.findHospitalById(258)).isNull();
    }

    @Test
    public void testFindAll_WithSizeEqualsTwo()
    {
        hospital = new Hospital("Hopital2", "4.58;1,9", 30, 18);
        hospital.setId(2);
        hospitalService.add(hospital);

        assertThat(hospitalService.findAll().size()).isEqualTo(2);
    }
    @Test
    public void testAdd_validateAddCorrectHospital()
    {
        assertThat(hospitalService.add(hospital)).isNotNull();
    }
    public void testAdd_validateAddingIncorrectHospital()
    {
        hospital.setPosition("1567894");
        assertThat(hospitalService.add(hospital)).isNull();

        hospital = new Hospital("Hopital", "4.58;125,9", 10, 10);
        hospital.setBedsAvailable(-3);
        assertThat(hospitalService.add(hospital)).isNull();

        hospital.setBedsAvailable(hospital.getTotalBeds() + 1);
        assertThat(hospitalService.add(hospital)).isNull();

        hospital = new Hospital("Hopital", "4.58;125,9", 10, 10);
        hospital.setTotalBeds(-1);
        assertThat(hospitalService.add(hospital)).isNull();

        hospital.setTotalBeds(hospital.getBedsAvailable() - 1);
        assertThat(hospitalService.add(hospital)).isNull();

        hospital = new Hospital("Hopital", "4.58;125,9", 10, 10);
        hospital.setName("");
        assertThat(hospitalService.add(hospital)).isNull();

    }
}
