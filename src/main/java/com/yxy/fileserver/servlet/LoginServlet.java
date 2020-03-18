package com.yxy.fileserver.servlet;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yxy
 * 登录 fileServer,已确保上传下载接口安全性
 */
@Slf4j
public class LoginServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    InputStream is= null;
    is = request.getInputStream();
    String bodyInfo = IOUtils.toString(is, "utf-8");
    log.info("入参信息："+bodyInfo);
  }
}
