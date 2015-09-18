package com.exodia.webservice.business;

import org.springframework.stereotype.Service;

/**
 * Created by manlm1 on 9/8/2015.
 */
@Service
public class Authentication {

    public String doLogin(String username, String pass){
        if (username.equals("admin")){
            return "You are ok!!";
        } else {

            if (pass == null){
                return "Poor you!";
            } else {
                return "Your password is " + pass;
            }
        }
    }
}
