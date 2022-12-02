package com.mufi.mufiServer.controller;

import com.mufi.mufiServer.dto.TestUserDto;
import com.mufi.mufiServer.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/hello")
    public String test() {
        return "Hello MUFI";
    }

    @GetMapping("/hello/{id}")
    public String idTest(@PathVariable("id") String id) {
        return "Hello " + id + "!";
    }

    @GetMapping("/info/{id}")
    public String getName(@PathVariable("id") String id) {
        return testService.getUserName(id);
    }

    @GetMapping("info1/{id}")
    public @ResponseBody Map<String, Object> getIdName(@PathVariable("id") String id) {
        return testService.getUserIdName(id);
    }

    @GetMapping("add/{id}/{name}")
    public @ResponseBody Map<String, Object> addUser(@PathVariable("id") String id, @PathVariable("name") String name) {
        return testService.addUser(id, name);
    }

    @GetMapping("info/all")
    public @ResponseBody Map<String, Object> getAllUser() {
        return testService.getAllUser();
    }

    @GetMapping("map")
    public Map<String, Object> testMapReturn() {
        Map<String, Object> map = new HashMap<>();
        ArrayList<TestUserDto> arrayList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            arrayList.add(new TestUserDto("idid", "namename"));
        }

        map.put("list", arrayList);

        return map;
    }
}
