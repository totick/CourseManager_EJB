/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.*;

/**
 *
 * @author John
 */
@Entity
@NamedQuery(name = "allTeachers", query = "SELECT t FROM Teacher t")
public class Teacher extends Person {
   
    
    public Teacher(){}
    
    public boolean equals(Teacher other){
        return other.getId() == this.getId();
    }
    
    public String toString(){
        return this.getEmail();
    }
}
