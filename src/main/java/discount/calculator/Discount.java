package discount.calculator;

import javafx.collections.ObservableList;

public interface Discount {
	//For Factory Pattern
	//Facade Pattern (Structural)
	public ObservableList<DiscountRecipient> calculateDiscount();
}
