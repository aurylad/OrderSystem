package manager.notes;

import java.io.IOException;

import custom.exception.EmptyNoteFieldExceptions;

//Mediator Pattern
public class User {
	private String name;

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param name
	 */
	public User(String name) {
		this.name = name;
	}

	/**
	 * @param note
	 * @throws IOException
	 * @throws EmptyNoteFieldExceptions
	 */
	public void sendNote(String note) throws IOException, EmptyNoteFieldExceptions {
		Note.buildNote(this, note);
	}
}