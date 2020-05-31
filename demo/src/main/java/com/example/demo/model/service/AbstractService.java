package com.example.demo.model.service;

import java.time.LocalDateTime;

import com.example.demo.entity.CmnColumn;
import com.example.demo.service.DateUtil;
import com.example.demo.service.ExclusionException;
import com.example.demo.service.ExclusionException.ExcluionType;

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
