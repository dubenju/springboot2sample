/**
 * 
 */
package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.apache.commons.lang3.StringUtils;

import com.example.demo.entity.pk.LoginUserId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author DBJ
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class LoginUser {
    @EmbeddedId
    private LoginUserId id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer age;

    @Column
    private String roles;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if ((obj instanceof LoginUser) == false) {
            return false;   
        }
        LoginUser o = (LoginUser) obj;

        return o.id.equals(this.id) &&
        StringUtils.equals(o.userName, this.userName) &&
        StringUtils.equals(o.email, this.email) &&
        StringUtils.equals(o.password, this.password) &&
        StringUtils.equals(o.roles, this.roles) &&
        o.age.equals(this.age);
    }
}
