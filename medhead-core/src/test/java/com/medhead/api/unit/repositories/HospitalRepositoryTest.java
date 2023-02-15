package com.medhead.api.unit.repositories;

import com.medhead.api.dao.HospitalRepository;
import com.medhead.api.dao.entity.HospitalEntity;
import com.medhead.api.dto.Hospital;
import com.medhead.api.mapper.HospitalMapper;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalRepositoryTest
{
    @Autowired
    private HospitalMapper hospitalMapper;

    @Autowired
    private HospitalRepository hospitalRepository;

    private HospitalEntity hospital;

    @BeforeEach
    public void createHospital()
    {
        Hospital hospitalDto = new Hospital("Hopital", "4.58,125,9", 10, 10);
        this.hospital = hospitalMapper.toEntity(hospitalDto);
        this.hospital.setId(1);
    }

    @Test
    void contextLoads()
    {
        assertThat(this.hospitalRepository).isNotNull();
    }

    @Test
    public void testFindById_ResourceNotFound()
    {
        assertThat(this.hospitalRepository.findHospitalById(-1)).isNull();
        assertThat(this.hospitalRepository.findHospitalById(99999999)).isNull();
    }

    @Nested
    @Tag("SaveIncorrectValues")
    @DisplayName("Try to save incorrect hospital")
    class SaveIncorrectHospitalTest
    {
        @Test
        public void testSave_incorrectCoordinatesHospital()
        {
            boolean exceptionThrown;
            try
            {
                hospital.setPosition("1567894");
                hospitalRepository.save(hospital);
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }
        @Test
        public void testSave_incorrectNameEmptyHospital()
        {
            boolean exceptionThrown;
            try
            {
                hospital.setName("");
                assertThat(hospitalRepository.save(hospital)).isNull();
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }
        @Test
        public void testSave_incorrectBedsAvailableHospital()
        {
            boolean exceptionThrown;
            try
            {
                hospital.setBedsAvailable(hospital.getTotalBeds() + 1);
                assertThat(hospitalRepository.save(hospital)).isNull();
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }
        @Test
        public void testSave_incorrectBedsAvailableNegativeHospital()
        {
            boolean exceptionThrown;
            try
            {
                hospital.setBedsAvailable(-3);
                hospitalRepository.save(hospital);
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }
        @Test
        public void testSave_incorrectTotalBedSHospital()
        {
            boolean exceptionThrown;
            try
            {
                hospital.setTotalBeds(hospital.getBedsAvailable() - 1);
                assertThat(hospitalRepository.save(hospital)).isNull();
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }
        @Test
        public void testSave_incorrectNegativeTotalBedsHospital()
        {
            boolean exceptionThrown;
            try
            {
                hospital.setTotalBeds(-1);
                assertThat(hospitalRepository.save(hospital)).isNull();
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }

    }
}
