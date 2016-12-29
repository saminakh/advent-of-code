import java.io.*;
import java.util.*;

public class Day2 {
  public static void main(String[] args) {
    ArrayList<String> data = readData("data.txt");
    int[] position = new int[]{0,0};
    for(String line : data) {
      position = getNextCode(position, line);
      System.out.printf("[" + position[0] + ", " + position[1] + "]");
      int code = readCode(position);
      System.out.println(" --> " + code);
    }
  }

  public static ArrayList<String> readData(String file) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      ArrayList<String> moves = new ArrayList<String>();
      String line;
      while ((line = br.readLine()) != null) {
        moves.add(line);
      }
      return moves;
    } catch(IOException e) {
      System.out.println("file " + file + " not found");
      return null;
    }
  }
  
  // interpret 1-9 keypad as
  // [-1, 1],  [0, 1],  [1, 1]
  // [-1, 0],  [0, 0],  [1, 0]
  // [-1, -1], [0, -1], [1, -1]
  
  public static int readCode(int[] position) {
    if (position[0] == -1 && position[1] == 1) return 1;
    if (position[0] == 0 && position[1] == 1) return 2;
    if (position[0] == 1 && position[1] == 1) return 3;
    if (position[0] == -1 && position[1] == 0) return 4;
    if (position[0] == 0 && position[1] == 0) return 5;
    if (position[0] == 1 && position[1] == 0) return 6;
    if (position[0] == -1 && position[1] == -1) return 7;
    if (position[0] == 0 && position[1] == -1) return 8;
    if (position[0] == 1 && position[1] == -1) return 9;
    return 0;
  }

  public static int[] getNextCode(int[] position, String line) {
    int[] nextPosition = position;

    for(int i = 0; i < line.length(); i++) {
      switch(line.charAt(i)) {
        case 'U':
          nextPosition[1] = move(nextPosition[1] + 1);
          break;
        case 'D':
          nextPosition[1] = move(nextPosition[1] - 1);
          break;
        case 'R':
          nextPosition[0] = move(nextPosition[0] + 1);
          break;
        case 'L':
          nextPosition[0] = move(nextPosition[0] - 1);
          break;
        default:
          break;
      }
    }
    return nextPosition;
  }
  
  public static int move(int move) {
    if(move < -1) return -1;
    if(move > 1) return 1;
    return move;
  }
}
