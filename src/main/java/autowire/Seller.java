package autowire;

public class Seller {
	private PersonalData personalData;
	private String name;
	private String lastName;

	public PersonalData getPersonalData() {
		return personalData;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
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
		return "Seller [personalData=" + personalData.toString() + ", name=" + name + ", lastName=" + lastName + "]";
	}

}
