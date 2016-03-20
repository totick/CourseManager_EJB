/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import enums.Level;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author John
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "allCourses", query = "SELECT c FROM Course c")
})
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "code", nullable = false)
    @NotNull(message = "Course code can not be empty")
    private String code;
    @Column(name = "name", nullable = false)
    @NotNull(message = "Course name can not be empty")    
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "level", nullable = false)
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column(name = "language")
    private String language;
    @ManyToOne
    @JoinColumn(name = "courseResponsible")
    private Person courseResponsible;
    @OneToMany
    @JoinTable(name = "semester")
    @JoinColumn(name = "id")
    private Set<CourseSemester> semesters = new HashSet<>();

    public Set<CourseSemester> getSemesters() {
        return semesters;
    }

    public void setSemesters(Set<CourseSemester> semesters) {
        this.semesters = semesters;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Person getCourseResponsible() {
        return courseResponsible;
    }

    public void setCourseResponsible(Person courseResponsible) {
        this.courseResponsible = courseResponsible;
    }

}
