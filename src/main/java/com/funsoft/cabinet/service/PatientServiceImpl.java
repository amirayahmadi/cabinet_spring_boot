package com.funsoft.cabinet.service;

import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Patient;
import com.funsoft.cabinet.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(long id) {
        return patientRepository.findById(id).get();
    }

    @Override
    public List<Patient> getPatients() {
        return (List<Patient>)patientRepository.findAll();
    }

    @Override
    public Patient findByPhone(String phone) {
        return patientRepository.findByPhone(phone);
    }

    @Override
    public Patient update(long id, Patient patient) {
        Patient p = getPatient(id);
        p.setFirstname(patient.getFirstname());
        p.setLastname(patient.getLastname());
        p.setPhone(patient.getPhone());
        return patientRepository.save(p);
    }

    @Override
    public Map<String, Boolean> delete(long id) {
        patientRepository.deleteById(id);
        Map<String,Boolean> res = new HashMap<>();
        res.put("deleted",Boolean.TRUE);
        return res;
    }
}
