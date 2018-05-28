package database.table.manager;

public class StopState implements State {

   public void doAction(Context context) {
      System.out.println("Player is in stop state");
      context.setState(this);	
   }

   public double toStrin(){
      return 55.5;
   }
}