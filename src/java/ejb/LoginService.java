/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Person;
import helpers.SecurityHelper;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author User
 */
@Stateless
public class LoginService implements ILoginService {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Person verifyLogin(String email, String password) {
        String hashedPassword = new SecurityHelper().hashPassword(password);
        TypedQuery<Person> query = em.createNamedQuery("verifyPerson", Person.class);
        query.setParameter("email", email);
        query.setParameter("password", hashedPassword);
        Person person = null;
        
        try{
            person = query.getSingleResult();
        }catch(NoResultException e){
            System.out.println("Problem in verifyLogin: " + e);
        }
        return person;
    }
    
}
