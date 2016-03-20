/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Course;
import java.util.List;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author John
 */
@Stateless
public class CourseService implements ICourseService {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Course> allCourses() {
        TypedQuery<Course> query = em.createNamedQuery("allCourses", Course.class);
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public void removeCourse(int id) {
        Course course = em.find(Course.class, id);
        em.remove(course);
    }

    @Override
    public Course saveCourse(Course course) {
        return em.merge(course);
    }

}
