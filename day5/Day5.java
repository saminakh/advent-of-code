import java.io.*;
import java.util.*;
import java.security.MessageDigest;

public class Day5 {
  public static void main(String[] args){
    String input = "wtnhxymk";
    StringBuilder password = new StringBuilder("--------");
    int i = 0;
    char dash = '-';
    while(password.indexOf("-") >= 0) {
      String hashInput = md5hash(input + i);
      if(hashInput.length() > 6) {
        if(hashInput.indexOf("00000") == 0){
          int index = Character.getNumericValue(hashInput.charAt(5));
          if(index < 8 && password.charAt(index) == dash) {
            password.setCharAt(index, hashInput.charAt(6));
            System.out.println(password);
          }
        }
      }
      i++;
    }
    System.out.println(password);
    
  }
  
  public static String md5hash(String s){
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(s.getBytes());

      byte byteData[] = md.digest();

      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < byteData.length; i++) {
       sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
      }
      return sb.toString();    
    } catch(Exception e) {
      System.out.println(e);
      return null;
    }
  }
}
