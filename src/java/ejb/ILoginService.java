/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Person;
import javax.ejb.Local;

/**
 *
 * @author John
 */
@Local
public interface ILoginService {
    
    Person verifyLogin(String email, String password);
    
}
