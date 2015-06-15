// import java.io.console;
import java.io.*;
// import java.util.Scanner;
// import java.io.IOException;
import java.lang.ProcessBuilder;
import java.lang.Process;

public class sth{
  public static void main(String args[]) throws IOException{

    ProcessBuilder pb = new ProcessBuilder("gz", "topic", "-e",
      "/gazebo/default/eater/ray_s/laser/scan", "-u");
    Process p = pb.start();

    BufferedReader stdInput = new BufferedReader(new
    InputStreamReader(p.getInputStream()), 10000);

    String s = stdInput.readLine();

    Float range_max = new Float(s.substring(s.indexOf("range_max"),
      s.indexOf("count")).split(" ", 3)[1]);
    Integer ray_count = new Integer(s.substring(s.indexOf("count"),
      s.indexOf("vertical_angle")).split(" ", 3)[1]);

    System.out.println(ray_count);
    System.out.println(range_max);

    String ss;
    String[] arr;
    Float[] ranges = new Float[ray_count];

    for(;;){

      ss = s.substring(s.indexOf("ranges:"), s.indexOf(" intens"));
      arr = ss.split("\\s*ranges:\\s*");


      for(int i = 0; i<arr.length - 1; i++){
        // System.out.print(ranges[i] + ' ');
        ranges[i] = Float.parseFloat(arr[i+1]);
      }
      // System.out.println(arr.length);
      // System.out.println(ranges.length);

  //     Scanner sc = new Scanner(s.substring(s.indexOf("ranges"),
  //       s.indexOf("inten")));
  //     sc.useDelimiter("\\s*ranges:\\s*");
  //     for(int i = 0; i<ray_count; i++)
  //       if(sc.hasNextFloat())
  //         ranges[i] = sc.nextFloat();
  //       else{
  //         System.out.println("rays error");
  // }
  //         System.exit(1);

      int ray_s_sectors = 12;
      Integer[] ray_s_cond = new Integer[ray_s_sectors];

      for(int i = 0; i < ray_s_sectors; i++){
        for(int j = ranges.length / ray_s_sectors * i;
          j < ranges.length / ray_s_sectors * (i+1) - 1; j++){
          // System.out.print(ranges[j] + " ");
          if(ranges[j] < range_max){
            if(ranges[j] < range_max/2)
              ray_s_cond[i] = 1;
            else
              ray_s_cond[i] = 2;
            // System.out.println(ray_s_cond[i]);
            break;
          }
        }
      }

      for(int i = 0; i<ray_s_sectors; i++)
        System.out.print(ray_s_cond[i] + " ");
      System.out.println("");

//taking the last string from the Stream buffer
      // while(stdInput.ready()){
        s = stdInput.readLine();
      // }

        // try{
        //   Thread.sleep(5000);
        // }
        // catch(InterruptedException ex){}

        // while (stdInput.readLine() != null)
        // {}

      // while ((s = stdInput.readLine()) != null) {
      //   System.out.println(s);
      // }
      // String s = stdInput.readLine();
  }

  }
}
