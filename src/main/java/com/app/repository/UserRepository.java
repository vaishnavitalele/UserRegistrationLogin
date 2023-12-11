package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.User;
import com.app.userdto.UserDTO;

public interface UserRepository extends JpaRepository<User, Long> {
    
    
    public User findByUserUsername(String username);

   
    public UserDTO findByUserPassword(String password);

    
    public boolean existsByUserUsername(String username);
    
    public boolean existsByEmailId(String EmailId);


}