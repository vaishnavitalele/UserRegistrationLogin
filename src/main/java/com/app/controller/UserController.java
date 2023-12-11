package com.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.entity.User;
import com.app.service.UserService;
import com.app.userdto.UserDTO;
import com.app.userdto.UserLoginDTO;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/user_data")
public class UserController {

	@Autowired
    private final UserService userService;

    public UserController(UserService userService) 
    {
        this.userService = userService;
    }
    
    public UserService getUserService()
    {
    	return userService;
    }
    
    
    @PostMapping(path="/save")
	public String savePerson(@Valid @RequestBody UserDTO user) {
		
			UserDTO p = new UserDTO(user.getUserUsername(),user.getEmailId(),user.getMobNo(),
					user.getUserPassword());
			
			userService.saveUser(p);
			
			System.out.println("Account Created Successfully " +user.getUserUsername() +" "
			+user.getUserPassword());
			return "User saved Successfully";
	}
    

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDTO user, BindingResult bindingResult) 
    {
         if (bindingResult.hasErrors()) 
         {
             return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
         }

         try 
         {
        	 User user1 =userService.registerUser(user);
        	 //User user1 = new User(user.getUserUsername(),user.getUserPassword(), user.getMobNo(), user.getEmailId());
             
             return new ResponseEntity<>(user1, HttpStatus.CREATED);
         } 
         catch (IllegalArgumentException e) 
         {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
         }
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody UserLoginDTO user) 
    {
    	if (user.getUserUsername() == null || user.getUserPassword() == null) 
    	{
           return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    	 try 
        { 
        	 userService.authenticateUser(user);
             return new ResponseEntity<>(" Login Successfully", HttpStatus.CREATED);
         } 
         catch (IllegalArgumentException e) 
         {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
         }
    }
    
    
}


