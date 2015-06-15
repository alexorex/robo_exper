import java.lang.ProcessBuilder;
import java.lang.Process;
import java.io.IOException;

import ru.nsu.alife.fs.FS;
import ru.nsu.alife.fs.IAcceptor;
import ru.nsu.alife.fs.IAction;
import ru.nsu.alife.fs.IFS;
import ru.nsu.alife.fs.PredicateSet;

public class Mover{

  Turn turn;

  public final IAction moveAhead = new IAction(){
    @Override
    public boolean doAction(){
      try{
        ProcessBuilder pbl = new ProcessBuilder("gz", "joint", "-m",
          "eater", "-j", "left_wheel_hinge", "--vel-t", "5");
        ProcessBuilder pbr = new ProcessBuilder("gz", "joint", "-m",
            "eater", "-j", "right_wheel_hinge", "--vel-t", "5");

        pbl.start();
      	pbr.start();
      }
      catch(IOException ex){}
      return true;
    }
  };

  public final IAction moveBack = new IAction(){
    @Override
    public boolean doAction(){
      try{
        ProcessBuilder pbl = new ProcessBuilder("gz", "joint", "-m",
          "eater", "-j", "left_wheel_hinge", "--vel-t", "-3");
        ProcessBuilder pbr = new ProcessBuilder("gz", "joint", "-m",
          "eater", "-j", "right_wheel_hinge", "--vel-t", "-3");

        pbl.start();
      	pbr.start();
      }
      catch(IOException ex){}
      return true;
    }
  };

  public final IAction turnRightSmall = new IAction(){
    @Override
    public boolean doAction(){
      try{
        if (turn == null)
          turn = new Turn();
        turn.right("small");
        }
      catch(IOException ex){}
      catch(InterruptedException EX){}
      return true;
    }
  };

  public final IAction turnLeftSmall = new IAction(){
    @Override
    public boolean doAction(){
      try{
        if (turn == null)
          turn = new Turn();
        turn.left("small");
        }
      catch(IOException ex){}
      catch(InterruptedException EX){}
      return true;
    }
  };

  public final IAction turnRightBig = new IAction(){
    @Override
    public boolean doAction(){
      try{
        if (turn == null)
          turn = new Turn();
        turn.right("big");
        }
      catch(IOException ex){}
      catch(InterruptedException EX){}
      return true;
    }
  };

  public final IAction turnLeftBig = new IAction(){
    @Override
    public boolean doAction(){
      try{
        if (turn == null)
          turn = new Turn();
        turn.left("big");
        }
      catch(IOException ex){}
      catch(InterruptedException EX){}
      return true;
    }
  };

}
