package databse.tables;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Table(name = "supplier") Reikalinga tik jei klases pavadinimas skiriasi nuo lenteles pavadinimo db
@Entity
public class Supplier {

	private Integer companyCode;
	private String companyName;
	private String country;
	private String address;
	private String phoneNumber;
	private String person;

	// @Column(name = "comanyCode") Reikalinga tik jei stulpelio pavadinimas
	// skiriasi nuo stulpelio pavadinimo db
	// @GeneratedValue(strategy = GenerationType.IDENTITY) reikalingas tik jei
	// duomenu bazeje stulpelis nustatytas (auto-increment)
	// @Id naudojamas kai stulpelis yra primary key
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

}
