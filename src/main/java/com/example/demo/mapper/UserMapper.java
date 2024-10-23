package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.User;

@Mapper
public interface UserMapper {

	User findById (int id);
	
	List<User> findAll();
	
	void removeAll();
	
	void insert(User user);
	
	void update (User user);
	
	void delete (int id);
	
	//メールアドレスでユーザを検索する機能
	
	//年齢の最小値、最大値を指定して、その範囲内にいるユーザーを取得
}
