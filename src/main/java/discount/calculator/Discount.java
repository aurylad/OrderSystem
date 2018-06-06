 package discount.calculator;

import javafx.collections.ObservableList;

public interface Discount {
	//Facade Pattern (Structural)
	/**
	 * @return
	 */
	public ObservableList<DiscountRecipient> calculateDiscount();
}
