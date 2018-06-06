package orders.reminder;

public class RedButtonDecorator extends ButtonDecorator {

	private String decoratedButtonStyle;
	private String greenButtom;

	/**
	 * @param decoratedShape
	 */
	public RedButtonDecorator(Button decoratedShape) {
		super(decoratedShape);
	}

	/* (non-Javadoc)
	 * @see orders.reminder.ButtonDecorator#setButton()
	 */
	@Override
	public String setButton() {
		decoratedButton.setButton();
		// Nebuvo galima mygtuko pakeitimą atlikti šiame metode, nes javafx neleidžia
		// jokių pakeitimų, ne controller klasėje. Todėl buvo pasirinktas toks
		// variantas, su gražinama reikšme
		greenButtom = setRedBorder(decoratedButton);
		return greenButtom;
	}

	/**
	 * @param decoratedButton
	 * @return
	 */
	private String setRedBorder(Button decoratedButton) {
		decoratedButtonStyle = "-fx-background-color: \r\n" + "        #3c7fb1,\r\n"
				+ "        linear-gradient(#fafdfe, #e8f5fc),\r\n"
				+ "        linear-gradient(#eaf6fd 0%, #b3ff99 49%, #b3ff99 50%, #66ff33 100%);\r\n"
				+ "    -fx-background-insets: 0,1,2;\r\n" + "    -fx-background-radius: 3,2,1;\r\n"
				+ "    -fx-padding: 3 30 3 30;\r\n" + "    -fx-text-fill: black;\r\n" + "    -fx-font-size: 16px;";
		return decoratedButtonStyle;
	}
}
