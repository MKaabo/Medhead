package com.medhead.api.IT;

import com.medhead.api.dto.*;
import com.medhead.api.services.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@TestPropertySource("classpath:application-test.properties")
public class ScenarioEmergencySteps extends IntegrationTest
{
    @Autowired
    private PatientService patientService;
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private EmergencyService emergencyService;
    @Autowired
    private DoctorService doctorService;

    private Patient patient;
    private final long patientId = 1;
    private List <Hospital> hospitals;

    private final long emergencyId = 1;

    @Given("a patient in an emergency")
    public void a_patient_in_an_emergency()
    {
        this.patient = this.patientService.findPatientById(this.patientId);
    }

    @And("hospitals exist")
    public void hospitals_exist()
    {
        hospitals = hospitalService.findAll();

        List <Doctor> doctors = new ArrayList<>();
        Doctor doctor1 = new Doctor("John Kayri", hospitals.get(0));
        doctor1.setSpecialization(Specialization.CARDIOLOGY);
        doctor1.setId(5);
        doctors.add(doctor1);
        doctorService.add(doctor1);

        Doctor doctor2 = new Doctor("Valérie Trumy", hospitals.get(0));
        doctor2.setId(6);
        doctor2.setSpecialization(Specialization.NEUROPATHOLOGY);
        doctors.add(doctor2);
        doctorService.add(doctor2);

        hospitals = hospitalService.findAll();

        hospitals.get(1).setDoctors(doctors);
        assertThat(hospitals.size()).isGreaterThan(2);
    }

    @When("a patient is in a medical emergency")
    public void a_patient_is_in_a_medical_emergency()
    {
        emergencyService.add(patient, hospitals);
    }

    @Then("the correct hospital should be found")
    public void the_correct_hospital_should_be_found()
    {
        final int hospitalThatShouldBeFoundId = 2;
        Emergency emergency = new Emergency(this.patient);
        emergency.setId(0);
        emergency.setHospital(hospitalService.findHospitalById(hospitalThatShouldBeFoundId));
        assertThat(emergencyService.findEmergencyById(0)).usingRecursiveComparison()
                .isEqualTo(emergency);
    }

    @Then("it creates an emergency correctly")
    public void it_creates_an_emergency_correctly()
    {

    }

    @Then("we are able to modify the emergency")
    public void we_are_able_to_modify_the_emergency()
    {

    }

}
