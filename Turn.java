import java.lang.ProcessBuilder;
import java.lang.Process;
import java.io.IOException;

import java.lang.InterruptedException;

public class Turn implements Runnable {

  ProcessBuilder pb1, pb2, pbl_ah, pbr_ah, pbl_b, pbr_b, pbl_st, pbr_st;
  String way, length;
  public Turn() throws IOException{
      pbl_ah = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "left_wheel_hinge", "--vel-t", "4");
      pbr_ah = new ProcessBuilder("gz", "joint", "-m",
          "eater", "-j", "right_wheel_hinge", "--vel-t", "4");
      pbl_b = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "left_wheel_hinge", "--vel-t", "-4");
      pbr_b = new ProcessBuilder("gz", "joint", "-m",
          "eater", "-j", "right_wheel_hinge", "--vel-t", "-4");
      pbl_st = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "left_wheel_hinge", "--vel-t", "0");
      pbr_st = new ProcessBuilder("gz", "joint", "-m",
          "eater", "-j", "right_wheel_hinge", "--vel-t", "0");
  }



  public void left(String length) throws InterruptedException, IOException{
    way = "left";
    this.length = length;
    (new Thread(this)).start();
    System.out.println(way);
  }

  public void right(String length) throws InterruptedException, IOException{
    way = "right";
    this.length = length;
    (new Thread(this)).start();
    System.out.println(way);
  }

  // @override
  public void run(){
    try{
        System.out.println(way);
      switch(way){
        case "left":
        System.out.println(way);
          pbl_b.start();
          pbr_ah.start();
          break;
        case "right":
        pbl_ah.start();
          pbr_b.start();
          break;
      }
      switch(length){
        case "small":
          Thread.sleep(1500);
        case "big":
          Thread.sleep(3000);
      }
      // System.out.println(st);
      pbl_st.start();
      pbr_st.start();
    }
    catch(IOException ex){}
    catch(InterruptedException ex){}
  }
}
