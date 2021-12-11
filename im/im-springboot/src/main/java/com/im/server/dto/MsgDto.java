package com.im.server.dto;

import lombok.Data;

@Data
public class MsgDto {

    private String uuid;
    private String userId;
    private String accept;
    private String msg;
    private Integer type;
}
