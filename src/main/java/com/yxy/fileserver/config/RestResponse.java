package com.yxy.fileserver.config;

import lombok.Data;

@Data
public class RestResponse<T> {
    /**
     * 状态码
     */
    private int status;
    /**
     * 信息
     */
    private String message;
    /**
     * 响应内容
     */
    private T content;

    public RestResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
