package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cat extends Pet {

    public Cat() {
        super();
    }

    public Cat(String name, String stringBirthDate, String stringCommands) {
        super(name, stringBirthDate, stringCommands);
    }

    public Cat(String name, LocalDate birthDate, ArrayList<String> commandsList) {
        super(name, birthDate, commandsList);
    }
}
