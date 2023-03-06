package com.funsoft.cabinet.entities;

//POJO => Entity

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@DATA inclut getter /setters/constructor with params and constructor without params
@Entity
@Table(name="doctors")
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotBlank(message = "firstname is mandatory")
    @Pattern(regexp="[a-zA-Z]+", message = "firstname should be contains only alphabettics")
    @Column(length = 30,nullable = false)
    private String firstname;

    @NotBlank(message = "lastname is mandatory")
    @Pattern(regexp="[a-zA-Z]+", message = "lastname should be contains only alphabettics")
    @Column(length = 30 , nullable = false)
    private String lastname;


    @NotBlank(message = "address is mandatory")
    @Column(nullable = false)
    private String address;

    @Email(message = "email invalid")
    @Column(nullable = false , unique = true)
    private String email;

    //@NotBlank(message = "specialite is mandatory")
    @Enumerated(EnumType.STRING)
    private Specialite specialite;

/*
    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List <Appointement> appointements;
*/
}
