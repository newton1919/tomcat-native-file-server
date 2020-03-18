package com.yxy.fileserver.servlet;

import com.yxy.fileserver.config.BusinessException;
import com.yxy.fileserver.utils.CommonUtils;
import com.yxy.fileserver.utils.ServletUtils;
import com.yxy.fileserver.utils.TokenCheckUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yxy
 * 参考url: https://blog.csdn.net/a3226988/article/details/82685094
 */
//使用如下注解才可接受文件上传
@MultipartConfig
public class UploadFileServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 验证token
    try {
      TokenCheckUtil.checkToken(req);
    } catch (BusinessException ex) {
      ServletUtils.setResponseException(resp, ex);
      return;
    }

    Part part = req.getPart("file");//获取上传的文件
    String appName = req.getParameter("app");//spring boot 应用名字
    String type = req.getParameter("type");//该应用下文件的用途

    String disposition = part.getHeader("Content-Disposition");
    String suffix = disposition.substring(disposition.lastIndexOf("."), disposition.length() - 1);//文件后缀名
    //随机的生存一个32的字符串
    String uuid = CommonUtils.generateUUID();
    String filename = uuid + suffix;//保存到文件系统的文件名
    //获取上传的文件流
    InputStream is = part.getInputStream();
    //动态获取tomcat服务器的路径
    String serverpath = req.getServletContext().getRealPath("upload");
    if (!Files.exists(Paths.get(serverpath))) {
      Files.createDirectory(Paths.get(serverpath));
    }
    if (!Files.exists(Paths.get(serverpath, appName))) {
      Files.createDirectory(Paths.get(serverpath, appName));
    }
    if (!Files.exists(Paths.get(serverpath, appName, type))) {
      Files.createDirectory(Paths.get(serverpath, appName, type));
    }
    Path fileDir = Paths.get(serverpath, appName, type);
    FileOutputStream fos = new FileOutputStream(fileDir + "/" + filename);
    byte[] bty = new byte[1024];
    int length = 0;
    while ((length = is.read(bty)) != -1) {
      fos.write(bty, 0, length);
    }
    fos.close();
    is.close();

    //返回文件路径给前端
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("fileUrl", "upload" + File.separator + appName + File.separator + type + File.separator + filename);
    ServletUtils.setResponseJson(resp, resultMap);
    return;
  }
}
