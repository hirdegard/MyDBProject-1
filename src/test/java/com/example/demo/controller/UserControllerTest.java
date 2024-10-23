package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;

@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserMapper userMapper;
	
	@Test
	void testGetUserById() throws Exception {
		
		//モックの設定
		var user = new User(1, "hoge", "hoge@gmail.com", 20);
		when(userMapper.findById(1)).thenReturn(user);
		
		// /users/1に対してGetリクエスト
		mockMvc.perform(get("/users/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("findByIdView"))
				.andExpect(model().attribute("user", user));
	}
	
	@Test
	void testgetAllUsers() throws Exception {
		//モックの設定
		List<User> users = List.of(
				new User(1, "hoge", "hoge@gmail.com", 20),
				new User(2, "fuga", "fuga@gmail.com", 30),
				new User(3, "piyo", "piyo@gmail.com", 40));
		when(userMapper.findAll()).thenReturn(users);
		
		// /usersに対するGetリクエスト
		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(view().name("findAllView"))
			.andExpect(model().attribute("users", users));
	}
	
	@Test 
	void testMoveCreateForm() throws Exception {
		// /users/addを受けて画面遷移
		mockMvc.perform(get("/users/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("createForm"));
	}
	
	@Test
	void testCreateUser() throws Exception {
		//テスト用のUser
		var user = new User();
		user.setName("taro");
		user.setEmail("taro@gmail.com");
		user.setAge(30);
		
		//ほんへ
		mockMvc.perform(post("/users")
				
				.param("name", "taro")
				.param("email", "taro@gmail.com")
				.param("age", "30"))
			.andExpect(status().isOk())
			.andExpect(view().name("createdView"));
			
		verify(userMapper, times(1)).insert(user);
	}
	
	@Test
	void testMoveUpdateForm () throws Exception {
		// /user/updateを受けて遷移
		mockMvc.perform(get("/users/update"))
			.andExpect(status().isOk())
			.andExpect(view().name("updateForm"));
	}

	@Test
	void testUpdateUser() throws Exception {
		//モックでリクエストパラメータを入れる
		var user = new User(4, "taro", "taro@gmail.com", 30);
		mockMvc.perform(put("/users")
				.param("id", "4")
				.param("name", "taro")
				.param("email", "taro@gmail.com")
				.param("age", "30"))
		.andExpect(status().isOk())
		.andExpect(view().name("updatedView"))
		.andExpect(model().attribute("user", user));
		
		verify(userMapper).update(user);
	}
	
	@Test
	void testDeleteUser() throws Exception{
		mockMvc.perform(get("/users/delete/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("deletedView"));
		
		verify(userMapper).delete(1);
	}
}
/*
@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		userMapper.delete(id);
		return "deletedView";//"User deleted successfully";
	}
*/