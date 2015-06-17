import java.io.*;
import java.lang.ProcessBuilder;
import java.lang.Process;

public class SurroundingScanner implements Runnable{
// launch process with "gz topic" and prepare for sensor output

      int ray_s_sectors = 12;
      // Long l1, l2;
      String s, s2, ss, ss2;
      String[] arr, arr2;
      Float[] ranges, ranges2;
      Float range_max;
      Integer ray_count;
      volatile static Integer[] ray_s_cond;
      volatile static boolean readReq = false;
      Process p, p2;
      ProcessBuilder pb, pb2;
      BufferedReader stdInput, stdInput2;
      InputStream iS, iS2;

    SurroundingScanner(){
      (new Thread(this)).start();
    }

    public void run(){

      try{

      pb = new ProcessBuilder("gz", "topic", "-e",
        "/gazebo/default/eater/ray_s/laser/scan", "-u");

      pb2 = new ProcessBuilder("gz", "topic", "-e",
        "/gazebo/default/eater/ray_s_high/laser/scan", "-u");

      p = pb.start();
      p2 = pb2.start();

      stdInput2 = new BufferedReader(new
      InputStreamReader(iS = p2.getInputStream()), 5000);
      stdInput = new BufferedReader(new
      InputStreamReader(iS2 = p.getInputStream()), 5000);

      s = stdInput.readLine();
      stdInput2.readLine();

      range_max = new Float(s.substring(s.indexOf("range_max"),
        s.indexOf("count")).split(" ", 3)[1]);
      ray_count = new Integer(s.substring(s.indexOf("count"),
        s.indexOf("vertical_angle")).split(" ", 3)[1]);

      ray_s_cond = new Integer[ray_s_sectors];
      ranges = new Float[ray_count];
      ranges2 = new Float[ray_count];

      System.out.println(ray_count);
      System.out.println(range_max);


      // p.destroy();
      // p2.destroy();
      }
      catch(IOException ex){}


      for(;;){
        try{
  //taking the last string from the Stream buffer
        java.util.Arrays.fill(ray_s_cond, 0);
        long timeDelta;
        // p = pb.start();
        // p2 = pb2.start();
        // stdInput = new BufferedReader(new
        // InputStreamReader(iS2 = p.getInputStream()), 5000);
        // stdInput2 = new BufferedReader(new
        // InputStreamReader(iS = p2.getInputStream()), 5000);

        do{
            s = stdInput.readLine();
            s2 = stdInput2.readLine();

            for(;;){
              arr = s.substring(s.indexOf("sec"), s.indexOf("scan")).split("\\s");
              // System.out.println(arr[0] + arr[1] + arr[2] + arr[3]);
              arr2 = s2.substring(s2.indexOf("sec"), s2.indexOf("scan")).split("\\s");
              timeDelta = (Long.parseLong(arr[1])-Long.parseLong(arr2[1])) * 1000000000 +
              Long.parseLong(arr[3])-Long.parseLong(arr2[3]);
              // System.out.println(timeDelta);

              if(timeDelta < -0100000000)
                s = stdInput.readLine();
              else if(timeDelta > 0100000000)
                s2 = stdInput2.readLine();
              else
                break;
            }
        }
        while( !readReq );

        s = stdInput.readLine();
        s2 = stdInput2.readLine();



        // System.out.println(s + "\\n" + s2);

        ss = s.substring(s.indexOf("ranges:"), s.indexOf(" intens"));
        arr = ss.split("\\s*ranges:\\s*");
        ss = s2.substring(s2.indexOf("ranges:"), s2.indexOf(" intens"));
        arr2 = ss.split("\\s*ranges:\\s*");

        for(int i = 0; i<arr.length - 1; i++){
          // System.out.print(ranges[i] + ' ');
          // System.out.print(arr2[i] + " ");
          ranges[i] = Float.parseFloat(arr[i+1]);
          ranges2[i] = Float.parseFloat(arr2[i+1]);
        }
        }
        catch(IOException ex){}

        // System.out.println(arr.length);
        // System.out.println(ranges.length);

        for(int i = 0; i < ray_s_sectors; i++){
          for(int j = ranges.length / ray_s_sectors * i;
            j < ranges.length / ray_s_sectors * (i+1) - 1; j++){
            // System.out.print(ranges[j] + " ");
              if(ranges2[j] < range_max/5){
                ray_s_cond[i] = -1;}
              else if(ranges2[j] < range_max/2 && (ray_s_cond[i] < -2 || ray_s_cond[i] == 0)){
                ray_s_cond[i] = -2;}
              else if(ranges2[j] < range_max && (ray_s_cond[i] < -3 || ray_s_cond[i] == 0)){
                ray_s_cond[i] = -3;}

              if(ranges[j] < range_max/5 && (ray_s_cond[i] < -1 || ray_s_cond[i] == 0))
                ray_s_cond[i] = 1;
              else if(ranges[j] < range_max/2 && ((ray_s_cond[i] > 2 || ray_s_cond[i] == 0) || ray_s_cond[i] < -2))
                ray_s_cond[i] = 2;
              else if(ranges[j] < range_max && ((ray_s_cond[i] > 3 || ray_s_cond[i] == 0) || ray_s_cond[i] < -3))
                ray_s_cond[i] = 3;
              // System.out.println(ray_s_cond[i]);
              // if(ray_s_cond[i] > 0 &&
              // ((ranges2[j] < range_max && ranges[j] - 0.1 < ranges2[j] && ranges2[j] < ranges[j] + 0.1) ||
              // (j+1 < ranges.length && ranges2[j+1] < range_max && ranges[j+1] - 0.1 < ranges2[j] &&
              // ranges2[j] < ranges[j+1] + 0.1) ||
              // (j-1 > 0 && ranges2[j-1] < range_max && ranges[j-1] - 0.1 < ranges2[j] &&
              // ranges2[j] < ranges[j-1] + 0.1)))
              //   ray_s_cond[i] = -ray_s_cond[i];
              // break;
          }
        }

        for(int i = 0; i<ray_s_sectors; i++)
          System.out.print(ray_s_cond[i] + " ");
        System.out.println("");

        readReq = false;
      }
      // p.destroy();
      // p2.destroy();

      // return ray_s_cond;

  }
}
