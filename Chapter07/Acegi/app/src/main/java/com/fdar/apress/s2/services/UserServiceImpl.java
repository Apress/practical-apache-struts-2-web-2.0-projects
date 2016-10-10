package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.User;

import javax.persistence.*;

import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

public class UserServiceImpl implements UserService, UserDetailsService {

    private EntityManagerFactory emf;

    public UserServiceImpl() {
        emf = Persistence.createEntityManagerFactory("s2app");
    }

    public User findByEmail(String email) {
        EntityManager entityMgr = emf.createEntityManager();
        return entityMgr.find(User.class,email);
    }

    public void persist(User user,String emailId) {

        EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            if( "".equals(emailId) ) {
                entityMgr.persist(user);
            } else {
                entityMgr.merge(user);
            }

            tx.commit();
        }
        catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException, DataAccessException {
        User u = findByEmail(s);
        if( s==null || "".equals(s.trim()) || u==null ) {
            throw new UsernameNotFoundException(s);
        }
        return new PermissionedUser(u);
    }
}
