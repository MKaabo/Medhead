package com.medhead.api.unit;
import com.medhead.api.dao.DoctorRepository;
import com.medhead.api.dao.EmergencyRepository;
import com.medhead.api.dao.entity.EmergencyEntity;
import com.medhead.api.dto.*;
import com.medhead.api.mapper.EmergencyMapper;
import com.medhead.api.services.EmergencyServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmergencyServiceTest
{
    @Autowired
    private EmergencyServiceImpl emergencyService;
    @Autowired
    private EmergencyMapper emergencyMapper;
    private List <Hospital> hospitals;
    private Emergency emergency;
    @MockBean
    private EmergencyRepository mockEmergencyRepository;
    @MockBean
    private DoctorRepository mockDoctorRepository;

    @BeforeEach
    public void createEmergency()
    {
        createHospitals();
        Patient patient = new Patient("Jean Cassel", 58, "5.435387,43.333622");
        patient.setId(1);

        this.emergency = new Emergency(patient);
        this.emergency.setId(1);
    }

    private void createHospitals()
    {
        hospitals = new ArrayList<>();
        hospitals.add(new Hospital("Hôpital Nord", "5.362163,43.379383", 150, 5));
        hospitals.add(new Hospital("Hôpital Montperrin", "5.438660,43.522411", 50, 3));
        hospitals.add(new Hospital("Hôpital Lariboisière", "2.341291,48.917518", 300, 250));
        hospitals.add(new Hospital("Hôpital Vaugirard", "2.280501199302833,48.86794770440761", 600, 286));
        hospitals.add(new Hospital("Centre Hospitalier de Tourcoing", "3.1572596483538806,50.805934725554344", 200, 110));
        hospitals.add(new Hospital("Centre Hospitalier de Dunkerque", "2.3827235478750013,51.10697104799068", 20, 5));
        hospitals.add(new Hospital("Hôpitaux universitaires de Strasbourg", "7.743594610409657,48.58344604062807", 180, 0));
        hospitals.add(new Hospital("Hôpital Edouard Herriot", "4.873966140508923,45.81929878885463", 1000, 120));
        hospitals.add(new Hospital("Centre hospitalier Métropole Savoie", "5.895694613481063,45.80781265776782", 180, 0));
        hospitals.add(new Hospital("Hôpital Rangueil", "1.4455293705867536,43.614261693278266", 1000, 120));
        hospitals.add(new Hospital("Centre Hospitalier Sèvre et Loire", "-1.4940893252308407,47.20258004614798", 500, 365));
        hospitals.add(new Hospital("U C Healthcare", "-104.90761951892537,39.62552712114013", 5000, 148));
    }

    @Test
    void contextLoads()
    {
        assertThat(this.emergencyService).isNotNull();
    }

    @Test
    public void testFindById_validateEmergency()
    {
        when(this.mockEmergencyRepository.findEmergencyById(anyLong()))
                .thenReturn(this.emergencyMapper.toEntity(this.emergency));
        Emergency emergencyTest = this.emergencyService.findEmergencyById(1);
        assertThat(emergencyTest).usingRecursiveComparison().isEqualTo(this.emergency);
    }

    @Test
    public void testFindById_validateDifferentEmergency()
    {
        when(this.mockEmergencyRepository.findEmergencyById(anyLong()))
                .thenReturn(this.emergencyMapper.toEntity(this.emergency));
        Emergency emergencyTest = emergencyService.findEmergencyById(1);
        this.emergency.setId(2);
        assertThat(emergencyTest).usingRecursiveComparison().isNotEqualTo(this.emergency);
    }
    @Test
    public void testFindAll_WithTwoEmergencies()
    {
        Hospital hospital = new Hospital("The Other Hospital", "-138,170", 150, 140);
        Patient patient = new Patient("Jay Quallin", 71, "-137,170");
        Emergency emergency2 = new Emergency(patient);
        emergency2.setHospital(hospital);
        emergency2.setId(2);

        List<Emergency> emergencies = new ArrayList<>();
        emergencies.add(this.emergency);
        emergencies.add(emergency2);

        when(this.mockEmergencyRepository.findAll()).thenReturn(this.emergencyMapper.toEntityList(emergencies));
        List<Emergency> emergenciesReturned = this.emergencyService.findAll();
        assertThat(emergenciesReturned).usingRecursiveComparison().isEqualTo(emergencies);
    }

    @Test
    public void testFindAll_Empty()
    {
        List<Emergency> emergencies = new ArrayList<>();
        when(this.mockEmergencyRepository.findAll()).thenReturn(this.emergencyMapper.toEntityList(emergencies));
        assertThat(this.emergencyService.findAll().size()).isEqualTo(0);
    }

    @Nested
    @Tag("AddEmergenciesAndFindHospital")
    @DisplayName("Test hospital finding algorithm")
    class TestHospitalFindingAlgorithm
    {
        @Test
        public void testAdd_validateAddCorrectEmergency()
        {
            when(mockEmergencyRepository.save(Mockito.any(EmergencyEntity.class)))
                    .thenReturn(emergencyMapper.toEntity(emergency));

            Patient patient = new Patient("Jean Cassel", 58, "5.435387,43.333622");
            patient.setId(1);

            List <Hospital> hospitalList = new ArrayList<> ();
            hospitalList.add(hospitals.get(0));

            assertThat(emergencyService.add(patient, hospitalList)).usingRecursiveComparison().isEqualTo(emergency);
        }

        @Test
        public void testFindClosestHospitalAlgorithm_UsingOnlyTravelDurations()
        {
            final int HOSPITAL_MARSEILLE_INDEX = 0;
            Hospital closestHospital = hospitals.get(HOSPITAL_MARSEILLE_INDEX);
            Patient patient = new Patient("Jean Cassel", 58, "5.435387,43.333622");
            patient.setId(1);
            assertThat(emergencyService.findClosestHospital(patient, hospitals)).usingRecursiveComparison().isEqualTo(closestHospital);
        }

        @Test
        public void testFindClosestHospitalAlgorithm_UsingOnlyTravelDurationsWithNulls()
        {
            final int HOSPITAL_US = 11;
            Hospital closestHospital = hospitals.get(HOSPITAL_US);
            Patient patient = new Patient("Kareem Nash", 42, "-104.89566353069789,39.609337164276006");
            patient.setId(1);
            assertThat(emergencyService.findClosestHospital(patient, hospitals)).usingRecursiveComparison().isEqualTo(closestHospital);
        }

        @Test
        public void testFindClosestHospitalAlgorithm_UsingTravelDurationsAndAvailableBeds()
        {
            final int HOSPITAL_MARSEILLE_INDEX = 0;
            final int HOSPITAL_AIX_INDEX = 1;
            Hospital closestHospital = hospitals.get(HOSPITAL_MARSEILLE_INDEX);
            closestHospital.setBedsAvailable(0);
            Hospital secondClosestHospital = hospitals.get(HOSPITAL_AIX_INDEX);

            Patient patient = new Patient("Jean Cassel", 58, "5.435387,43.333622");
            patient.setId(1);
            assertThat(emergencyService.findClosestHospital(patient, hospitals)).usingRecursiveComparison().isEqualTo(secondClosestHospital);
        }

        @Test
        public void testFindClosestHospitalAlgorithm_UsingTravelDurationsAndSpecialization()
        {
            final int HOSPITAL_VAUGIRARD_INDEX = 3;
            final int HOSPITAL_TOURCOING = 4;

            Hospital closestHospitalWithSpec = hospitals.get(HOSPITAL_VAUGIRARD_INDEX);
            Doctor doctor = new Doctor("John Thomas", closestHospitalWithSpec);
            doctor.setSpecialization(Specialization.IMMUNOLOGY);
            closestHospitalWithSpec.getDoctors().add(doctor);

            Hospital closestHospitalWithRightSpec = hospitals.get(HOSPITAL_TOURCOING);
            Doctor doctor1 = new Doctor("Estelle Valey", closestHospitalWithRightSpec);
            doctor1.setSpecialization(Specialization.CARDIOLOGY);
            closestHospitalWithRightSpec.getDoctors().add(doctor1);

            Patient patient = new Patient("Jean Cassel", 58, "5.435387,43.333622");
            patient.setId(1);
            patient.setSpecialization(Specialization.CARDIOLOGY);

            assertThat(emergencyService.findClosestHospital(patient, hospitals)).usingRecursiveComparison().isEqualTo(closestHospitalWithRightSpec);
        }

        @Test
        public void testFindClosestHospitalAlgorithm_UsingTravelDurationsAndSpecializationAndAvailableBeds()
        {
            final int HOSPITAL_MARSEILLE_INDEX = 0;
            final int HOSPITAL_VAUGIRARD_INDEX = 3;
            final int HOSPITAL_TOURCOING = 4;

            Hospital closestHospital = hospitals.get(HOSPITAL_MARSEILLE_INDEX);
            Doctor doctor = new Doctor("John Thomas", closestHospital);
            doctor.setSpecialization(Specialization.IMMUNOLOGY);
            closestHospital.getDoctors().add(doctor);

            Hospital closestHospitalWithRightSpec = hospitals.get(HOSPITAL_VAUGIRARD_INDEX);
            Doctor doctor1 = new Doctor("Estelle Valey", closestHospitalWithRightSpec);
            doctor1.setSpecialization(Specialization.CARDIOLOGY);
            closestHospitalWithRightSpec.getDoctors().add(doctor1);
            closestHospitalWithRightSpec.setBedsAvailable(0);

            Hospital closestHospitalWithRightSpecAndBeds = hospitals.get(HOSPITAL_TOURCOING);
            Doctor doctor2 = new Doctor("Mary Ulle", closestHospitalWithRightSpecAndBeds);
            doctor2.setSpecialization(Specialization.CARDIOLOGY);
            closestHospitalWithRightSpecAndBeds.getDoctors().add(doctor2);

            Patient patient = new Patient("Jean Cassel", 58, "5.435387,43.333622");
            patient.setId(1);
            patient.setSpecialization(Specialization.CARDIOLOGY);

            assertThat(emergencyService.findClosestHospital(patient, hospitals)).usingRecursiveComparison().isEqualTo(closestHospitalWithRightSpecAndBeds);
        }

        @Test
        public void testFindClosestHospitalAlgorithm_UsingTravelDurations_ToUnreachableArea()
        {
            Patient patient = new Patient("Noe Were", 45, "53.892278,-79.159623");
            patient.setId(1);
            assertThat(emergencyService.findClosestHospital(patient, hospitals)).isNull();
        }
    }
}
