package orders.reminder;

public abstract class ButtonDecorator implements Button {
	   protected Button decoratedButton;

	   /**
	 * @param decoratedButton
	 */
	public ButtonDecorator(Button decoratedButton){
	      this.decoratedButton = decoratedButton;
	   }

	   /* (non-Javadoc)
	 * @see orders.reminder.Button#setButton()
	 */
	public String setButton(){
	      decoratedButton.setButton();
	      return  null;
	   }	
	}
