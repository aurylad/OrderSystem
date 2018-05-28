package manager.notes;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Note {

	static ObservableList<String> notesObservableList;

	// Mediator Pattern
	public static void buildNote(User user, String message) throws IOException {

		File file = new File("notes.txt");
		FileWriter fw = new FileWriter(file, true);
		PrintWriter pw = new PrintWriter(fw);
		// Mediator Pattern
		pw.println(new Date().toString() + " [" + user.getName() + "] : " + message);
		pw.close();

		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);

		String line;
		List<String> notesList = new ArrayList<String>();

		while ((line = reader.readLine()) != null) {
			notesList.add(line + System.lineSeparator());
		}
		notesObservableList = FXCollections.observableArrayList();
		notesObservableList.addAll(notesList);

		fileReader.close();
		reader.close();

	}

	public static ObservableList<String> getNotesObservableList() {
		return notesObservableList;
	}

}