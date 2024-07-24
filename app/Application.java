package app;

import app.repo.LocalRepository;
import app.repo.Repository;
import app.view.ConsoleView;
import app.view.View;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            View view = new ConsoleView(scanner);
            Repository repository = new LocalRepository();
            Presenter presenter = new Presenter(view, repository);
            presenter.init();
        }
    }
}
