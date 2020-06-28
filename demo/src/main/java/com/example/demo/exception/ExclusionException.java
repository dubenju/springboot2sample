package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

public class ExclusionException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public enum ExcluionType {
        EDIT
        , DELETE
        ;
    }
    
    @Getter
    @Setter
    private String tableName;

    @Getter
    @Setter
    private ExcluionType excluionType;
    
    public ExclusionException() {
        super();
        this.excluionType = ExcluionType.EDIT;
    }
    public ExclusionException(String message) {
        super(message);
        this.excluionType = ExcluionType.EDIT;
    }
    public ExclusionException(String message, String tableName) {
        super(message);
        this.tableName = tableName;
        this.excluionType = ExcluionType.EDIT;
    }
    public ExclusionException(String message, String tableName, ExcluionType excluionType) {
        super(message);
        this.tableName = tableName;
        this.excluionType = excluionType;
    }
}
