package com.funsoft.cabinet.service;

import com.funsoft.cabinet.entities.Patient;

import java.util.List;
import java.util.Map;

public interface PatientService {
    public Patient save(Patient patient);
    public Patient getPatient(long id);
    public List<Patient> getPatients();
    public Patient findByPhone(String phone);
    public Patient update(long id,Patient patient);
    public Map<String,Boolean> delete(long id);

}
