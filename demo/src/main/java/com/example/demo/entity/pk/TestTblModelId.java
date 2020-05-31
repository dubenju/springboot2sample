package com.example.demo.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class TestTblModelId implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name="TEST_ID")
    private String testId;
}
