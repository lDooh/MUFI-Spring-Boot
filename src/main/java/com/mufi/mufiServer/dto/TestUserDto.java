package com.mufi.mufiServer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestUserDto {
    private String id;

    private String name;

    public TestUserDto() {

    }

    public TestUserDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
