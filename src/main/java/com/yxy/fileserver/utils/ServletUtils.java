package com.yxy.fileserver.utils;

import com.alibaba.fastjson.JSON;
import com.yxy.fileserver.config.BusinessException;
import com.yxy.fileserver.config.RestResponse;

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

  //返回异常错误给response
  public static void setResponseException(HttpServletResponse response, BusinessException ex) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = null;
    try {
      out = response.getWriter();
      RestResponse restResponse = new RestResponse(ex.getCode(), ex.getMsg());
      out.println(JSON.toJSON(restResponse));
      out.flush();
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

  //返回异常错误给response
  public static void setResponseError(HttpServletResponse response, int code, String message) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = null;
    try {
      out = response.getWriter();
      RestResponse restResponse = new RestResponse(code, message);
      out.println(JSON.toJSON(restResponse));
      out.flush();
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }
}
