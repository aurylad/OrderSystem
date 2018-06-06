package databse.tables;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Supplier {

	// lauku reiksmes panaudotos iprasminti scope = "singleton" panaudojima
	private Integer companyCode;
	private String companyName = "Nenurodyta";
	private String country = "Nenurodyta";
	private String address = "Nenurodyta";
	private String phoneNumber = "Nenurodyta";
	private String person = "Nenurodyta";

	/**
	 * @return
	 */
	@Id
	public Integer getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode
	 */
	public void setCompanyCode(Integer companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * @return
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return
	 */
	public String getPerson() {
		return person;
	}

	/**
	 * @param person
	 */
	public void setPerson(String person) {
		this.person = person;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Supplier [companyCode=" + companyCode + ", companyName=" + companyName + ", country=" + country
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", person=" + person + "]";
	}

	
	
}
