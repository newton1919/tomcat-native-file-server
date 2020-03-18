package com.yxy.fileserver.servlet;

import com.alibaba.fastjson.JSON;
import com.yxy.fileserver.DTO.LoginDto;
import com.yxy.fileserver.config.Config;
import com.yxy.fileserver.config.RestResponse;
import com.yxy.fileserver.utils.ServletUtils;
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

  protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    InputStream is= null;
    is = request.getInputStream();
    String bodyInfo = IOUtils.toString(is, "utf-8");
    log.info("入参信息："+bodyInfo);
    LoginDto loginDto = JSON.parseObject(bodyInfo, LoginDto.class);

    RestResponse restResponse = new RestResponse(200, "成功");
    if (!Config.apiKey.equals(loginDto.getApiKey()) ||(!Config.apiSecret.equals(loginDto.getApiSecret()))) {
      log.error("登录密钥错误, 无法登录");
      restResponse.setStatus(403);
      restResponse.setMessage("登录密钥错误, 无法登录");
      ServletUtils.setResponseJson(response, restResponse);
      return;
    }
    // 成功登录,返回 token

  }
}
