package com.example.demo.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUserId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Column(name="ID")
    private long id;
}
