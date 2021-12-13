package com.im.server.dto;

import lombok.Data;

@Data
public class ChatDto {

    private String uuid;
    private String userId;

    public ChatDto() {

    }

    public ChatDto(String uuid, String userId) {
        this.uuid = uuid;
        this.userId = userId;
    }
}
