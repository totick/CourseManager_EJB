/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author John
 */
@Entity
@Table(name = "absence")
public class Absence implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Temporal(TemporalType.DATE)
    @Column(name = "absenceDate", nullable = false)
    private Date absenceDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="studentId")
    private Student student;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="semesterId")
    private CourseSemester semester;

    public CourseSemester getSemester() {
        return semester;
    }

    public void setSemester(CourseSemester semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAbsenceDate() {
        return absenceDate;
    }

    public void setAbsenceDate(Date absenceDate) {
        this.absenceDate = absenceDate;
    }

}
