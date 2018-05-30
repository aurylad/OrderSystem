package autowire;

import org.springframework.beans.factory.annotation.Autowired;

public class Cleaner {
	private PersonalData personalData;
	private String name;
	private String lastName;

	/**
	 * @param personalData
	 * @param lastName
	 */
	@Autowired
	public Cleaner(PersonalData personalData) {
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
		return "Cleaner [personalData=" + personalData.toString() + ", name=" + name + ", lastName=" + lastName + "]";
	}

}
