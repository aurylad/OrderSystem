package orders.reminder;

import application.MainController;

public class SimpleButton implements Button {

	   @Override
	   public void setButton() {
	      System.out.println("add simple fxml button");
	      
	      //MainController.getBtnPendingOrders().setText("everything is fine");
	   }
	}
