
import controller.Controller;
import logger.Logger;
import model.FileOperation;
import model.RepositoryActions;
import model.Repository;
import model.RepositoryFile;
import view.View;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger("log.txt");
        FileOperation fileOperation = new FileOperation("notes.txt");

        Repository repository = new RepositoryFile(fileOperation);
        Repository repositoryLog = new RepositoryActions(repository, logger, fileOperation);
        Controller controller = new Controller(repositoryLog);

        View view = new View(controller, logger);
        view.run();
    }
}