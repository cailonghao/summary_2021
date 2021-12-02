package com.dubbo.provider.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImMessage implements Serializable {

    private String uuid;
    private String send;
    private String accept;
    private String msg;

}
