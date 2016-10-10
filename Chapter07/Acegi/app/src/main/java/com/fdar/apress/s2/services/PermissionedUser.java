package com.fdar.apress.s2.services;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import com.fdar.apress.s2.domain.User;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public class PermissionedUser implements UserDetails {

    private User user;

    public PermissionedUser( User user ) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getPassword() {
        return user==null ? "" : user.getPassword();
    }

    public GrantedAuthority[] getAuthorities() {
        return new GrantedAuthority[] { new GrantedAuthorityImpl("ROLE_USER") };
    }

    public String getUsername() {
        return user==null ? "" : user.getEmail();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}
