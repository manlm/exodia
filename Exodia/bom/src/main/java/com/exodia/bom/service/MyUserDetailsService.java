package com.exodia.bom.service;

import com.exodia.common.constant.Constant;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.dao.UserRolesDAO;
import com.exodia.database.entity.AdminAccount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by manlm1 on 9/11/2015.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger LOG = Logger.getLogger(MyUserDetailsService.class);

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    @Autowired
    private UserRolesDAO userRolesDAO;

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        LOG.info(new StringBuilder("[loadUserByUsername] Start: username = ").append(username));
        AdminAccount adminAccount = adminAccountDAO.getByUsername(username);

        if (adminAccount.getStatus().getId() == Constant.STATUS_ID.DELETED.getValue()) {
            adminAccount = null;
        }

        List<GrantedAuthority> authorities = buildUserAuthority(adminAccount.getRole().getId());

        LOG.info("[loadUserByUsername] End");
        return buildUserForAuthentication(adminAccount, authorities);
    }

    private User buildUserForAuthentication(AdminAccount adminAccount,
                                            List<GrantedAuthority> authorities) {
        LOG.info(new StringBuilder("[buildUserForAuthentication] Start: username = ")
                .append(adminAccount.getUsername()));
        LOG.info("[buildUserForAuthentication] End");
        return new User(adminAccount.getUsername(),
                adminAccount.getPassword(), true,
                true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(int userRoles) {
        LOG.info(new StringBuilder("[buildUserAuthority] Start: userRoles = ").append(userRoles));
        Set<GrantedAuthority> setAuths = new HashSet<>();

        // Build user's authorities
        String role = userRolesDAO.findById(userRoles);
        setAuths.add(new SimpleGrantedAuthority(role));

        List<GrantedAuthority> Result = new ArrayList<>(setAuths);

        LOG.info("[buildUserAuthority] End");
        return Result;
    }
}
