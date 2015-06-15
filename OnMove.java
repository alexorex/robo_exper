import java.io.*;
import java.lang.ProcessBuilder;
import java.lang.Process;
import java.lang.InterruptedException;

public class OnMove{

      Boolean res;
      String s, ss;
      Float time0, time1, x0, x1, y0, y1;
      Process p;
      ProcessBuilder pb;
      BufferedReader stdInput;

    OnMove() throws IOException{
      pb = new ProcessBuilder("gz", "topic", "-e",
        "/gazebo/default/pose/info", "-u");
    }

    public Boolean sensorOut() throws IOException, InterruptedException{
      p = pb.start();
      stdInput = new BufferedReader(new
      InputStreamReader(p.getInputStream()), 10000);

      s = stdInput.readLine();

      time0 = new Float(s.substring(s.indexOf("sec"),
        s.indexOf("nsec")).split(" ", 3)[1]);
      x0 = new Float(s.substring(s.indexOf("x:"),
        s.indexOf("y:")).split(" ", 3)[1]);
      y0 = new Float(s.substring(s.indexOf("y:"),
        s.indexOf("z:")).split(" ", 3)[1]);

      for(;;){
        Thread.sleep(200);
        s = stdInput.readLine();
        if((time1 = new Float(s.substring(s.indexOf("sec"),
          s.indexOf("nsec")).split(" ", 3)[1])) - time0 < 1)
          break;
      }
      x1 = new Float(s.substring(s.indexOf("x:"),
        s.indexOf("y:")).split(" ", 3)[1]);
      y1 = new Float(s.substring(s.indexOf("y:"),
        s.indexOf("z:")).split(" ", 3)[1]);

      if( (x1-x0)*(x1-x0) + (y1-y0)*(y1-y0) > 0.5E-7 )
        res = true;
      else
        res = false;

      // System.out.println((x1-x0)*(x1-x0) + (y1-y0)*(y1-y0));
      // System.out.println(res);

      pb.destroy();

      return res;
  }
}
