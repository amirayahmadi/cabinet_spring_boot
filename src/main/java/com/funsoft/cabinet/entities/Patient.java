package com.funsoft.cabinet.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private  String lastname;
    private String phone;


    //pour ignorer l'action de conversion de la liste appointement en format json
    @JsonIgnore
    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Appointement> appointements;
}
