/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptors;

import java.util.Map;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author User
 */
public class LoggingInterceptor {
    
    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        System.out.println(ic.getTarget().toString());
        System.out.println(ic.getMethod().toString());
        Map<String, Object> contextData = ic.getContextData();
        System.out.println("Contextdata:");
        for(Map.Entry<String, Object> entry : contextData.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Parameters:");
        Object[] params = ic.getParameters();
        if(params != null)
            for(Object param : params){
                System.out.println(param.toString());
            }
        return ic.proceed();
    }
}
