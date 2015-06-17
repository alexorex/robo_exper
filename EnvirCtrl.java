import java.io.*;
import java.lang.ProcessBuilder;
import java.lang.Process;

import java.lang.InterruptedException;

public class EnvirCtrl implements Runnable{

  Integer[] ray_s_cond;
  String[] arr;
  String s;
  Float x, y, xRnd, yRnd;
  // , minBord = new Float(-6), maxBord = new Float(6)
  float eaterx, eatery, foodx, foody;
  Process p, p2;
  ProcessBuilder pb, pb2;
  BufferedReader stdInput;
  SurroundingScanner ObjectSensor;
  static boolean foodEaten = false;

  EnvirCtrl(){
    // pb = new ProcessBuilder("gz", "topic", "-e",
    //   "/gazebo/default/pose/info", "-u");
    // p = pb.start();
    // stdInput = new BufferedReader(new
    //   InputStreamReader(p.getInputStream()), 10000);
    pb = new ProcessBuilder("gz", "model", "-m", "eater", "-p");
    (new Thread(this)).start();
  }

  private void foodRespawn(){
    try{
      p = pb.start();
      stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()), 100);
      arr = stdInput.readLine().split("\\s+");

      System.out.println(arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]);

      x = Float.parseFloat(arr[0]);
      y = Float.parseFloat(arr[1]);

      System.out.println(x.toString()+" "+y.toString());

      do
        xRnd = -6 + (float) java.lang.Math.random() * 12;
      while((xRnd > x-2) && (xRnd < x+2));
      do
        yRnd = -6 + (float) java.lang.Math.random()*12;
      while((yRnd > x-2) && (yRnd < x+2));

      System.out.println(xRnd.toString()+" "+yRnd.toString());

      s = "gz model -m food" + " -x " + xRnd.toString() + " -y " + yRnd.toString() + " -z 2";
      System.out.println(s);
      p2 = java.lang.Runtime.getRuntime().exec(s);

      // if(p.isAlive())
      //   p.destroy();
      // if(p2.isAlive())
      //   p2.destroy();
    }
    catch(IOException ex){}
  }

  public void run(){

    ObjectSensor = new SurroundingScanner();

    for(;;){
      try{
        Thread.sleep(1000);
      }
      catch(InterruptedException ex){}

      ObjectSensor.readReq = true;
      ray_s_cond = ObjectSensor.ray_s_cond;
      for (int item: ray_s_cond){
        if(item == -1){
          foodEaten = true;
          foodRespawn();
          break;
        }
      }

    }
  }
}
