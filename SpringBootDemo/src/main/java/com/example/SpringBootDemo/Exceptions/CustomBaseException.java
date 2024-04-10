package com.example.SpringBootDemo.Exceptions;

import lombok.Data;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.http.HttpStatus;
@Data
public class CustomBaseException  extends RuntimeException{

    private HttpStatus status;
    private SimpleResponse simpleResponse;

    public CustomBaseException(HttpStatus status, SimpleResponse simpleResponse) {
        this.status = status;
        this.simpleResponse = simpleResponse;
    }
}
