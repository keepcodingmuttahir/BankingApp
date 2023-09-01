package com.redmath.bankingapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository < User, Long > {

    //@Query("select u from User u where u.userName = ?1")
//    @Query(value = "SELECT * FROM users ", nativeQuery = true)
//     List<User> findAll(String userName);
//    @Query(value = "SELECT * FROM users WHERE user_name = ?", nativeQuery = true)
//    User findByUserName(String userName);
     User findByUserName (String username);

}
