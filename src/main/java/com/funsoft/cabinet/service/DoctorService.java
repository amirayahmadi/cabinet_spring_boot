package com.funsoft.cabinet.service;

import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Specialite;
import com.funsoft.cabinet.exception.ResourceNotFound;

import java.util.List;
import java.util.Map;

public interface DoctorService {
    public Doctor save(Doctor doctor);
    public Doctor getDoctor(Long id) throws ResourceNotFound;
    public List<Doctor> getDoctors();
    public Map<String,Boolean> deleteDoctor(Long id);
    public List<Doctor> searchDoctorByFirstname(String firstname);
    public List<Doctor> searchDoctorByFirstnameOrLastname(String name);
    public List<Doctor> searchDoctorBySpecialite(Specialite specialite);
    public List<Doctor> advancedSearch(Specialite specialite , String pseudo);
    public Doctor updateDoctor(long id ,Doctor doctor) throws ResourceNotFound;
}
