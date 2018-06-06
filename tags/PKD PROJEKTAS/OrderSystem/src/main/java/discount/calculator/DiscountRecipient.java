package discount.calculator;

public class DiscountRecipient {

	// For Builder Pattern

	private String phone;
	private int dicount;

	/**
	 * @param name
	 * @param phone
	 * @param dicount
	 */
	/**
	 * @param builder
	 */
	public DiscountRecipient(Builder builder) {
		this.phone = builder.phone;
		this.dicount = builder.dicount;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DiscountRecipient [phone=" + phone + ", dicount=" + dicount + "]";
	}

	public static class Builder {

		private String phone;
		private int dicount;

		public Builder() {

		}

		public Builder phone(String value) {
			this.phone = value;
			return this;
		}

		public Builder dicount(int value) {
			this.dicount = value;
			return this;
		}

		public DiscountRecipient build() {
			return new DiscountRecipient(this);
		}

	}

	/**
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return
	 */
	public int getDicount() {
		return dicount;
	}

	/**
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param dicount
	 */
	public void setDicount(int dicount) {
		this.dicount = dicount;
	}
	
	
	

}
