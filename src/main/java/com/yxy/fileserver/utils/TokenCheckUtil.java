package com.yxy.fileserver.utils;

import com.yxy.fileserver.config.BusinessException;
import com.yxy.fileserver.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
public class TokenCheckUtil {
  //存储token池
  private static Map<String, Date> tokenPool = new HashMap<>();
  //存储 不需要验证token的url列表
  private static List<String> noAuthUrls = new ArrayList<>();

  static {
    noAuthUrls.add("/login");
  }

  // 检测token是否有效
  public static void checkToken(HttpServletRequest request) throws BusinessException {
    String requestURI = request.getRequestURI();
    System.out.println("requestURI---->" + requestURI);
    // 放行某些url,不验证token
    if (noAuthUrls.contains(requestURI)) {
      return;
    }
    String token = request.getParameter("token");
    if (StringUtils.isEmpty(token)) {
      throw new BusinessException(403, "token不能为空");
    }
    Date expiredDate = tokenPool.get(token);
    if (expiredDate == null) {
      throw new BusinessException(403, "token无效");
    }
    //检查是否在有效期
    if (expiredDate.before(new Date())) {
      //删除过时的token
      deleteToken(token);
      throw new BusinessException(403, "token过时");
    }
    return;
  }

  //将token保存在后端内存中
  public static void pushToken(String token) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.add(Calendar.SECOND, Config.tokenTime);
    Date expiredDate = cal.getTime();
    tokenPool.put(token, expiredDate);
  }

  public static void deleteToken(String token) {
    tokenPool.remove(token);
  }
}
