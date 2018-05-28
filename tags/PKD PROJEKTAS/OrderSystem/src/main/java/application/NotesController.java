package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
		User notesUser = new User(nameTextField.getText());
		notesUser.sendNote(noteTextArea.getText());
		notesListView.setItems(Note.getNotesObservableList());
	}

}
