import java.io.*;
import java.io.IOException;
import java.lang.InterruptedException;
import ru.nsu.alife.fs.IAction;


class ctrl{
  public static void main(String[] args) throws InterruptedException, IOException{
    // try{
      // Mover a = new Mover();
      // a.moveAhead.doAction();
      // a.moveBack.doAction();
      // a.turnRightSmall.doAction();
      // a.turnLeftSmall.doAction();
      // a.turnRightBig.doAction();
      // a.turnLeftBig.doAction();
    // }
    // catch(InterruptedException ex){}
    // catch(IOException ex){}

    // (new OnMove()).sensorOut();
    // try{
    //   sth a = new sth();
    //   for(;;){
    //   a.sensorOut();
    //   Thread.sleep(500);
    //   }
    // }
    // catch(InterruptedException ex){}

    // new EnvirCtrl();
    //
    //
    //   String s;
    //   Float x, y, xRnd, yRnd;
    //   Process p, p2;
    //
    //       xRnd = -6 + (float) java.lang.Math.random() * 12;
    //       yRnd = -6 + (float) java.lang.Math.random()*12;
    //
    //       s = "gz model -m food" + " -x " + xRnd.toString() + " -y " + yRnd.toString() + " -z 2";
    //       System.out.println(s);
    //       p2 = java.lang.Runtime.getRuntime().exec(s);

    IAction par;

    Ctrl_Sys mvr = new Ctrl_Sys();

    par = mvr.moveAhead;
    par.doAction();
    try{
      Thread.sleep(5000);
    }
    catch(InterruptedException ex){}

    par = mvr.moveBack;
    par.doAction();
    try{
      Thread.sleep(5000);
    }
    catch(InterruptedException ex){}

    par = mvr.turnLeft;
    par.doAction();
    try{
      Thread.sleep(5000);
    }
    catch(InterruptedException ex){}

    par = mvr.turnRight;
    par.doAction();
    try{
      Thread.sleep(5000);
    }
    catch(InterruptedException ex){}

    par = mvr.stop;
    par.doAction();

  }
}
