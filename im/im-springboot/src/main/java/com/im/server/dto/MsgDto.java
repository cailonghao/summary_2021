package com.im.server.dto;

import lombok.Data;

@Data
public class MsgDto {

    private Boolean ok;
    private String uuid;
    private String userId;
    private String accept;
    private String msg;
    private String directive;
    private Integer type;
}
