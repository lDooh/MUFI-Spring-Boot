package com.mufi.mufiServer.service;

import java.sql.Date;
import java.util.Map;

public interface UserService {
    public Map<String, Object> userLogin(String id, String pw);

    public Map<String, Object> userIdInspection(String id);

    public Map<String, Object> userSignUp(String id, String pw, String name, String callNumber, Date birth, int gender);
}
