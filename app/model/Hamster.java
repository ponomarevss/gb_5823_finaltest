package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hamster extends Pet {
    public Hamster() {
        super();
    }

    public Hamster(String name, String stringBirthDate, String stringCommands) {
        super(name, stringBirthDate, stringCommands);
    }

    public Hamster(String name, LocalDate birthDate, ArrayList<String> commandsList) {
        super(name, birthDate, commandsList);
    }
}
