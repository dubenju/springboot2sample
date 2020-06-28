package com.example.demo.model.service;

import java.time.LocalDateTime;

import com.example.demo.entity.CmnColumn;
import com.example.demo.exception.ExclusionException;
import com.example.demo.exception.ExclusionException.ExcluionType;
import com.example.demo.utils.DateUtil;

public abstract class AbstractService {
    protected void checkExclusion(CmnColumn table, LocalDateTime updateDateTime, String tableName) {
        checkExclusion(table, updateDateTime, tableName, ExcluionType.EDIT);
    }
    protected void checkExclusion(CmnColumn table, LocalDateTime updateDateTime, String tableName, ExcluionType excluionType) {
        LocalDateTime dbUpdateDateTime = DateUtil.convertLocalDateTime(table.getUpdateDate(), table.getUpdateTime());
        if (dbUpdateDateTime.isAfter(updateDateTime)) {
            throw new ExclusionException("", tableName, excluionType);
            
        }
    }
}
