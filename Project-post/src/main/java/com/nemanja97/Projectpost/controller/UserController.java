package com.nemanja97.Projectpost.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nemanja97.Projectpost.dto.UserDTO;
import com.nemanja97.Projectpost.entity.User;
import com.nemanja97.Projectpost.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/users")
public class UserController {
	
	@Autowired
	private UserServiceInterface userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<User> users = userService.findAll();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for(User u: users) {
			usersDTO.add(new UserDTO(u));
		}
		return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id){
		User user = userService.findOne(id);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
	
	@GetMapping(value="/user/{username}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username){
		User user = userService.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
	@PostMapping(consumes="application/json")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
		User user = new User();
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setPhoto(userDTO.getPhoto());
		
		user = userService.save(user);
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.CREATED);
	}
	
}
