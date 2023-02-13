package com.medhead.api.IT;

import com.medhead.api.dto.Appointment;
import com.medhead.api.dto.Doctor;
import com.medhead.api.dto.Patient;
import com.medhead.api.services.AppointmentService;
import com.medhead.api.services.DoctorService;
import com.medhead.api.services.PatientService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class ScenarioAppointmentSteps
{
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;

    private long appointmentId = 1;
    private Patient patient;
    private long patientId = 1;
    private Doctor doctor;
    private long doctorId = 1;

    final String APPOINTMENT_TIME =  "2023-09-01";

    @Given("a patient exists")
    public void a_patient_exists()
    {
        this.patient = this.patientService.findPatientById(this.patientId);
    }

    @And("a doctor exists with an existing hospital")
    public void a_doctor_exists_with_an_existing_hospital()
    {
        this.doctor = this.doctorService.findDoctorById(this.doctorId);
        assertThat(this.doctor.getHospital() != null);
    }

    @When("a patient schedules an appointment")
    public void a_patient_schedules_an_appointment()
    {
        Appointment appointment = new Appointment(this.doctor, this.patient, Date.valueOf(APPOINTMENT_TIME));
        appointment.setId(1);
        this.appointmentService.add(appointment);
    }

    @Then("it creates an appointment correctly")
    public void it_creates_an_appointment_correctly()
    {

        Appointment appointment = new Appointment(this.doctor, this.patient, Date.valueOf(APPOINTMENT_TIME));
        appointment.setId(1);
        assertThat(this.appointmentService.findAppointmentById(this.appointmentId)).usingRecursiveComparison()
                .isEqualTo(appointment);
    }
}
