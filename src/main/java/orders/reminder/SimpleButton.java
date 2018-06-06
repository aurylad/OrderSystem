package orders.reminder;

public class SimpleButton implements Button {
	private String greyButtom;

	/* (non-Javadoc)
	 * @see orders.reminder.Button#setButton()
	 */
	@Override
	public String setButton() {
		// Nebuvo galima mygtuko pakeitimą atlikti šiame metode, nes javafx neleidžia
		// jokių pakeitimų, ne controller klasėje. Todėl buvo pasirinktas toks
		// variantas, su gražinama reikšme
		greyButtom = "-fx-background-color: \r\n" + "        #3c7fb1,\r\n"
				+ "        linear-gradient(#fafdfe, #e8f5fc),\r\n"
				+ "        linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #d1e0e0 100%);\r\n"
				+ "    -fx-background-insets: 0,1,2;\r\n" + "    -fx-background-radius: 3,2,1;\r\n"
				+ "    -fx-padding: 3 30 3 30;\r\n" + "    -fx-text-fill: black;\r\n" + "    -fx-font-size: 16px;";
		return greyButtom;
	}
}
