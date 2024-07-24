package app.view;

import java.util.Scanner;

public class ConsoleView implements View{

    private final Scanner scanner;

    public ConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String get(String string) {
        System.out.print(string);
        return scanner.nextLine();
    }

    @Override
    public void set(String value) {
        System.out.println(value);
    }
}
