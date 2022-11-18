package com.mufi.mufiServer.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("/hello")
    public String test() {
        return "Hello MUFI";
    }

    @GetMapping("/hello/{id}")
    public String idTest(@PathVariable String id) {
        return "Hello " + id + "!";
    }
}
