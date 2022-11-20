package com.mufi.mufiServer.service;

import java.util.Map;

public interface TestService {
    public String getUserName(String id);

    public Map<String, Object> getUserIdName(String id);

    public Map<String, Object> addUser(String id, String name);

    public Map<String, Object> getAllUser();
}
