/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Local;
import java.util.*;
import entities.*;

/**
 *
 * @author John
 */
@Local
public interface ITeacherService {
    
    List<Teacher> allTeachers();
    
    Teacher getTeacherById(int id);
    
    void removeTeacher(int id);
    
    Teacher saveTeacher(Teacher teacher);
    
}
