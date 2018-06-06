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
	/**
	 * @param personalData
	 */
	@Autowired
	public Cleaner(PersonalData personalData) {
		this.personalData = personalData;
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
	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return
	 */
	public PersonalData getPersonalData() {
		return personalData;
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
		return "Cleaner [personalData=" + personalData.toString() + ", name=" + name + ", lastName=" + lastName + "]";
	}

}
