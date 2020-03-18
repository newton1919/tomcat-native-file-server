package com.yxy.fileserver.config;

import lombok.Data;

/**
 * 自定义业务异常
 * author： yuxiaoyang
 */
@Data
public class BusinessException extends Exception {
  private int code;
  private String msg;

  public BusinessException(int code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }
}
