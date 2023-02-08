package com.medhead.api.unit;
import com.medhead.api.dao.AppointmentRepository;
import com.medhead.api.dao.entity.AppointmentEntity;
import com.medhead.api.dto.Appointment;
import com.medhead.api.dto.Doctor;
import com.medhead.api.dto.Hospital;
import com.medhead.api.dto.Patient;
import com.medhead.api.mapper.AppointmentMapper;
import com.medhead.api.services.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentServiceTest
{
    @Autowired
    private AppointmentServiceImpl appointmentService;
    @Autowired
    private AppointmentMapper appointmentMapper;
    private Appointment appointment;
    @MockBean
    private AppointmentRepository mockAppointmentRepository;
    @BeforeEach
    public void createAppointment()
    {
        appointment = new Appointment();
        appointment.setId(1);

        Hospital hospital =  new Hospital("Hôpital Lariboisière", "2.341291,48.917518", 300, 250);
        appointment.setDoctor(new Doctor("John Kali", hospital));

        Patient patient = new Patient("Jay Quallin", 71, "-137,170");
        appointment.setPatient(patient);

        appointment.setDate(new Date());
    }
    @Test
    public void testFindById_validateAppointment()
    {
        when(this.mockAppointmentRepository.findById(anyLong()))
                .thenReturn(this.appointmentMapper.toEntity(this.appointment));
        Appointment appointmentTest = this.appointmentService.findAppointmentById(1);
        assertThat(appointmentTest).usingRecursiveComparison().isEqualTo(appointment);
    }

    @Test
    public void testFindById_validateDifferentAppointment()
    {
        when(this.mockAppointmentRepository.findById(anyLong()))
                .thenReturn(this.appointmentMapper.toEntity(this.appointment));
        Appointment appointmentTest = this.appointmentService.findAppointmentById(1);
        this.appointment.setId(2);
        assertThat(appointmentTest).usingRecursiveComparison().isNotEqualTo(this.appointment);
    }
    @Test
    public void testFindAll_WithTwoAppointments()
    {
        Appointment appointment2 = new Appointment();
        appointment2.setId(2);
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(this.appointment);
        appointments.add(appointment2);

        when(this.mockAppointmentRepository.findAll()).thenReturn(this.appointmentMapper.toEntityList(appointments));
        assertThat(this.appointmentService.findAll()).containsAll(appointments);
    }

    @Test
    public void testFindAll_Empty()
    {
        List<Appointment> appointments = new ArrayList<>();
        when(this.mockAppointmentRepository.findAll()).thenReturn(this.appointmentMapper.toEntityList(appointments));
        assertThat(this.appointmentService.findAll().size()).isEqualTo(0);
    }
    @Test
    public void testAdd_validateAddCorrectAppointment()
    {
        when(this.mockAppointmentRepository.save(Mockito.any(AppointmentEntity.class)))
                .thenReturn(this.appointmentMapper.toEntity(this.appointment));

        assertThat(this.appointmentService.add(this.appointment)).usingRecursiveComparison().isEqualTo(this.appointment);
    }

}
