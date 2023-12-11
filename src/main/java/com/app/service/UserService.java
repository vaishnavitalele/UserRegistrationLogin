package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.repository.UserRepository;
import com.app.userdto.UserDTO;
import com.app.userdto.UserLoginDTO;

import jakarta.validation.Valid;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User saveUser(UserDTO user){
		User user12 = new User(user.getUserUsername(),user.getUserPassword(),user.getEmailId(),user.getMobNo());
		return userRepository.save(user12);
	}

	public User registerUser(@Valid UserDTO user1) throws IllegalArgumentException 
	{
	    if (userRepository.existsByUserUsername(user1.getUserUsername())) 
	    {
	        throw new IllegalArgumentException("Username  is already taken");
	    }
	    else if(userRepository.existsByEmailId(user1.getEmailId())) {
	    	throw new IllegalArgumentException("EmailId is already taken");
	    }
	    
	    User existingUser = userRepository.findByUserUsername(user1.getUserUsername());
	    
	    if (existingUser != null) 
	    {
	        throw new IllegalArgumentException("Username is already registered, register again");
	    }
	    
	    User newUser = new User( user1.getUserUsername(), user1.getUserPassword(), user1.getMobNo(), user1.getEmailId());
	   return userRepository.save(newUser);
	}

	
	public void authenticateUser(@Valid UserLoginDTO user) throws IllegalArgumentException 
	{
    if (!userRepository.existsByUserUsername(user.getUserUsername())) {
        throw new IllegalArgumentException("Username does not exist");
    }

    User existingUser = userRepository.findByUserUsername(user.getUserUsername());

    if (!existingUser.getUserPassword().equals(user.getUserPassword())) {
        throw new IllegalArgumentException("Incorrect password");
    }
	
	}

}



