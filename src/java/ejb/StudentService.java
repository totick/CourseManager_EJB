/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Absence;
import entities.Student;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author John
 */
@Stateless
public class StudentService implements IStudentService {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Student> allStudents() {
        TypedQuery<Student> query = em.createNamedQuery("allStudents", Student.class);
        List<Student> students = query.getResultList();
        return students;
    }

    @Override
    public void removeStudent(int id) {
        Student student = em.find(Student.class, id);
        //The argument must be a managed entity
        em.remove(student);
    }

    @Override
    public Student saveStudent(Student student) {
        Student savedStudent = em.merge(student);
        return savedStudent;
    }

    @Override
    //Using save student for now to merge the student with the added Absence object
    public Absence addAbsence(Absence abs) {
        em.persist(abs);
        return abs;
    }

    @Override
    public void removeAbsence(Absence abs) {
        Absence mergedAbs = em.merge(abs);
        em.remove(mergedAbs);
    }
    
    @Override
    public void removeAbsence(int studentId, int semesterId, Date absenceDate){
        Query query = em.createQuery("select a from Absence a where a.student.id = :studentId and a.semester.id = :semesterId and a.absenceDate = :absenceDate", Absence.class);
        query.setParameter("studentId", studentId);
        query.setParameter("semesterId", semesterId);
        query.setParameter("absenceDate", absenceDate);
        Absence abs = (Absence) query.getSingleResult();
        em.remove(abs);
    }
    
    @Override
    public List<Absence> getAbsenceList(int studentId, int semesterId){
        Query query = em.createQuery("select a from Absence a where a.student.id = :studentId and a.semester.id = :semesterId", Absence.class);
        query.setParameter("studentId", studentId);
        query.setParameter("semesterId", semesterId);       
        List<Absence> result = query.getResultList();
        return result;
    }
}
