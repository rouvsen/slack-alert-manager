package com.rouvsen.examples.slackalertmanager.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/success")
    public String success() {
        return "All systems go!";
    }

    @GetMapping("/error")
    public String error() {
        throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Simulated internal error");
    }
}

