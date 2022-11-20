package com.mufi.mufiServer.service;

import com.mufi.mufiServer.dao.UserMapper;
import com.mufi.mufiServer.dto.AppUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserMapper userMapper;

    @Override
    public Map<String, Object> userLogin(String id, String pw) {
        Map<String, Object> map = new HashMap<>();
        // 로그인 실패: 0, 로그인 성공: 1
        map.put("isLoginSuccess", 0);

        try {
            AppUserDto appUserDto = userMapper.userLogin(id, pw);

            System.out.println("====================================================================================================================");
            System.out.println(appUserDto.toString());
            System.out.println("====================================================================================================================");

            // 존재하지 않는 계정
            if (appUserDto == null) {
                map.put("Failed Type", -1);
            }
            else {
                // 비밀번호 오류
                if (!pw.equals(appUserDto.getUser_pw())) {
                    map.put("Failed Type", 0);
                }
                // 로그인 성공
                else {
                    map.replace("isLoginSuccess", 1);
                    map.put("name", appUserDto.getName());
                    map.put("call_number", appUserDto.getCall_number());
                    map.put("birth", appUserDto.getBirth());
                    map.put("gender", appUserDto.getGender());
                }
            }
        } catch (Exception e) {
            map.put("Failed Type", -2);
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map<String, Object> userIdInspection(String id) {
        Map<String, Object> map = new HashMap<>();

        // 0: 회원가입 불가, 1: 회원가입 가능
        map.put("isNotDuplicated", 0);

        try {
            // 해당하는 ID가 없어야 회원가입 가능
            if (userMapper.userIdInspection(id) != null) {
                map.put("Failed Type", -1);
            }
            else {
                map.replace("isNotDuplicated", 1);
            }
        } catch (Exception e) {
            map.put("Failed Type", -2);
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map<String, Object> userSignUp(String id, String pw, String name, String callNumber, Date birth, int gender) {
        Map<String, Object> map = new HashMap<>();

        // 0: 회원가입 실패, 1: 회원가입 성공
        map.put("isSignUpSuccess", 0);

        try {
            userMapper.userSignUp(id, pw, name, callNumber, birth, gender);

            map.replace("isSignUpSuccess", 1);
        } catch (Exception e) {
            map.put("Failed Type", -2);
            e.printStackTrace();
        }

        return map;
    }
}
