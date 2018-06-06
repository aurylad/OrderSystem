package autowire;

public class Seller {
	private PersonalData personalData;
	private String name;
	private String lastName;

	/**
	 * @return
	 */
	public PersonalData getPersonalData() {
		return personalData;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param personalData
	 */
	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Seller [personalData=" + personalData.toString() + ", name=" + name + ", lastName=" + lastName + "]";
	}

}
