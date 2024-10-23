package com.example.demo.mapper;



import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.MyDbProject1Application;
import com.example.demo.domain.User;

@SpringBootTest(classes = MyDbProject1Application.class)
@Transactional
class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	void testFindById() {
		var user1 = userMapper.findById(1);
		
		assertThat(user1, notNullValue());
		assertThat(user1.getName(), is ("hoge"));
		assertThat(user1.getEmail(), is("hoge@gmail.com"));
		assertThat(user1.getAge(), is(20));
	}
	
	@Test
	void testFindAll() {
		var user1 = new User(1, "hoge", "hoge@gmail.com", 20);
		var user2 = new User(2, "fuga", "fuga@gmail.com", 30);
		var user3 = new User(3, "piyo", "piyo@gmail.com", 40);
		List<User> exceptedList = new ArrayList<User>(List.of(user1, user2, user3));
		List<User> testList = userMapper.findAll();
		testList.forEach(e -> assertThat(exceptedList.contains(e), is(true)));
		
	}
	
	@Test
	void testRemoveAll() {
		userMapper.removeAll();
		assertThat(userMapper.findAll().size(), is(0));
	}
	
	@Test
	void testInsert() {
		userMapper.removeAll();
		var user = new User();
		user.setName("drken");
		user.setEmail("drken@gmail.com");
		user.setAge(100);
		userMapper.insert(user);
		var result = userMapper.findAll().get(0);
		assertThat(result.getName(), is(user.getName()));
		assertThat(result.getEmail(), is(user.getEmail()));
		assertThat(result.getAge(), is(user.getAge()));
	}
	
	@Test
	void testUpdate() {
		var user1 = userMapper.findById(1);
		var user2 = new User(1, "test", "test@gmail.com", 20);
		userMapper.update(user2);
		var user3 = userMapper.findById(1);
		assertThat(user3, is(not(user1)));
		assertThat(user3, is(user2));
	}
	
	@Test
	void testDelete() {
		userMapper.delete(1);
		var user = userMapper.findById(1);
		assertThat(user, is(nullValue()));
	}
	
	
	

}
