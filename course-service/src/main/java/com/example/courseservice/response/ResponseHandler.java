package com.example.courseservice.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    // This method is used to build the response object
    public static ResponseEntity<Object> responseBuilder(
            String message, HttpStatus status, Object responseObject) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message); //Response message
        response.put("status", status.value()); //Numeric status code
        response.put("data", responseObject);  //Response object

        return new ResponseEntity<>(response, status);
    }
}
