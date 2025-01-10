package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {
    @Override
    public VisitEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String description) {
        // Pobierz pacjenta
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Pacjent nie znaleziony o ID: " + patientId);
        }
        // Pobierz doktora
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("Doktor nie znaleziony o ID: " + doctorId);
        }
        // Utwórz nową wizytę
        VisitEntity visit = new VisitEntity();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit.setTime(visitTime);
        visit.setDescription(description);
        // Inicjalizuj listę wizyt, jeśli jest pusta
        if (patient.getVisits() == null) {
            patient.setVisits(new ArrayList<>());
        }
        // Dodaj wizytę do pacjenta
        patient.getVisits().add(visit);
        // Zapisz zmiany kaskadowo poprzez merge pacjenta
        entityManager.merge(patient);
        return visit;
    }

    @Override
    public List<PatientEntity> findByAgeBiggerThan(int age) {
        TypedQuery<PatientEntity> query = entityManager.createQuery(
                "SELECT p FROM PatientEntity p WHERE p.age > :age",
                PatientEntity.class
        );
        query.setParameter("age", age);
        return query.getResultList();
    }

    @Override
    public List<PatientEntity> findByVisitsCountBiggerThan(long x) {
        TypedQuery<PatientEntity> query = entityManager.createQuery(
                "SELECT p FROM PatientEntity p WHERE SIZE(p.visits) > :x",
                PatientEntity.class
        );
        query.setParameter("x", x);
        return query.getResultList();
    }

    @Override
    public List<PatientEntity> findByLastName(String lastName) {
        TypedQuery<PatientEntity> query = entityManager.createQuery(
                "SELECT p FROM PatientEntity p WHERE p.lastName = :lastName",
                PatientEntity.class
        );
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
}