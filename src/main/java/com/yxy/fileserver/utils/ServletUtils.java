package com.yxy.fileserver.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yxy
 * servlet相关帮助类
 */
public class ServletUtils {

  //设置response的json body
  public static void setResponseJson(HttpServletResponse response, Object body) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = null;
    try {
      out = response.getWriter();
      out.println(JSON.toJSON(body));
      out.flush();
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }
}
