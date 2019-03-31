package com.example.demo.bean;

import javax.validation.constraints.NotBlank;

import com.example.demo.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserForm {

    @NotBlank(message="IDは必須項目です。")
    private String id;
    private String userName;
    private String password;
    private String age;
    public UserForm(User user) {
        this.id = Long.toString(user.getId());
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.age = Integer.toString(user.getAge());
    }
    public User getUser() {
        User user = new User();
        user.setId(Long.parseLong(this.id));
        user.setUserName(this.userName);
        user.setPassword(this.password);
        user.setAge(Integer.parseInt(this.age));
        return user;
    }
}
