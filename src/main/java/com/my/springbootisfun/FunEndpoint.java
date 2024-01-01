package com.my.springbootisfun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunEndpoint {

    @Value("${api-response:na}")
    private String getEndpointResponse;

    @GetMapping
    public String getFun() {
        return getEndpointResponse;
    }

}
