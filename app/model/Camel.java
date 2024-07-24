package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Camel extends PackAnimal {
    public Camel() {
        super();
    }

    public Camel(String name, String stringBirthDate, String stringCommands) {
        super(name, stringBirthDate, stringCommands);
    }

    public Camel(String name, LocalDate birthDate, ArrayList<String> commandsList) {
        super(name, birthDate, commandsList);
    }
}
