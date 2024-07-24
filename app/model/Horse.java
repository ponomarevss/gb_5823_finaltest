package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Horse extends PackAnimal {
    public Horse() {
        super();
    }

    public Horse(String name, String stringBirthDate, String stringCommands) {
        super(name, stringBirthDate, stringCommands);
    }

    public Horse(String name, LocalDate birthDate, ArrayList<String> commandsList) {
        super(name, birthDate, commandsList);
    }
}
