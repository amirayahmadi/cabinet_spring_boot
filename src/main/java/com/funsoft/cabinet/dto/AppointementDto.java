package com.funsoft.cabinet.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
public class AppointementDto implements Serializable {
    private long id ;
    private long idPatient;
    private long idDoctor;
    private Date rdvTime;
}
