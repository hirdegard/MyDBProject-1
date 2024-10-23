package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;


@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private User user;
	@GetMapping("{id}")
	public String getUserById(@PathVariable int id, Model model) {
		user = userMapper.findById(id);
		model.addAttribute("user", user);
		return "findByIdView";//userMapper.findById(id);
	}
//	
//	@GetMapping
//	public List<User> getAllUsers() {
//		return //userMapper.findAll();
//	}
//	
//	@PostMapping
//	public String createUser(@RequestBody User user) {
//		userMapper.insert(user);
//		return //"User created successfully";
//	}
//	
//	@PutMapping
//	public String updateUser(@PathVariable int id, @RequestBody User user) {
//		user.setId(id);
//		userMapper.update(user);
//		return //"User updated successfully";
//	}
//	
//	@DeleteMapping("/{id}")
//	public String deleteUser(@PathVariable int id) {
//		userMapper.delete(id);
//		return //"User deleted successfully";
//	}
}
