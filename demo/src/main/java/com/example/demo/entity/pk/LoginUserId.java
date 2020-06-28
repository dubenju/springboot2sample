package com.example.demo.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder(toBuilder = true)
public class LoginUserId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Column(name="ID")
    private long id;
}
