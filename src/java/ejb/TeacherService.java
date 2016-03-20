/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Teacher;
import interceptors.LoggingInterceptor;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.*;

/**
 *
 * @author John
 */
@Stateless
@Interceptors(LoggingInterceptor.class)
public class TeacherService implements ITeacherService {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Teacher> allTeachers() {
        TypedQuery<Teacher> query = em.createNamedQuery("allTeachers", Teacher.class);
        List<Teacher> teachers = query.getResultList();
        return teachers;
    }
    
    /**
     * @param teacher
     *      The teacher to add or to merge
     * @return 
     *      The managed instance
     */
    @Override
    public Teacher saveTeacher(Teacher teacher){
        /**
         * Merge creates a new instance of your entity, copies the state from the supplied entity, and makes the new copy managed.
         * The instance you pass in will not be managed
         * (any changes you make will not be part of the transaction - unless you call merge again).
         * 
         * Persist takes an entity instance, adds it to the context and makes that instance managed 
         * (ie future updates to the entity will be tracked).
         */
        return em.merge(teacher);
    }

    @Override
    public Teacher getTeacherById(int id) {
        return em.find(Teacher.class, id);
    }
    
    @Override
    public void removeTeacher(int id){
        Teacher teacher = em.find(Teacher.class, id);
        //The argument must be a managed entity
        em.remove(teacher);
    }

}
