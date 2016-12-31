import java.io.*;
import java.util.*;

public class Day3 {
  public static void main(String[] args) {
    ArrayList<int[]> data = readData("data.txt");
    
    // part 1
    int possibleTriangles = 0;
    for(int[] triangle : data) {
      if(isTriangle(triangle)) possibleTriangles++;
    }
    System.out.println(possibleTriangles);
    
    // part 2
    data = readDataVertical("data.txt");
    possibleTriangles = 0;
    for(int[] triangle : data) {
      if(isTriangle(triangle)) possibleTriangles++;
    }
    System.out.println(possibleTriangles);
  }
  
  public static boolean isTriangle(int[] triangle) {
    if(
      triangle[0] + triangle[1] > triangle[2] &&
      triangle[0] + triangle[2] > triangle[1] &&
      triangle[1] + triangle[2] > triangle[0]
    ) return true;
    return false;
  }
  public static ArrayList<int[]> readData(String file) {
    ArrayList<int[]> triangles = new ArrayList<int[]>();
    try{
      Scanner scanner = new Scanner(new File(file));
      while(scanner.hasNextInt()) {
        int[] triangle = new int[3];
        for (int i = 0; i < 3; i++) {
          if(scanner.hasNextInt()) triangle[i] = scanner.nextInt();
        }
        triangles.add(triangle);
      }      
    } catch(FileNotFoundException e) {
      System.out.println(e);
    }

    return triangles;
  }
  
  public static ArrayList<int[]> readDataVertical(String file) {
    ArrayList<int[]> triangles = new ArrayList<int[]>();
    ArrayList<Integer> sides = new ArrayList<Integer>();
    try{
      Scanner scanner = new Scanner(new File(file));
      while(scanner.hasNextInt()) {
        sides.add(scanner.nextInt());
      }
      for(int i = 0; i < sides.size(); i = i + 9) {
        int[] t1 = new int[3];
        int[] t2 = new int[3];
        int[] t3 = new int[3];
        for (int j = i; j < i + 9; j++) {
          if (j % 3 == 0) t1[(j - i) / 3] = sides.get(j);
          if (j % 3 == 1) t2[(j - i) / 3] = sides.get(j);
          if (j % 3 == 2) t3[(j - i) / 3] = sides.get(j);
        }
        triangles.add(t1);
        triangles.add(t2);
        triangles.add(t3);
      }
    } catch(FileNotFoundException e) {
      System.out.println(e);
    }

    return triangles;
  }
}
