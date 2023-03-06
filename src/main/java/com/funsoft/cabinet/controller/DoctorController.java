package com.funsoft.cabinet.controller;

import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Specialite;
import com.funsoft.cabinet.exception.ResourceNotFound;
import com.funsoft.cabinet.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctors")

public class DoctorController extends BasicController{
    @Autowired
    DoctorService doctorService;


    @GetMapping(value="/welcome")
    public String welcome(){
        return "welcome this my first methode get";
    }

    @PostMapping
    public Doctor createDoctor(@Valid @RequestBody Doctor doctor){
        return doctorService.save(doctor);
    }
    @GetMapping
    public List<Doctor> getDoctors(){
        return doctorService.getDoctors();
    }
    @GetMapping(value="/{id}")
    public Doctor getDoctor(@PathVariable("id") Long id) throws ResourceNotFound {
        return doctorService.getDoctor(id);

    }

    @GetMapping(value="/get")
    public Doctor getDoctorByParam(@RequestParam("doctorId") Long id) throws ResourceNotFound {
        return doctorService.getDoctor(id);
    }

    @DeleteMapping
    public Map<String,Boolean> delete(@RequestParam("id")Long id){
        return doctorService.deleteDoctor(id);
    }

    @PutMapping
    public Doctor updateDoctor(@Valid @RequestParam("id") Long id , @RequestBody Doctor doctor) throws ResourceNotFound {
        return doctorService.updateDoctor(id,doctor);
    }

    @GetMapping(value = "/find")
    public List<Doctor> getBySpecialite(@RequestParam("specialite")Specialite specialite){
        return doctorService.searchDoctorBySpecialite(specialite);
    }

    @GetMapping(value = "/find2")
    public List<Doctor> getBySpecialiteAndPseudo(@RequestParam("specialite,pseudo")Specialite specialite ,String pseudo){
        return doctorService.advancedSearch(specialite,pseudo+'%');
    }
}
