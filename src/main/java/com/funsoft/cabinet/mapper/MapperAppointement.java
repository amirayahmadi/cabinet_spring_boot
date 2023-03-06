package com.funsoft.cabinet.mapper;

import com.funsoft.cabinet.dto.AppointementDto;
import com.funsoft.cabinet.entities.Appointement;
import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Patient;
import com.funsoft.cabinet.exception.ResourceNotFound;
import com.funsoft.cabinet.service.DoctorService;
import com.funsoft.cabinet.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperAppointement  implements Serializable {
    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    //stocker dto in BD
    public Appointement dtoToEntity(AppointementDto appointementDto) throws ResourceNotFound {

        Appointement appointement = new Appointement();

        Doctor doctor = doctorService.getDoctor(appointementDto.getIdDoctor());
        appointement.setDoctor(doctor);

        Patient patient = patientService.getPatient(appointementDto.getIdPatient());
        appointement.setPatient(patient);

        appointement.setRdvTime(appointementDto.getRdvTime());
        return appointement;
    }


    //base to afficher dto(for utilisateur)
    public AppointementDto entityToDto(Appointement appointement){
        AppointementDto appointementDto = new AppointementDto();
        appointementDto.setId(appointement.getId());
        appointementDto.setIdDoctor(appointement.getDoctor().getId());
        appointementDto.setIdPatient(appointement.getPatient().getId());
        appointementDto.setRdvTime(appointement.getRdvTime());
        return appointementDto;
    }

    public List<AppointementDto> entitiesToListDto(List<Appointement> entities){
        return entities.stream().map(item ->entityToDto(item)).collect(Collectors.toList());
    }
}
