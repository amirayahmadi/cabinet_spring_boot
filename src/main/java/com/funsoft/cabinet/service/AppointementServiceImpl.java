package com.funsoft.cabinet.service;

import com.funsoft.cabinet.dto.AppointementDto;
import com.funsoft.cabinet.entities.Appointement;
import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Patient;
import com.funsoft.cabinet.exception.ResourceNotFound;
import com.funsoft.cabinet.mapper.MapperAppointement;
import com.funsoft.cabinet.repository.AppointementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppointementServiceImpl implements AppointementService{
    @Autowired
    AppointementRepository appointementRepository;

    @Autowired
    MapperAppointement mapperAppointement;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @Override
    public Appointement createAppointement(AppointementDto appointementDto) throws ResourceNotFound {
        //convertir appointementDto to appointement
        Appointement appointement = mapperAppointement.dtoToEntity(appointementDto);
        return appointementRepository.save(appointement);
    }

    @Override
    public List<AppointementDto> getAppointements() {
        List<Appointement> appointments = appointementRepository.findAll();
        List<AppointementDto> appointmentDtos = mapperAppointement.entitiesToListDto(appointments);
        return appointmentDtos;
    }

    @Override
    public List<AppointementDto> getAppointementByDoctor(long idDoctor) throws ResourceNotFound {
        Doctor doctor= doctorService.getDoctor(idDoctor);
        List<Appointement> appointements = appointementRepository.findByDoctor(doctor);
        List<AppointementDto> appointementDtos = mapperAppointement.entitiesToListDto(appointements);
        return appointementDtos;
    }

    @Override
    public List<AppointementDto> getAppointementByPatient(long idPatient) {
        Patient patient = patientService.getPatient(idPatient);
        List<Appointement> appointements = appointementRepository.findByPatient(patient);
        List<AppointementDto> appointementDtos = mapperAppointement.entitiesToListDto(appointements);
        return appointementDtos;

    }

    @Override
    public AppointementDto getAppointement(long id) throws ResourceNotFound {
        Appointement appointement = appointementRepository.findById(id).orElseThrow(()-> new ResourceNotFound("appointement not found for id :"+id));
        return mapperAppointement.entityToDto(appointement);
    }

    @Override
    public Map<String,Boolean> deleteAppointement(long id) throws ResourceNotFound {
        Appointement appointement = appointementRepository.findById(id).orElseThrow(()-> new ResourceNotFound("appointement not found for id :"+id));
        appointementRepository.delete(appointement);
        Map<String,Boolean> res = new HashMap<>();
        res.put("deleted",Boolean.TRUE);
        return res;
    }

    @Override
    public Appointement updateAppointement(long id, AppointementDto appointementDto) throws ResourceNotFound {
        Appointement appointement = appointementRepository.findById(id).orElseThrow(()-> new ResourceNotFound("appointement not found for id :"+id));
        appointement.setDoctor(doctorService.getDoctor(appointementDto.getIdDoctor()));
        appointement.setPatient(patientService.getPatient((appointementDto.getIdPatient())));
        appointement.setRdvTime(appointementDto.getRdvTime());
        return appointementRepository.save(appointement);
    }
}
