package com.exodia.webservice.business;

import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.database.dao.PlayerAccountDAO;
import com.exodia.database.entity.PlayerAccount;
import com.exodia.webservice.model.RegisterModel;
import com.exodia.webservice.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by manlm1 on 9/8/2015.
 */
@Service
public class Authentication {

    @Autowired
    private PlayerAccountDAO playerAccountDAO;

    public String doLogin(String username, String pass) {
        if (username.equals("admin")) {
            return "You are ok!!";
        } else {

            if (pass == null) {
                return "Poor you!";
            } else {
                return "Your password is " + pass;
            }
        }
    }

    public RegisterResponse doRegister(String email, String password) {
        PlayerAccount account = new PlayerAccount();
        account.setEmail(email);
        account.setPassword(password);
        account.setStatus(Constant.STATUS_ID.ACTIVE.getValue());
        account.setCreationTime(DateTimeUtil.getCurUTCInMilliseconds());
        account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
        playerAccountDAO.save(account);

        RegisterResponse response = new RegisterResponse();
        RegisterModel model = new RegisterModel();
        model.setEmail(email);
        response.setData(model);
        response.setMessage("Successful");
        response.setStatusCode("01");
        return response;
    }
}
