import java.io.*;
import java.util.*;

public class Day6 {
  public static void main(String[] args) {
    String[] data = readData("data.txt");
    String maxString = "";
    String minString = "";
    for(String s : data) {
      HashMap map = charList(s);
      maxString += maxChar(map);
      minString += minChar(map);
    }
    System.out.println("max: " + maxString);
    System.out.println("min: " + minString);
  }

  public static char minChar(HashMap map) {
    int maxVal = 10000;
    char maxKey = ' ';
    for(Object key : map.keySet()) {
      if((int)map.get(key) < maxVal) {
        maxKey = (char)key;
        maxVal = (int)map.get(key);
      }
    }
    return maxKey;
  }
    
  public static char maxChar(HashMap map) {
    int maxVal = 0;
    char maxKey = ' ';
    for(Object key : map.keySet()) {
      if((int)map.get(key) > maxVal) {
        maxKey = (char)key;
        maxVal = (int)map.get(key);
      }
    }
    return maxKey;
  }
  
  public static int countChar(char c, String s) {
    int count = 0;
    for(int i = 0; i < s.length(); i++) {
      if(c == s.charAt(i)) count++;
    }
    return count;
  }
  
  public static HashMap charList(String s) {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for(int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if(!map.containsKey(c) && (int)c >= 97 && (int)c <= 122) map.put(c, countChar(c, s));
    }
    return map;
  }
  
  public static String[] readData(String file) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String[] cols = new String[8];
      for(int i = 0; i < cols.length; i++) {
        cols[i] = "";
      }
      String line;
      while ((line = br.readLine()) != null) {
        for(int i = 0; i < cols.length; i++) {
          cols[i] = cols[i] + line.charAt(i);
        }
      }
      return cols;
    } catch(IOException e) {
      System.out.println("file " + file + " not found");
      return null;
    }
  }
}
