/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import helpers.SecurityHelper;
import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.InheritanceType.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author John
 */
@Entity
@Table(name = "person")
@Inheritance(strategy = JOINED)
@NamedQuery(name = "verifyPerson", query = "SELECT p FROM Person p WHERE p.email = :email and p.password = :password")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "firstName", nullable = false)
    @NotNull(message = "First name can not be empty")
    private String firstName;
    @Column(name = "lastName", nullable = false)
    @NotNull(message = "Last name can not be empty")
    private String lastName;
    @Column(name = "personnr", nullable = false, length = 12)
    @Size(max = 12)
    private String personnr;
    @Column(name = "email", nullable = false)
    @NotNull(message = "Email can not be empty")
    private String email;
    @Column(name = "password", nullable = false, length = 250)
    @NotNull(message = "Password can not be empty")
    private String password;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "phoneNumber")
    private String phoneNumber;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonnr() {
        return personnr;
    }

    public void setPersonnr(String personnr) {
        this.personnr = personnr;
    }

    public String toString() {
        return Integer.toString(this.id);
    }

    public boolean equals(Person other) {
        return other.getId() == this.getId();
    }
    
    @PrePersist
    private void hashPasswordForCreatedPerson(){
        String hashedPassword = new SecurityHelper().hashPassword(this.getPassword());
        this.setPassword(hashedPassword);
    }

}
