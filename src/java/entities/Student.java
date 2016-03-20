/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import enums.Gender;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author John
 */
@Entity
@NamedQuery(name = "allStudents", query = "SELECT s FROM Student s")
public class Student extends Person {

    @Enumerated(EnumType.STRING) //Enumerated annotation is used for enums
    @Column(name = "gender")
    private Gender gender = Gender.MALE;
    
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "absence")
    @JoinColumn(name="studentId", referencedColumnName="id")
    private List<Absence> absenceList;


    public Student() {
    }    
    
    public List<Absence> getAbsenceList() {
        return absenceList;
    }

    public void setAbsenceList(List<Absence> absenceList) {
        this.absenceList = absenceList;
    }
   

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String toString(){
        return this.getEmail();
    }
}
