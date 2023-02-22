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
import org.springframework.test.context.TestPropertySource;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
@TestPropertySource("classpath:application-test.properties")
public class ScenarioAppointmentSteps extends IntegrationTest
{
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;

    private final long appointmentId = 1;
    private Patient patient;
    private final long patientId = 1;
    private Doctor doctor;
    private final long doctorId = 1;

    final String APPOINTMENT_TIME =  "2023-09-01";

    @Given("a patient who needs an appointment")
    public void a_patient_who_needs_an_appointment()
    {
        this.patient = this.patientService.findPatientById(this.patientId);
    }

    @And("a doctor available")
    public void a_doctor_available()
    {
        this.doctor = this.doctorService.findDoctorById(this.doctorId);
        assertThat(this.doctor.getHospital()).isNotNull();
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

    @Then("we are able to modify the appointment")
    public void we_are_able_to_modify_the_appointment()
    {

    }

}
