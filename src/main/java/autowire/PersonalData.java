package autowire;

public class PersonalData {
	private String posting;
	private String workExperience;

	public String getPosting() {
		return posting;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setPosting(String posting) {
		this.posting = posting;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	@Override
	public String toString() {
		return "PersonalData [posting=" + posting + ", workExperience=" + workExperience + "]";
	}

}
