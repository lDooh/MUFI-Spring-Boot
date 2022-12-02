package com.mufi.mufiServer.service;

import com.mufi.mufiServer.dao.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestMapper testMapper;

    @Override
    public String getUserName(String id) {
        return testMapper.getUserName(id);
    }

    @Override
    public Map<String, Object> getUserIdName(String id) {
        Map<String, Object> map = new HashMap<>();

        map.put("아이디", id);

        if (testMapper.getUserName(id) != null) {
            map.put("이름", testMapper.getUserName(id));
            map.put("나이", 20);
        }
        else {
            map.put("이름", "유저 정보 없음");
        }

        return map;
    }

    @Override
    public Map<String, Object> addUser(String id, String name) {
        Map<String, Object> map = new HashMap<>();

        try {
            testMapper.addUser(id, name);

            map.put("회원가입 결과", "성공");
            map.put("아이디", id);
            map.put("이름", name);
        } catch (Exception e) {
            map.put("회원가입 결과", "실패");
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map<String, Object> getAllUser() {
        Map<String, Object> map = new HashMap<>();

        map.put("유저들", testMapper.getAllUser());

        return map;
    }
}
