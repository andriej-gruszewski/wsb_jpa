package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Specialization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private AddressDao addressDao;


    @Test
    public void testAddVisitToPatient() {
        // given
        AddressEntity patientAddress = new AddressEntity();
        patientAddress.setAddressLine1("ul. Czereśnniowa");
        patientAddress.setCity("Bydgoszcz");
        patientAddress.setPostalCode("86-065");
        patientAddress = addressDao.save(patientAddress);

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Patryk");
        patient.setLastName("Nowak");
        patient.setPatientNumber("P2001");
        patient.setEmail("elo.tero@wp.pl");
        patient.setTelephoneNumber("666888777");
        patient.setDateOfBirth(LocalDate.of(2000, 4, 10));
        patient.setAge(24);
        patient.setAddress(patientAddress);
        patient.setVisits(new ArrayList<>());
        patient = patientDao.save(patient);

        AddressEntity doctorAddress = new AddressEntity();
        doctorAddress.setAddressLine1("ul. Czerkaska 10");
        doctorAddress.setPostalCode("86-065");
        doctorAddress.setCity("Bydgoszcz");
        doctorAddress = addressDao.save(doctorAddress);

        DoctorEntity doctor = new DoctorEntity();
        doctor.setSpecialization(Specialization.GP);
        doctor.setFirstName("Tymoteusz");
        doctor.setLastName("Len");
        doctor.setTelephoneNumber("123456789");
        doctor.setEmail("z.e@gmail.com");
        doctor.setDoctorNumber("Z2002");
        doctor.setAddress(doctorAddress);
        doctor = doctorDao.save(doctor);

        Long patientId = patient.getId();
        Long doctorId = doctor.getId();
        LocalDateTime visitTime = LocalDateTime.of(2024, 2, 2, 13, 15);
        String description = "Check";

        VisitEntity visit = patientDao.addVisitToPatient(patientId, doctorId, visitTime, description);

        assertThat(visit).isNotNull();
        assertThat(visit.getId()).isNotNull();

        assertThat(visit.getPatient().getId()).isEqualTo(patientId);
        assertThat(visit.getDoctor().getId()).isEqualTo(doctorId);
        assertThat(visit.getTime()).isEqualTo(visitTime);
        assertThat(visit.getDescription()).isEqualTo(description);

        PatientEntity updatedPatient = patientDao.findOne(patientId);
        assertThat(updatedPatient).isNotNull();
        assertThat(updatedPatient.getVisits()).isNotNull();
        assertThat(updatedPatient.getVisits()).hasSize(1);
    }

}