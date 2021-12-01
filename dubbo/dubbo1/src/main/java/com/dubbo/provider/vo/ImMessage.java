package com.dubbo.provider.vo;

import lombok.Data;

@Data
public class ImMessage {

    /**
     * 类型 login normal
     */
    private String msgType;
    /**
     * 消息userId
     */
    private Long msgUserId;
    /**
     * 平台
     */
    private Integer msgPlat;

}
