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

	@Id
	public Integer getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(Integer companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Supplier [companyCode=" + companyCode + ", companyName=" + companyName + ", country=" + country
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", person=" + person + "]";
	}

	
	
}
