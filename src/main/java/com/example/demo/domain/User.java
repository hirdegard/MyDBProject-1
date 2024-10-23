package com.example.demo.domain;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	private Integer id;
	private String name;
	private String email;
	private Integer age;
	

	public User() {
		
	}
	// equalsメソッドのオーバーライド
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // 同じインスタンスであれば等しい
        if (o == null || getClass() != o.getClass()) return false;  // 型が異なれば等しくない
        User user = (User) o;
        return Objects.equals(id, user.id) &&
               Objects.equals(name, user.name) &&
               Objects.equals(email, user.email) &&
               Objects.equals(age, user.age);  // ラッパークラスのequalsメソッドで比較
    }
}
