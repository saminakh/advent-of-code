import java.io.*;
import java.util.*;

public class Day4 {
  public static void main(String[] args) {
    ArrayList<String[]> data = readData("data.txt");
    int roomSum = 0;
    for(String[] room : data) {
      HashMap map = charList(room[0]);
      char[] sorted = sortMap(map);
      String calcString = "";
      int index = 0;
      for(int i = 0; i < 5; i++) {
        calcString += sorted[i];
      }

      if (calcString.equals(room[2])) {
        int roomNum = Integer.parseInt(room[1]);
        roomSum += roomNum;
      }
    }
    System.out.println(roomSum);
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
  
  public static char[] sortMap(HashMap map) {
    char[] sorted = new char[map.size()];
    int i = 0;
    for(Object key : map.keySet()) {
      sorted[i] = (Character) key;
      i++;
    }
    char temp;
    for(int j = 0; j < sorted.length; j++) {
      for(int k = 1; k < sorted.length - j; k++) {
        if ((int)map.get(sorted[k - 1]) < (int)map.get(sorted[k])) {
          temp = sorted[k - 1];
          sorted[k - 1] = sorted[k];
          sorted[k] = temp;
        } else if (
          (int)map.get(sorted[k - 1]) == (int)map.get(sorted[k]) &&
          (int)sorted[k - 1] > (int)sorted[k]
        ) {
          temp = sorted[k - 1];
          sorted[k - 1] = sorted[k];
          sorted[k] = temp;          
        }
      }
    }
    return sorted;
  }
  
  public static ArrayList<String[]> readData(String file) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      ArrayList<String[]> rooms = new ArrayList<String[]>();
      String line;
      while ((line = br.readLine()) != null) {
        String[] room = new String[3];
        room[0] = line.substring(0, line.lastIndexOf("-"));
        room[1] = line.substring(line.lastIndexOf("-") + 1, line.indexOf("["));
        room[2] = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
        rooms.add(room);
      }
      return rooms;
    } catch(IOException e) {
      System.out.println("file " + file + " not found");
      return null;
    }
  }
}
