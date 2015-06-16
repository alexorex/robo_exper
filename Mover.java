import java.lang.ProcessBuilder;
import java.lang.Process;
import java.io.IOException;

import ru.nsu.alife.fs.FS;
import ru.nsu.alife.fs.IAcceptor;
import ru.nsu.alife.fs.IAction;
import ru.nsu.alife.fs.IFS;
import ru.nsu.alife.fs.PredicateSet;

public class Mover{

  // Turn turn;

  public final IAction stop = new IAction(){
    @Override
    public boolean doAction(){
      try{
        java.lang.Runtime.getRuntime().exec("gz joint -m " +
          "eater -j left_wheel_hinge --vel-t 0");
        java.lang.Runtime.getRuntime().exec("gz joint -m " +
          "eater -j right_wheel_hinge --vel-t 0");
      }
      catch(IOException ex){}

      Ctrl_Sys.animateInMotion = false;
      return true;
    }
  };
  public final IAction moveAhead = new IAction(){
    @Override
    public boolean doAction(){
      try{
        java.lang.Runtime.getRuntime().exec("gz joint -m " +
          "eater -j left_wheel_hinge --vel-t 5");
        java.lang.Runtime.getRuntime().exec("gz joint -m " +
          "eater -j right_wheel_hinge --vel-t 5");
        // ProcessBuilder pbl = new ProcessBuilder("gz", "joint", "-m",
        //   "eater", "-j", "left_wheel_hinge", "--vel-t", "5");
        // ProcessBuilder pbr = new ProcessBuilder("gz", "joint", "-m",
        //     "eater", "-j", "right_wheel_hinge", "--vel-t", "5");
        //
        // pbl.start();
      	// pbr.start();
      }
      catch(IOException ex){}

      Ctrl_Sys.animateInMotion = true;
      return true;
    }
  };


  public final IAction moveBack = new IAction(){
    @Override
    public boolean doAction(){
      try{
        java.lang.Runtime.getRuntime().exec("gz joint -m " +
          "eater -j left_wheel_hinge --vel-t -3");
        java.lang.Runtime.getRuntime().exec("gz joint -m " +
          "eater -j right_wheel_hinge --vel-t -3");
        // ProcessBuilder pbl = new ProcessBuilder("gz", "joint", "-m",
        //   "eater", "-j", "left_wheel_hinge", "--vel-t", "-3");
        // ProcessBuilder pbr = new ProcessBuilder("gz", "joint", "-m",
        //   "eater", "-j", "right_wheel_hinge", "--vel-t", "-3");
        //
        // pbl.start();
      	// pbr.start();
      }
      catch(IOException ex){}

      Ctrl_Sys.animateInMotion = true;
      return true;
    }
  };

  public final IAction turnRight = new IAction(){
    @Override
    public boolean doAction(){
      try{
        java.lang.Runtime.getRuntime().exec("gz joint -m " +
          "eater -j left_wheel_hinge --vel-t 4");
        java.lang.Runtime.getRuntime().exec("gz joint -m " +
          "eater -j right_wheel_hinge --vel-t -4");
        }
      catch(IOException ex){}
      // catch(InterruptedException EX){}

      Ctrl_Sys.animateInMotion = true;
      return true;
    }
  };

  public final IAction turnLeft = new IAction(){
    @Override
    public boolean doAction(){
      try{
        java.lang.Runtime.getRuntime().exec("gz joint -m " +
          "eater -j left_wheel_hinge --vel-t -4");
        java.lang.Runtime.getRuntime().exec("gz joint -m " +
          "eater -j right_wheel_hinge --vel-t 4");
        }
      catch(IOException ex){}
      // catch(InterruptedException EX){}

      Ctrl_Sys.animateInMotion = true;
      return true;
    }
  };


  // public final IAction turnRightSmall = new IAction(){
  //   @Override
  //   public boolean doAction(){
  //     try{
  //       if (turn == null)
  //         turn = new Turn();
  //       turn.right("small");
  //       }
  //     catch(IOException ex){}
  //     catch(InterruptedException EX){}
  //     return true;
  //   }
  // };
  //
  // public final IAction turnLeftSmall = new IAction(){
  //   @Override
  //   public boolean doAction(){
  //     try{
  //       if (turn == null)
  //         turn = new Turn();
  //       turn.left("small");
  //       }
  //     catch(IOException ex){}
  //     catch(InterruptedException EX){}
  //     return true;
  //   }
  // };
  //
  // public final IAction turnRightBig = new IAction(){
  //   @Override
  //   public boolean doAction(){
  //     try{
  //       if (turn == null)
  //         turn = new Turn();
  //       turn.right("big");
  //       }
  //     catch(IOException ex){}
  //     catch(InterruptedException EX){}
  //     return true;
  //   }
  // };
  //
  // public final IAction turnLeftBig = new IAction(){
  //   @Override
  //   public boolean doAction(){
  //     try{
  //       if (turn == null)
  //         turn = new Turn();
  //       turn.left("big");
  //       }
  //     catch(IOException ex){}
  //     catch(InterruptedException EX){}
  //     return true;
  //   }
  // };

}
