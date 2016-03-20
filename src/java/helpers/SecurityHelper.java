/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author John
 */
public class SecurityHelper {

    public String hashPassword(String password) {
        String hashedPassword = null;
        try {
            byte[] salt = new byte[16];
            salt = "borrachoPerdido".getBytes();

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            hashedPassword = enc.encodeToString(hash);
            
        } catch (Exception e) {
            System.out.println("Error hashing password: " + e);
        }

        return hashedPassword;
    }

}
