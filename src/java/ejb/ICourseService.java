/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Course;
import java.util.List;

/**
 *
 * @author User
 */
public interface ICourseService {
    
    List<Course> allCourses();
            
    void removeCourse(int id);
    
    Course saveCourse(Course course);
}
