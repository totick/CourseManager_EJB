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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
@Stateless
public class CourseSemesterService implements ICourseSemesterService {

    @PersistenceContext
    EntityManager em;

    
    @Override
    public CourseSemester saveCourseSemester(CourseSemester semester) {
        CourseSemester savedSemester = em.merge(semester);
        return savedSemester;
    }

    @Override
    public List<CourseSemester> getSemesters(int courseId) {
        TypedQuery<CourseSemester> query = em.createNamedQuery("getSemestersByCourseId", CourseSemester.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }

    @Override
    public void removeSemester(int semesterId) {
        CourseSemester semester = em.find(CourseSemester.class, semesterId);
        em.remove(semester);
    }
    
    @Override
    public List<Student> getUnsubscribedStudents(List<Student> subscribedStudents){
        
        //JPA compares against the collection of values returned from the Student entities toString method
        //in this case I overrided the method returning the email of the student to use for comparison.
        Query query = em.createQuery("select s from Student s where s.email not in :subscribedStudents", Student.class);
        
        query.setParameter("subscribedStudents", subscribedStudents);
        List<Student> result = query.getResultList();
        return result;
    }

    @Override
    public List<Teacher> getUnsubscribedTeachers(List<Teacher> subscribedTeachers) {
        Query query = em.createQuery("select t from Teacher t where t.email not in :subscribedTeachers", Teacher.class);
        query.setParameter("subscribedTeachers", subscribedTeachers);
        List<Teacher> result = query.getResultList();
        return result;
    }

    @Override
    public CourseSemester subscribeStudents(CourseSemester semester, List<String> studentEmails) {
        Query query = em.createQuery("select s from Student s where s.email in :studentEmails", Student.class);
        query.setParameter("studentEmails", studentEmails);
        List<Student> result = query.getResultList();
        semester.getStudents().addAll(result);
        //No need to return the result. The same semester instance gets updated
        return em.merge(semester);
    }

    @Override
    public CourseSemester subscribeTeachers(CourseSemester semester, List<String> teacherEmails) {
        Query query = em.createQuery("select t from Teacher t where t.email in :teacherEmails", Teacher.class);
        query.setParameter("teacherEmails", teacherEmails);
        List<Teacher> result = query.getResultList();
        semester.getTeachers().addAll(result);
        
        return em.merge(semester);
    }

    @Override
    public CourseSemester getSemester(String semesterCode) {
        TypedQuery<CourseSemester> query = em.createNamedQuery("getSemesterBySemesterCode", CourseSemester.class);
        query.setParameter("semesterCode", semesterCode);
        CourseSemester result = null;
        try{
            result = query.getSingleResult();    
        }catch(NoResultException nre){
            System.out.println(nre);
        }
        
        return result;
    }
    
    
}
