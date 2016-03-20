/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author User
 */
@Entity
@Table(name = "semester")
@NamedQueries(
        {
            @NamedQuery(name = "getSemestersByCourseId", query = "SELECT cs from CourseSemester cs where cs.course.id = :courseId"),
            @NamedQuery(name = "getSemesterBySemesterCode", query = "select cs from CourseSemester cs where cs.semesterCode = :semesterCode")
        }
)
public class CourseSemester implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "semesterCode", nullable = false)
    private String semesterCode;
    @Temporal(TemporalType.DATE)
    @Column(name = "startDate", nullable = false)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToMany
    @JoinTable(
            name = "semesterteacher",
            joinColumns = {
                @JoinColumn(name = "semesterId", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "teacherId", referencedColumnName = "id")
            }
    )
    private List<Teacher> teachers;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "semesterstudent",
            joinColumns = {
                @JoinColumn(name = "semesterId", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "studentId", referencedColumnName = "id")
            }
    )
    private List<Student> students;
    

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
