package com.funsoft.cabinet.repository;

import com.funsoft.cabinet.entities.Appointement;
import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointementRepository extends JpaRepository<Appointement,Long> {

    //select a from Appointement a where a.patient = :patient
    //select * from appointement a where a.patient_id =  patient.id
    public List<Appointement> findByPatient(Patient patient);


    public List<Appointement> findByDoctor(Doctor doctor);
}
