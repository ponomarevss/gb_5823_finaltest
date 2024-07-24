package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class PackAnimal extends Animal {
    public PackAnimal() {
        super();
    }

    public PackAnimal(String name, String stringBirthDate, String stringCommands) {
        super(name, stringBirthDate, stringCommands);
    }

    public PackAnimal(String name, LocalDate birthDate, ArrayList<String> commandsList) {
        super(name, birthDate, commandsList);
    }
}
