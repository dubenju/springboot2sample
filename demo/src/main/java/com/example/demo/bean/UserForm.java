package com.example.demo.bean;

import javax.validation.constraints.NotBlank;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.pk.LoginUserId;

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
    public UserForm(LoginUser user) {
        this.id = Long.toString(user.getId().getId());
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.age = Integer.toString(user.getAge());
    }
    public LoginUser getUser() {
        LoginUser user = new LoginUser();
        user.setId(new LoginUserId());
        user.getId().setId(Long.parseLong(this.id));
        user.setUserName(this.userName);
        user.setPassword(this.password);
        user.setAge(Integer.parseInt(this.age));
        return user;
    }
}
