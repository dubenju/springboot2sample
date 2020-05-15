package com.example.demo.config;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class SessionInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String userName;
    private String email;
}
