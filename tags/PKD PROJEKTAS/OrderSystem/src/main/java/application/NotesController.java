package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import custom.exception.EmptyNoteFieldExceptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import manager.notes.Note;
import manager.notes.User;

public class NotesController implements Initializable {

	@FXML
	private ListView<String> notesListView;

	@FXML
	private TextArea noteTextArea;

	@FXML
	private Button saveButton;

	@FXML
	private TextField nameTextField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void saveNote() throws IOException {

		// Mediator Pattern
		// Sudaro užrašą, naudojant vartotojo vardą ir tekstą, atvaizduoja jį ListView
		User notesUser = new User(nameTextField.getText());

		try {
			notesUser.sendNote(noteTextArea.getText());
		} catch (EmptyNoteFieldExceptions e) {
			System.out.println(e);
		}

		notesListView.setItems(Note.getNotesObservableList());
	}

}
