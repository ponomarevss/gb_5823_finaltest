package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pet extends Animal {
    public Pet() {
        super();
    }

    public Pet(String name, String stringBirthDate, String stringCommands) {
        super(name, stringBirthDate, stringCommands);
    }

    public Pet(String name, LocalDate birthDate, ArrayList<String> commandsList) {
        super(name, birthDate, commandsList);
    }
}
