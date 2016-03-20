/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.*;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author John
 */
@Local
public interface IStudentService {

    List<Student> allStudents();

    void removeStudent(int id);

    Student saveStudent(Student student);
    
    Absence addAbsence(Absence absence);
    
    void removeAbsence(Absence abs);
    
    void removeAbsence(int studentId, int semesterId, Date absenceDate);
    
    List<Absence> getAbsenceList(int studentId, int semesterId);
}
