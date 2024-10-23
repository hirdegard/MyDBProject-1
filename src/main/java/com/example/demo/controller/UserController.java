package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;


@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("{id}")
	public String getUserById(@PathVariable int id, Model model) {
		var user = userMapper.findById(id);
		model.addAttribute("user", user);
		return "findByIdView";//userMapper.findById(id);
	}
	
	@GetMapping
	public String getAllUsers(Model model) {
		List<User> users = userMapper.findAll();
		model.addAttribute("users", users);
		return "findAllView";//userMapper.findAll();
	}
	
	@PostMapping
	public String createUser(@RequestParam("name") String name, 
			@RequestParam("email") String email, @RequestParam("age") int age, Model model) {
		var user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setAge(age);
		userMapper.insert(user);
		return "createdView";//"User created successfully";
	}
	
	@GetMapping("/add")
	public String moveCreateForm () {
		return "createForm";
	}
	
	@PutMapping
	public String updateUser(@ModelAttribute User user, Model model){
		model.addAttribute("user", user);
		
		userMapper.update(user);
		
		return "updatedView";//"User updated successfully";
	}
	
	@GetMapping("/update")
	public String moveUpdateForm() {
		return "updateForm";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		userMapper.delete(id);
		return "deletedView";//"User deleted successfully";
	}
	
	//mapperのほうでメールアドレスでユーザーを検索する機能等を実装予定なので、それに合わせてコントローラもつくる
	
}
