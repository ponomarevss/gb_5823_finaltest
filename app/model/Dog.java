package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Dog extends Pet {
    public Dog() {
        super();
    }

    public Dog(String name, String stringBirthDate, String stringCommands) {
        super(name, stringBirthDate, stringCommands);
    }

    public Dog(String name, LocalDate birthDate, ArrayList<String> commandsList) {
        super(name, birthDate, commandsList);
    }
}
