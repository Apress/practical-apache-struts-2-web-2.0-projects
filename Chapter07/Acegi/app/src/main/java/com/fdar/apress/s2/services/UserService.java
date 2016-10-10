package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.User;

public interface UserService {

    public User findByEmail( String email );

    public void persist( User user, String emailId );

}
