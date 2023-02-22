package com.medhead.api.unit.repositories;

import com.medhead.api.dao.AppointmentRepository;
import com.medhead.api.dao.entity.AppointmentEntity;
import com.medhead.api.dto.Appointment;
import com.medhead.api.dto.Doctor;
import com.medhead.api.dto.Hospital;
import com.medhead.api.dto.Patient;
import com.medhead.api.mapper.AppointmentMapper;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.sql.Date;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class AppointmentRepositoryTest
{
    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private AppointmentEntity appointment;

    @BeforeEach
    public void createAppointment()
    {
        Hospital hospitalDto = new Hospital("Hopital", "4.58,125,9", 10, 10);
        Doctor doctorDto = new Doctor("John B.", hospitalDto);
        Patient patientDto = new Patient("Jean Cassel", 58, "10.2,147.5");

        Appointment appointmentDto = new Appointment(doctorDto, patientDto, new Date(System.currentTimeMillis()));
        this.appointment = appointmentMapper.toEntity(appointmentDto);
        this.appointment.setId(1);
    }

    @Test
    void contextLoads()
    {
        assertThat(this.appointmentRepository).isNotNull();
    }

    @Test
    public void testFindById_ResourceNotFound()
    {
        assertThat(this.appointmentRepository.findById(-1)).isNull();
        assertThat(this.appointmentRepository.findById(99999999)).isNull();
    }

    @Nested
    @Tag("SaveIncorrectValues")
    @DisplayName("Try to save incorrect appointment")
    class SaveIncorrectAppointmentTest
    {
        @Test
        public void testSave_nullPatientAppointment()
        {
            boolean exceptionThrown;
            try
            {
                appointment.setPatient(null);
                appointmentRepository.save(appointment);
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }
        @Test
        public void testSave_nullDoctorAppointment()
        {
            boolean exceptionThrown;
            try
            {
                appointment.setDoctor(null);
                assertThat(appointmentRepository.save(appointment)).isNull();
                exceptionThrown = false;
            }
            catch (TransactionSystemException exc)
            {
                exceptionThrown = true;
            }
            assertThat(exceptionThrown).isTrue();
        }
        @Test
        public void testSave_nullIncorrectDateAppointment()
        {
            boolean exceptionThrown;
            try
            {
                final String APPOINTMENT_TIME =  "2021-05-21";
                appointment.setDate(Date.valueOf(APPOINTMENT_TIME));
                assertThat(appointmentRepository.save(appointment)).isNull();
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
