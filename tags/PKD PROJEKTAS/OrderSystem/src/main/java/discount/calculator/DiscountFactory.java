package discount.calculator;

public class DiscountFactory {

	public Discount getClientType(String clientType){

		if (clientType.equalsIgnoreCase("SILVERCLIENT")) {
			return new SilverClient();
		}else if (clientType.equalsIgnoreCase("GOLDCLIENT")) {
			return new GoldClient();
		}
		
		return null;
	}
}
