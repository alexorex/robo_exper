import java.util.LinkedList;
import java.util.List;

import ru.nsu.alife.fs.FS;
import ru.nsu.alife.fs.IAcceptor;
import ru.nsu.alife.fs.IAction;
import ru.nsu.alife.fs.IFS;
import ru.nsu.alife.fs.PredicateSet;

class Ctrl_Sys extends IAcceptor{

  SurroundingScanner sensor;
  Mover mvr;
  double proactivity = 0.5;

  IAction moveAhead;
  IAction moveBack;
  IAction turnRightSmall;
  IAction turnLeftSmall;
  IAction turnRightBig;
  IAction turnLeftBig;

  Ctrl_Sys(){
    sensor = new SurroundingScanner();

    Mover mvr = new Mover();
    moveAhead = mvr.moveBack;
    moveBack = mvr.moveAhead;
    turnRightSmall = mvr.turnRightSmall;
    turnLeftSmall = mvr.turnLeftSmall;
    turnRightBig = mvr.turnRightBig;
    turnLeftBig = mvr.turnLeftBig;
  }

  @Override
  public PredicateSet getCurrentSituation() {
    Integer[] surrounding;
    PredicateSet situation = new PredicateSet();
    surrounding = sensor.sensorOut();

    situation.set(Predicate.FoodEaten, FoodCtrl.foodEaten == true);

    situation.set(Predicate.FoodAheadFar, surrounding[1] == -3);
    situation.set(Predicate.FoodAheadLeftFar, surrounding[2] == -3);
    situation.set(Predicate.FoodAheadRightFar, surrounding[0] == -3);
    situation.set(Predicate.FoodAheadClose, surrounding[1] == -2);
    situation.set(Predicate.FoodAheadLeftClose, surrounding[2] == -2);
    situation.set(Predicate.FoodAheadRightClose, surrounding[0] == -2);

    situation.set(Predicate.FoodBehindFar, surrounding[7] == -3);
    situation.set(Predicate.FoodBehindLeftFar, surrounding[8] == -3);
    situation.set(Predicate.FoodBehindRightFar, surrounding[6] == -3);
    situation.set(Predicate.FoodBehindClose, surrounding[7] == -2);
    situation.set(Predicate.FoodBehindLeftClose, surrounding[8] == -2);
    situation.set(Predicate.FoodBehindRightClose, surrounding[6] == -2);

    situation.set(Predicate.FoodLeftFar, surrounding[3] == -3 ||
    surrounding[4] == -3 || surrounding[5] == -3);
    situation.set(Predicate.FoodLeftClose, surrounding[3] == -2 ||
    surrounding[4] == -2 || surrounding[5] == -2);
    situation.set(Predicate.FoodRightFar, surrounding[9] == -3 ||
    surrounding[10] == -3 || surrounding[11] == -3);
    situation.set(Predicate.FoodRightClose, surrounding[9] == -2 ||
    surrounding[10] == -2 || surrounding[11] == -2);

    situation.set(Predicate.WallAheadFar, surrounding[1] == 3);
    situation.set(Predicate.WallAheadLeftFar, surrounding[2] == 3);
    situation.set(Predicate.WallAheadRightFar, surrounding[0] == 3);
    situation.set(Predicate.WallAheadClose, surrounding[1] == 2);
    situation.set(Predicate.WallAheadLeftClose, surrounding[2] == 2);
    situation.set(Predicate.WallAheadRightClose, surrounding[0] == 2);
    situation.set(Predicate.WallBehindFar, surrounding[7] == 3);
    situation.set(Predicate.WallBehindLeftFar, surrounding[8] == 3);
    situation.set(Predicate.WallBehindRightFar, surrounding[6] == 3);
    situation.set(Predicate.WallBehindClose, surrounding[7] == 2);
    situation.set(Predicate.WallBehindLeftClose, surrounding[8] == 2);
    situation.set(Predicate.WallBehindRightClose, surrounding[6] == 2);

    return situation;
  }

  @Override
  public IAction getRandomAction() {
    List<IAction> actions = new LinkedList<IAction>();

    actions.add(turnLeftSmall);
    actions.add(turnRightSmall);

    if(sensor.ray_s_cond[1] != 1)
      actions.add(moveAhead);
    if(sensor.ray_s_cond[7] != 1)
      actions.add(moveBack);
    if(sensor.ray_s_cond[4] != 1){
      actions.add(turnLeftBig);
    }
    if(sensor.ray_s_cond[10] != 1){
      actions.add(turnRightBig);
    }

    IAction action = actions.get((int) (Math.random() * actions.size()));

    return action;
  }

  @Override
  public double getProactivity(){
    return proactivity;
  }

  void main(String[] args){



  }
}
