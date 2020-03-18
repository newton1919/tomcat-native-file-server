package com.yxy.fileserver.servlet;

import com.yxy.fileserver.config.BusinessException;
import com.yxy.fileserver.utils.ServletUtils;
import com.yxy.fileserver.utils.TokenCheckUtil;
import org.apache.catalina.servlets.DefaultServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultServletExtend extends DefaultServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 验证token
    try {
      TokenCheckUtil.checkToken(request);
    } catch (BusinessException ex) {
      ServletUtils.setResponseException(response, ex);
      return;
    }

    super.doPost(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 验证token
    try {
      TokenCheckUtil.checkToken(request);
    } catch (BusinessException ex) {
      ServletUtils.setResponseException(response, ex);
      return;
    }

    super.doGet(request, response);
  }
}
