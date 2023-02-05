package view;

import controller.Controller;
import logger.Loggable;
import model.Note;

import java.util.List;
import java.util.Scanner;

public class View {
    private Controller controller;
    private Loggable logger;

    public View(Controller controller, Loggable logger) {
        this.controller = controller;
        this.logger = logger;
    }


    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Выберите команду:\n READ - Показать заметку\n CREATE - Создать заметку\n UPDATE - Обновить заметку\n LIST - Прочесть все\n DELETE - Удалить\n EXIT - выход\n", true);
            try {
                com = Commands.valueOf(command);
            } catch (IllegalArgumentException e) {
                System.out.println("Неизвестная команда");
                logger.log(" Некорректная команда.");
                continue;
            }

            try {
                switch (com) {
                    case CREATE:
                        Note note = setNote(false);
                        logger.log(" Выбрано - Создание заметки.");
                        controller.saveNote(note);
                        logger.log(" Заметка создана. ");
                        break;
                    case READ:
                        String id = prompt(" Введите ID заметки: ", false);
                        logger.log(" Выбрано - чтение заметки по ID: " + id);
                        Note readedNote = controller.readNote(id);
                        System.out.println(readedNote);
                        logger.log(" Заметка выведена.");
                        break;
                    case LIST:
                        System.out.println(" Вывод заметок: \n");
                        logger.log(" Выбрано - показ всех заметок. ");
                        List<Note> noteList = controller.readNoteList();
                        for (Note u : noteList) {
                            System.out.println(u+"\n");
                        }
                        break;
                    case UPDATE:
                        logger.log(" Выбран ID заметки для обновления. ");
                        Note updateNote = setNote(true);
                        controller.updateNote(updateNote);
                        System.out.println(" Заметка обновлена. \n");
                        logger.log(" Заметка обновлена. ");
                        break;
                    case DELETE:
                        String noteId = prompt(" Введите ID заметки: ", false);
                        logger.log(" Выбрано - удаление заметки по ID: \"" + noteId);
                        Note deleteNote = controller.readNote(noteId);
                        controller.deleteNote(deleteNote);
                        System.out.println(" Заметка удалена. \n");
                        logger.log(" Заметка удалена. ");
                        break;
                    case EXIT:
                        return;
                    case NONE:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                logger.log(" Ошибка: " + e.getMessage());

            }
        }
    }

    private String prompt(String message, Boolean upCase) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        if (upCase) {
            return in.nextLine().toUpperCase();
        } else {
            return in.nextLine();
        }
    }

    private Note setNote(boolean forUpdate) {
        String idString = "";
        if (forUpdate) {
            idString = prompt("Id: ", false);

        }

        String title = prompt("Название: ", false);
        String fieldString = prompt("Содержание: ", false);
        String dataString = prompt("Дата: ", false);
        if (forUpdate) {
            return new Note(idString, title, fieldString, dataString);
        }
        return new Note(title, fieldString, dataString);

    }
}
