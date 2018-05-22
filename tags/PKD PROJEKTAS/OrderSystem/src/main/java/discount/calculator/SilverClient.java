package discount.calculator;

public class SilverClient implements Discount {
	//For Factory Pattern
	@Override
	public void calculateDiscount() {
		// TODO Auto-generated method stub
		
		//Builder Pattern
		DiscountRecipient recipient = new DiscountRecipient.Builder()
				.name("Jonušas")
				.phone("865429430")
				.dicount(15)
				.build();
		
		System.out.println(recipient);
	}
	
}
