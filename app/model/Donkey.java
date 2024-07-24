package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Donkey extends PackAnimal {
    public Donkey() {
        super();
    }

    public Donkey(String name, String stringBirthDate, String stringCommands) {
        super(name, stringBirthDate, stringCommands);
    }

    public Donkey(String name, LocalDate birthDate, ArrayList<String> commandsList) {
        super(name, birthDate, commandsList);
    }
}
