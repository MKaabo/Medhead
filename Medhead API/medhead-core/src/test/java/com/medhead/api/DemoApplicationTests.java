package com.medhead.api;

import com.medhead.api.dao.entity.Doctor;
import com.medhead.api.dao.entity.Hospital;
import com.medhead.api.dao.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.Doc;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	void testSimpleEmergency()
	{
		Patient p = new Patient();
		Hospital hospitalA = new Hospital();
		Hospital hospitalB = new Hospital();

		Doctor doctorA = new Doctor();
		Doctor doctorB = new Doctor();
		Doctor doctorC = new Doctor();
	}

}
