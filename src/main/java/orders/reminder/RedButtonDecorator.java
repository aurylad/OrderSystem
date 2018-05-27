package orders.reminder;

public class RedButtonDecorator extends ButtonDecorator {

	   public RedButtonDecorator(Button decoratedShape) {
	      super(decoratedShape);		
	   }

	   @Override
	   public void setButton() {
	      decoratedButton.setButton();	       
	      setRedBorder(decoratedButton);
	   }

	   private void setRedBorder(Button decoratedShape){
	      System.out.println("Border Color: Red");
	   }
	}
