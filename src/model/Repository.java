package model;

import java.util.List;
import model.Note;

public interface Repository {
    List<Note> getAllNotes();
    String CreateNote(Note note);
    Note updateNote(Note note) throws Exception;
    Note readNote(String noteId) throws Exception;
    void deleteNote(Note note) throws Exception;
}
