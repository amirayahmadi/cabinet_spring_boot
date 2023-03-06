package com.funsoft.cabinet.controller;

import com.funsoft.cabinet.dto.AppointementDto;
import com.funsoft.cabinet.entities.Appointement;
import com.funsoft.cabinet.exception.ResourceNotFound;
import com.funsoft.cabinet.service.AppointementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rdvs")
public class AppointementController {
    @Autowired
    AppointementService appointementService;

    @PostMapping
    public Appointement createAppointement (@RequestBody AppointementDto appointementDto) throws ResourceNotFound {
        return appointementService.createAppointement(appointementDto);
    }
    @GetMapping
    public List<AppointementDto> getAppointements(){
        return appointementService.getAppointements();
    }
    @GetMapping("/patient/find")
    public List <AppointementDto> findByPatient (@RequestParam("patientId") long idp){
        return appointementService.getAppointementByPatient(idp);
    }

    @GetMapping("/doctor/find")
    public List <AppointementDto> findByDoctor (@RequestParam("doctortId") long idd) throws ResourceNotFound {
        return appointementService.getAppointementByDoctor(idd);
    }
    @GetMapping("/{id}")
    public  AppointementDto getAppointement(@PathVariable("id") long id ) throws ResourceNotFound {
        return appointementService.getAppointement(id);
    }
    @DeleteMapping
    public Map<String,Boolean> deleteAppointement(@RequestParam("id") long id) throws ResourceNotFound {
        return appointementService.deleteAppointement(id);
    }
    @PutMapping
    public Appointement updateAppointement(@RequestParam("id") long id, @RequestBody AppointementDto appointementDto) throws ResourceNotFound {
       return appointementService.updateAppointement(id,appointementDto);
    }
}
