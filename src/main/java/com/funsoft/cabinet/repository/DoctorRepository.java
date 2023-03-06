package com.funsoft.cabinet.repository;

import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Specialite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Long> {

    //select * from Doctor d  where d.specialite = :spec => JPQL
    //named query => la requette va executer a partir le nom de la methode
    public List<Doctor> findBySpecialite(Specialite specialite);

    public List<Doctor> findByFirstname(String firstname);

    //select * from Doctor d where d.firstname = :firstname or d.lastname=:lastname
    public List<Doctor> findByFirstnameOrLastname(String firstname,String lastname);

    @Query(value="SELECT d FROM Doctor d WHERE d.specialite= :spec AND d.firstname LIKE :pseudo OR d.lastname LIKE :pseudo")
    public List <Doctor> search(@Param("spec") Specialite specialite,@Param("pseudo") String pseudo);
}
