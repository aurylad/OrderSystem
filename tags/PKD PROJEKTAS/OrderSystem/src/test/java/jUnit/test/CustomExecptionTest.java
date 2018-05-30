package jUnit.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import custom.exception.EmptyNoteFieldExceptions;
import manager.notes.User;	

class CustomExecptionTest {
	
	@Test
	public void custom_created_exception_test() {
		User notesUser = new User("a");
		assertThrows(EmptyNoteFieldExceptions.class, () -> 
		notesUser.sendNote(""));
	}
}
