package com.jpacourse.service;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.AddressTO;
import com.jpacourse.mapper.AddressMapper;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.enums.Specialization;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

import com.jpacourse.mapper.PatientMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PatientDao patientDao;


    @Before
    public void setUp() {
        clearDatabase();
    }

    private void clearDatabase() {
        visitDao.deleteAll();
        patientDao.deleteAll();
        doctorDao.deleteAll();
        addressDao.deleteAll();
    }

    @Test
    public void testFindAllVisitsForPatient() {
        AddressEntity savedAddress = createAndSaveAddress();
        DoctorEntity doctor = createAndSaveDoctor(savedAddress);
        PatientTO savedPatientTO = createAndSavePatient(savedAddress);
        createAndSaveVisit(doctor, savedPatientTO);

        assertThat(patientService.findAllVisitsForPatient(savedPatientTO.getId())).hasSize(1);
    }

    @Test
    public void testShouldFindPatientById() {
        AddressEntity savedAddress = createAndSaveAddress();
        DoctorEntity doctor = createAndSaveDoctor(savedAddress);
        PatientTO savedPatientTO = createAndSavePatient(savedAddress);
        createAndSaveVisit(doctor, savedPatientTO);

        PatientTO found = patientService.findById(savedPatientTO.getId());

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(savedPatientTO.getId());
        assertThat(found.getFirstName()).isEqualTo("Piotr");
        assertThat(found.getLastName()).isEqualTo("Nowicki");
        assertThat(found.getPatientNumber()).isEqualTo("P123");
        assertThat(found.getAge()).isEqualTo(30);
        assertThat(found.getVisits()).hasSize(1);
        assertThat(found.getVisits().get(0).getDoctorFirstName()).isEqualTo("Katarzyna");
        assertThat(found.getVisits().get(0).getDoctorLastName()).isEqualTo("Kowalska");
        assertThat(found.getVisits().get(0).getTime()).isEqualTo(LocalDateTime.of(2024, 2, 1, 14, 0));
    }

    @Test
    public void testShouldDeletePatientAndHisVisitsButNotDoctors() {
        AddressEntity savedAddress = createAndSaveAddress();
        DoctorEntity doctor = createAndSaveDoctor(savedAddress);
        PatientTO savedPatientTO = createAndSavePatient(savedAddress);
        createAndSaveVisit(doctor, savedPatientTO);

        long doctorCountBefore = doctorDao.count();
        long visitCountBefore = visitDao.count();
        long patientCountBefore = patientDao.count();

        patientService.deletePatient(savedPatientTO.getId());

        assertThat(patientDao.count()).isEqualTo(patientCountBefore - 1);
        assertThat(visitDao.count()).isEqualTo(visitCountBefore - 1);
        assertThat(doctorDao.count()).isEqualTo(doctorCountBefore);
        assertThat(patientDao.findOne(savedPatientTO.getId())).isNull();
    }

    private void createAndSaveVisit(DoctorEntity doctor, PatientTO patient) {
        VisitEntity visit = new VisitEntity();
        visit.setDoctor(doctor);
        visit.setPatient(PatientMapper.mapToEntity(patient));
        visit.setDescription("Kontrolna wizyta");
        visit.setTime(LocalDateTime.of(2024, 2, 1, 14, 0));
        visitDao.save(visit);
    }


    private AddressEntity createAndSaveAddress() {
        AddressTO addressTO = new AddressTO();
        addressTO.setCity("Kraków");
        addressTO.setAddressLine1("ul. Główna 10");
        addressTO.setPostalCode("30-001");
        AddressEntity addressEntity = AddressMapper.mapToEntity(addressTO);
        return addressDao.save(addressEntity);
    }

    private DoctorEntity createAndSaveDoctor(AddressEntity address) {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Katarzyna");
        doctor.setLastName("Kowalska");
        doctor.setTelephoneNumber("567890123");
        doctor.setEmail("katarzyna.kowalska@example.com");
        doctor.setDoctorNumber("D123");
        doctor.setSpecialization(Specialization.DERMATOLOGIST);
        doctor.setAddress(address);
        return doctorDao.save(doctor);
    }

    private PatientTO createAndSavePatient(AddressEntity address) {
        AddressTO addressTO = AddressMapper.mapToTO(address);
        PatientTO patientTO = new PatientTO();
        patientTO.setFirstName("Piotr");
        patientTO.setLastName("Nowicki");
        patientTO.setTelephoneNumber("789456123");
        patientTO.setEmail("piotr.nowicki@example.com");
        patientTO.setPatientNumber("P123");
        patientTO.setDateOfBirth(LocalDate.of(1994, 5, 15));
        patientTO.setAge(30);
        patientTO.setAddress(addressTO);
        return patientService.savePatient(patientTO);
    }
}