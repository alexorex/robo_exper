import java.lang.ProcessBuilder;
import java.lang.Process;
import java.io.IOException;

import ru.nsu.alife.fs.FS;
import ru.nsu.alife.fs.IAcceptor;
import ru.nsu.alife.fs.IAction;
import ru.nsu.alife.fs.IFS;
import ru.nsu.alife.fs.PredicateSet;

public class Mover{

  // ProcessBuilder st_l;
  // ProcessBuilder st_r;
  // ProcessBuilder fw_l;
  // ProcessBuilder fw_r;
  // ProcessBuilder bk_l;
  // ProcessBuilder bk_r;
  // ProcessBuilder lf_l;
  // ProcessBuilder lf_r;
  // ProcessBuilder rt_l;
  // ProcessBuilder rt_r;
  //
  // // public final IAction stop, moveAhead, moveBack, turnRight, turnLeft;
  //
  // void Mover(){
  //   ProcessBuilder st_l = new ProcessBuilder("gz", "joint", "-m",
  //   "eater", "-j", "left_wheel_hinge", "--vel-t", "0");
  //   ProcessBuilder st_r = new ProcessBuilder("gz", "joint", "-m",
  //   "eater", "-j", "right_wheel_hinge", "--vel-t", "0");
  //   ProcessBuilder fw_l = new ProcessBuilder("gz", "joint", "-m",
  //   "eater", "-j", "left_wheel_hinge", "--vel-t", "9");
  //   ProcessBuilder fw_r = new ProcessBuilder("gz", "joint", "-m",
  //   "eater", "-j", "right_wheel_hinge", "--vel-t", "9");
  //   ProcessBuilder bk_l = new ProcessBuilder("gz", "joint", "-m",
  //   "eater", "-j", "left_wheel_hinge", "--vel-t", "-3");
  //   ProcessBuilder bk_r = new ProcessBuilder("gz", "joint", "-m",
  //   "eater", "-j", "right_wheel_hinge", "--vel-t", "-3");
  //   ProcessBuilder lf_l = new ProcessBuilder("gz", "joint", "-m",
  //   "eater", "-j", "left_wheel_hinge", "--vel-t", "-3");
  //   ProcessBuilder lf_r = new ProcessBuilder("gz", "joint", "-m",
  //   "eater", "-j", "right_wheel_hinge", "--vel-t", "3");
  //   ProcessBuilder rt_l = new ProcessBuilder("gz", "joint", "-m",
  //   "eater", "-j", "left_wheel_hinge", "--vel-t", "3");
  //   ProcessBuilder rt_r = new ProcessBuilder("gz", "joint", "-m",
  //   "eater", "-j", "right_wheel_hinge", "--vel-t", "-3");
  // }

  public final IAction stop = new IAction(){
    @Override
    public boolean doAction(){
      try{
        ProcessBuilder st_l = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "left_wheel_hinge", "--vel-t", "0");
        ProcessBuilder st_r = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "right_wheel_hinge", "--vel-t", "0");
        // java.lang.Runtime.getRuntime().exec("gz joint -m " +
        //   "eater -j left_wheel_hinge --vel-t 0");
        // java.lang.Runtime.getRuntime().exec("gz joint -m " +
        //   "eater -j right_wheel_hinge --vel-t 0");
        st_l.start();
        st_r.start();

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
        ProcessBuilder fw_l = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "left_wheel_hinge", "--vel-t", "9");
        ProcessBuilder fw_r = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "right_wheel_hinge", "--vel-t", "9");
        // java.lang.Runtime.getRuntime().exec("gz joint -m " +
        //   "eater -j left_wheel_hinge --vel-t 5");
        // java.lang.Runtime.getRuntime().exec("gz joint -m " +
        //   "eater -j right_wheel_hinge --vel-t 5");
        // ProcessBuilder pbl = new ProcessBuilder("gz", "joint", "-m",
        //   "eater", "-j", "left_wheel_hinge", "--vel-t", "5");
        // ProcessBuilder pbr = new ProcessBuilder("gz", "joint", "-m",
        //     "eater", "-j", "right_wheel_hinge", "--vel-t", "5");
        //
        // pbl.start();
      	// pbr.start();
        fw_l.start();
        fw_r.start();
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
        ProcessBuilder bk_l = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "left_wheel_hinge", "--vel-t", "-3");
        ProcessBuilder bk_r = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "right_wheel_hinge", "--vel-t", "-3");
        // java.lang.Runtime.getRuntime().exec("gz joint -m " +
        //   "eater -j left_wheel_hinge --vel-t -3");
        // java.lang.Runtime.getRuntime().exec("gz joint -m " +
        //   "eater -j right_wheel_hinge --vel-t -3");
        // ProcessBuilder pbl = new ProcessBuilder("gz", "joint", "-m",
        //   "eater", "-j", "left_wheel_hinge", "--vel-t", "-3");
        // ProcessBuilder pbr = new ProcessBuilder("gz", "joint", "-m",
        //   "eater", "-j", "right_wheel_hinge", "--vel-t", "-3");
        //
        // pbl.start();
      	// pbr.start();
        bk_l.start();
        bk_r.start();
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
        ProcessBuilder rt_l = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "left_wheel_hinge", "--vel-t", "3");
        ProcessBuilder rt_r = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "right_wheel_hinge", "--vel-t", "-3");
        // java.lang.Runtime.getRuntime().exec("gz joint -m " +
        //   "eater -j left_wheel_hinge --vel-t 3");
        // java.lang.Runtime.getRuntime().exec("gz joint -m " +
        //   "eater -j right_wheel_hinge --vel-t -3");
        rt_l.start();
        rt_r.start();
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
        ProcessBuilder lf_l = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "left_wheel_hinge", "--vel-t", "-3");
        ProcessBuilder lf_r = new ProcessBuilder("gz", "joint", "-m",
        "eater", "-j", "right_wheel_hinge", "--vel-t", "3");
        // java.lang.Runtime.getRuntime().exec("gz joint -m " +
        //   "eater -j left_wheel_hinge --vel-t -3");
        // java.lang.Runtime.getRuntime().exec("gz joint -m " +
        //   "eater -j right_wheel_hinge --vel-t 3");
        lf_l.start();
        lf_r.start();
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
