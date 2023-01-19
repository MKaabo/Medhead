package com.medhead.api;

import com.medhead.api.dao.entity.DoctorEntity;
import com.medhead.api.dao.entity.HospitalEntity;
import com.medhead.api.dao.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	void testSimpleEmergency()
	{
		PatientEntity p = new PatientEntity();
		HospitalEntity hospitalA = new HospitalEntity();
		HospitalEntity hospitalB = new HospitalEntity();

		DoctorEntity doctorA = new DoctorEntity();
		DoctorEntity doctorB = new DoctorEntity();
		DoctorEntity doctorC = new DoctorEntity();
	}

}
