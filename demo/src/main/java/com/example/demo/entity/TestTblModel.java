package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demo.entity.pk.TestTblModelId;

import lombok.Data;

@Data
@Entity
@Table(name="TEST_TBL")
public class TestTblModel implements Serializable, CmnColumn {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private TestTblModelId id;
        @Column(name="TEST_NAME")
        private String testName;
        @Column(name="CREATE_DATE")
        private String createDate;
        @Column(name="CREATE_TIME")
        private String createTime;
        @Column(name="UPDATE_DATE")
        private String updateDate;
        @Column(name="UPDATE_TIME")
        private String updateTime;
        @Column(name="UPDATE_USER_ID")
        private String updateUserId;
        @Column(name="UPDATE_WINDOW_ID")
        private String updateWindowId;
}
