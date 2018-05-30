package autowire;

public class Storekeeper {

	private PersonalData personalData;
	private String name;
	private String lastName;

	/**
	 * @param personalData
	 * @param lastName
	 */
	public Storekeeper(PersonalData personalData) {
		this.personalData = personalData;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Storekeeper [personalData=" + personalData.toString() + ", name=" + name + ", lastName=" + lastName
				+ "]";
	}

}
