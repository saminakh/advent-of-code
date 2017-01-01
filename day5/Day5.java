import java.io.*;
import java.util.*;
import java.security.MessageDigest;

public class Day5 {
  public static void main(String[] args){
    String input = "wtnhxymk";
    String password = "";
    int i = 0;
    while(password.length() < 8) {
      String hashInput = md5hash(input + i);
      if(hashInput.length() > 5) {
        if(hashInput.indexOf("00000") == 0){
          password += hashInput.charAt(5);
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
