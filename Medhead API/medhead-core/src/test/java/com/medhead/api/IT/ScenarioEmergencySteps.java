package com.medhead.api.IT;

import com.medhead.api.dto.*;
import com.medhead.api.services.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class ScenarioEmergencySteps
{
    @Autowired
    private PatientService patientService;
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private EmergencyService emergencyService;
    private Patient patient;
    private long patientId = 1;
    private List <Hospital> hospitals;

    private long emergencyId = 1;

    @Given("a patient exists")
    public void a_patient_exists()
    {
        this.patient = this.patientService.findPatientById(this.patientId);
    }

    @And("At least three hospitals exists hospital")
    public void at_least_three_hospitals_exists()
    {
        hospitals = hospitalService.findAll();
        assertThat(hospitals.size()).isGreaterThan(2);
    }

    @When("a patient is in an emergency")
    public void a_patient_is_in_an_emergency()
    {
        Emergency emergency = new Emergency(this.patient);
        emergency.setId(1);
        emergencyService.add(patient, hospitals);
    }

    @Then("it creates an emergency correctly")
    public void it_creates_an_emergency_correctly()
    {
        final int hospitalThatShouldBeFoundIndex = 0;
        Emergency emergency = new Emergency(this.patient);
        emergency.setId(1);
        emergency.setHospital(hospitalService.findHospitalById(hospitalThatShouldBeFoundIndex));
        assertThat(this.emergencyService.findEmergencyById(this.emergencyId)).usingRecursiveComparison()
                .isEqualTo(emergency);
    }

}
