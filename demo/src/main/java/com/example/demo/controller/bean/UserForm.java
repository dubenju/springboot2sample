package com.example.demo.controller.bean;

import javax.validation.constraints.NotBlank;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.pk.LoginUserId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode
public class UserForm {

    @NotBlank(message="IDは必須項目です。")
    private String id;
    private String userName;
    private String email;
    private String password;
    private String age;
    private String roles;

    
    public UserForm(LoginUser user) {
        this.id = Long.toString(user.getId().getId());
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.age = Integer.toString(user.getAge());
        this.roles = user.getRoles();
    }
    public LoginUser getUser() {
        LoginUser user = new LoginUser();
        user.setId(new LoginUserId());
        user.getId().setId(Long.parseLong(this.id));
        user.setUserName(this.userName);
        user.setEmail(this.email);
        user.setPassword(this.password);
        if (this.age != null) user.setAge(Integer.parseInt(this.age));
        user.setRoles(this.roles);
        return user;
    }
}
