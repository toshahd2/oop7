package model;

import java.util.List;
import logger.Loggable;
import model.Note;
import model.Mapper;

public class RepositoryActions implements Repository {
    private Repository repository;
    private Loggable logger;
    protected Mapper mapper = new Mapper();
    private Operation fileOperation;

    public RepositoryActions(Repository repository, Loggable logger, Operation fileOperation) {
        this.repository = repository;
        this.logger = logger;
        this.fileOperation = fileOperation;
    }
    @Override
    public List<Note> getAllNotes() {
        logger.log(" Просмотр всех заметок. ");
        List<Note> notes = repository.getAllNotes();
        return notes;
    }

    @Override
    public String CreateNote(Note note) {
        String result;
        logger.log(" Создание заметки. ");
        result = repository.CreateNote(note);
        return result;
    }

    @Override
    public Note updateNote(Note note) throws Exception {
        logger.log(" Обновление заметки. ");
        Note noteUpdate = repository.updateNote(note);
        return noteUpdate;
    }

    @Override
    public Note readNote(String noteId) throws Exception {
        logger.log(" Открытие заметки по ID. ");
        Note noteRead = repository.readNote(noteId);
        return noteRead;
    }

    @Override
    public void deleteNote(Note note) throws Exception {
        logger.log(" Удаление заметки по ID. ");
        repository.deleteNote(note);
    }
}
