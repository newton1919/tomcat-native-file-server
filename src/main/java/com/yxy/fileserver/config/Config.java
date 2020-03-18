package com.yxy.fileserver.config;

import lombok.Data;

/**
 * @author yxy
 * 配置类
 */
@Data
public class Config {
  public final static String apiKey = "644d78d7444b4633b67f730822d23b5e";
  public final static String apiSecret = "7cf09473e0004049a220050c507442f6";
  // token有效期,单位 秒, 默认一天
  public final static int tokenTime = 60 * 60 * 24;
}
