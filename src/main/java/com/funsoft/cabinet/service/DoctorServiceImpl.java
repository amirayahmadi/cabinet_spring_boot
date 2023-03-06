package com.funsoft.cabinet.service;

import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Specialite;
import com.funsoft.cabinet.exception.ResourceNotFound;
import com.funsoft.cabinet.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorRepository doctorRepository;


    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctor(Long id) throws ResourceNotFound {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFound("doctor not found for id :"+id));
        return doctor;
    }

    @Override
    public List<Doctor> getDoctors() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    @Override
    public Map<String, Boolean> deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
        Map<String,Boolean> res = new HashMap<>();
        res.put("deleted",Boolean.TRUE);
        return res;
    }

    @Override
    public List<Doctor> searchDoctorByFirstname(String firstname) {
        return doctorRepository.findByFirstname(firstname);
    }

    @Override
    public List<Doctor> searchDoctorByFirstnameOrLastname(String name) {
        return doctorRepository.findByFirstnameOrLastname(name,name);
    }

    @Override
    public List<Doctor> searchDoctorBySpecialite(Specialite specialite) {
        return doctorRepository.findBySpecialite(specialite);
    }

    @Override
    public List<Doctor> advancedSearch(Specialite specialite, String pseudo) {
        return doctorRepository.search(specialite,pseudo);
    }

    @Override
    public Doctor updateDoctor(long id, Doctor doctor) throws ResourceNotFound{
        Doctor old = getDoctor(id);
        old.setFirstname(doctor.getFirstname());
        old.setLastname(doctor.getLastname());
        old.setAddress(doctor.getAddress());
        old.setEmail(doctor.getEmail());
        old.setSpecialite(doctor.getSpecialite());
        return doctorRepository.save(old);

    }
}
