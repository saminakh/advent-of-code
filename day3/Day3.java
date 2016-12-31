import java.io.*;
import java.util.*;

public class Day3 {
  public static void main(String[] args) {
    ArrayList<int[]> data = readData("data.txt");
    
    // part 1
    int possibleTriangles = 0;
    for(int[] triangle : data) {
      if(
        triangle[0] + triangle[1] > triangle[2] &&
        triangle[0] + triangle[2] > triangle[1] &&
        triangle[1] + triangle[2] > triangle[0]
      ) possibleTriangles++;
    }
    System.out.println(possibleTriangles);
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
}
