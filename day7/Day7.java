import java.io.*;
import java.util.*;

public class Day7 {
  public static void main(String[] args) {
    ArrayList<String> data = readData("data.txt");
    int validIps = 0;
    for(String line : data) {
      ParsedIP ip = parseData(line);
      boolean isValidIp = true;
      boolean hasAbba = false;
      ArrayList<String> type1 = ip.type1;
      ArrayList<String> type2 = ip.type2;
      for(String chunk : type1) {
        if(isAbba(chunk)) hasAbba = true;
      }
      for(String chunk : type2) {
        if(isAbba(chunk)) isValidIp = false;
      }
      if(isValidIp && hasAbba) validIps++;
    }
    System.out.println(validIps);
  }
  
  public static boolean isAbba(String s) {
    int index = 0;
    if(s.length() < 4) return false;
    while(index < s.length() - 3) {
      int char1 = (int) s.charAt(index);
      int char2 = (int) s.charAt(index + 1);
      if (char1 == char2) {
        index++;
      } else {
        int char3 = (int) s.charAt(index + 2);
        int char4 = (int) s.charAt(index + 3);
        if ((char1 == char4) && (char2 == char3)) return true;
        index++;
      }
    }
    return false;
  }
  
  public static ArrayList<String> readData(String file) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      ArrayList<String> data = new ArrayList<String>();
      String line;
      while ((line = br.readLine()) != null) {
        data.add(line);
      }
      return data;
    } catch(IOException e) {
      System.out.println("file " + file + " not found");
      return null;
    }
  }
  
  public static ParsedIP parseData(String line) {
    int index = 0;
    ArrayList<String> type1 = new ArrayList<String>();
    ArrayList<String> type2 = new ArrayList<String>();
    boolean isType1 = true;
    while(index < line.length()) {
      if(isType1) {
        int end = line.indexOf("[", index);
        if(end == -1) end = line.length();
        String parsedString = line.substring(index, end);
        type1.add(parsedString);
        index = end + 1;
      } else {
        int end = line.indexOf("]", index);
        String parsedString = line.substring(index, end);
        type2.add(parsedString);
        index = end + 1;
      }
      isType1 = !isType1;
    }
    return new ParsedIP(type1, type2);
  }
  
  public static class ParsedIP {
    ArrayList<String> type1;
    ArrayList<String> type2;
    
    public ParsedIP(ArrayList<String> type1, ArrayList<String> type2) {
      this.type1 = type1;
      this.type2 = type2;
    }
  }
}
