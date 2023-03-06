package com.funsoft.cabinet.repository;

import com.funsoft.cabinet.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    public Patient findByPhone(String phone);
}
