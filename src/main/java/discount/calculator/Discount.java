package discount.calculator;

import javafx.collections.ObservableList;

public interface Discount {
	//Facade Pattern (Structural)
	public ObservableList<DiscountRecipient> calculateDiscount();
}
