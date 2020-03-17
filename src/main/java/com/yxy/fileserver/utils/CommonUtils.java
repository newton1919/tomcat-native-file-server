package com.yxy.fileserver.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtils {
  public static Date getDateFromStr(String dateStr, String pattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    try {
      return sdf.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String generateUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public static void main(String[] args) {
    String newUuid = CommonUtils.generateUUID();
    System.out.println("uuid---->" + newUuid);
  }
}
