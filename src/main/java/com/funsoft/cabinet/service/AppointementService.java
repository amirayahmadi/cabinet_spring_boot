package com.funsoft.cabinet.service;

import com.funsoft.cabinet.dto.AppointementDto;
import com.funsoft.cabinet.entities.Appointement;
import com.funsoft.cabinet.exception.ResourceNotFound;

import java.util.List;
import java.util.Map;

public interface AppointementService {
    public Appointement createAppointement(AppointementDto appointementDto) throws ResourceNotFound;
    public List<AppointementDto> getAppointements();
    public List<AppointementDto> getAppointementByDoctor(long idDoctor) throws ResourceNotFound;
    public List<AppointementDto> getAppointementByPatient(long idPatient);
    public AppointementDto getAppointement(long id) throws ResourceNotFound;
    public Map<String,Boolean> deleteAppointement(long id) throws ResourceNotFound;
    public Appointement updateAppointement(long id ,AppointementDto appointementDto) throws ResourceNotFound;
}
