package discount.calculator;

import java.util.ArrayList;

public class DiscountRecipient {

	static ArrayList<String> list = new ArrayList<String>();
	
	//For Builder Pattern
	
	private String name;
	private String phone;
	private int dicount;

	/**
	 * @param name
	 * @param phone
	 * @param dicount
	 */
	public DiscountRecipient(Builder builder) {
		this.name = builder.name;
		this.phone = builder.phone;
		this.dicount = builder.dicount;
		list.add(phone);
	}

	@Override
	public String toString() {
		return "DiscountRecipient [name=" + name + ", phone=" + phone + ", dicount=" + dicount + "]";
	}

	public static class Builder {

		private String name;
		private String phone;
		private int dicount;

		public Builder() {

		}

		public Builder name(String value) {
			this.name = value;
			return this;
		}

		public Builder phone(String value) {
			this.phone = value;
			return this;
		}

		public Builder dicount(int value) {
			this.dicount = value;
			return this;
		}
		
		public DiscountRecipient build(){
			return new DiscountRecipient(this);
		}

	}

	public static ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	
	
}
