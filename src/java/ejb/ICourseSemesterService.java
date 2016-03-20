/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.CourseSemester;
import entities.Student;
import entities.Teacher;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface ICourseSemesterService {

    CourseSemester saveCourseSemester(CourseSemester semester);
    
    List<CourseSemester> getSemesters(int courseId);
    
    void removeSemester(int semesterId);
    
    List<Student> getUnsubscribedStudents(List<Student> subscribedStudents);
    
    List<Teacher> getUnsubscribedTeachers(List<Teacher> subscribedTeachers);
    
    CourseSemester subscribeStudents(CourseSemester semester, List<String> studentEmails);
   
    CourseSemester subscribeTeachers(CourseSemester semester, List<String> teacherEmails);
    
    CourseSemester getSemester(String semesterCode);

}
