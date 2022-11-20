package com.mufi.mufiServer.controller;

import com.mufi.mufiServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Map;

@RestController
@RequestMapping("app/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/login/{id}/{pw}")
    public Map<String, Object> userLogin(@PathVariable("id") String id, @PathVariable("pw") String pw) {
        return userService.userLogin(id, pw);
    }
    
    // 회원가입 시 ID 중복검사
    @GetMapping("/inspection/{id}")
    public Map<String, Object> userInspection(@PathVariable("id") String id) {
        return userService.userIdInspection(id);
    }

    @GetMapping("/signup/{id}/{pw}/{name}/{callNumber}/{birth}/{gender}")
    public Map<String, Object> userSignUp(@PathVariable("id") String id, @PathVariable("pw") String pw,
                                          @PathVariable("name") String name, @PathVariable("callNumber") String callNumber,
                                          @PathVariable("birth") Date birth, @PathVariable("gender") int gender) {
        return userService.userSignUp(id, pw, name, callNumber, birth, gender);
    }
}
