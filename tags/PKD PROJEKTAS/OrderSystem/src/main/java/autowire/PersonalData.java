package autowire;

public class PersonalData {
	private String posting;
	private String workExperience;

	/**
	 * @return
	 */
	public String getPosting() {
		return posting;
	}

	/**
	 * @return
	 */
	public String getWorkExperience() {
		return workExperience;
	}

	/**
	 * @param posting
	 */
	public void setPosting(String posting) {
		this.posting = posting;
	}

	/**
	 * @param workExperience
	 */
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonalData [posting=" + posting + ", workExperience=" + workExperience + "]";
	}

}
