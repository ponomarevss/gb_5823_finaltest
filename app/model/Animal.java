package app.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Animal {
    private String name;
    private LocalDate birthDate;
    private ArrayList<String> commandsList = new ArrayList<>();

    public Animal() {
        this.name = "";
        this.birthDate = LocalDate.MIN;
    }

    public Animal(String name, LocalDate birthDate, ArrayList<String> commandsList) {
        this.name = name;
        this.birthDate = birthDate;
        this.commandsList = commandsList;
    }

    public Animal(String name, String stringBirthDate, String stringCommands) {
        this.name = name;
        this.birthDate = stringBirthDateToLocalDate(stringBirthDate);
        this.commandsList = stringCommandsToList(stringCommands);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void addCommands(String stringCommands) {
        commandsList.addAll(stringCommandsToList(stringCommands));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\t{\t" +
                "name='" + name + '\'' +
                ", \tbirthDate=" + birthDate +
                ", \tcommandsList=" + commandsList +
                "\t}";
    }

    private ArrayList<String> stringCommandsToList(String stringCommands) {
        if (stringCommands.isEmpty()) return new ArrayList<>();
        String[] arr = stringCommands.split(",");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim().toLowerCase();
            arr[i] = arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1);
        }
        return new ArrayList<>(Arrays.asList(arr));
    }

    private LocalDate stringBirthDateToLocalDate(String stringBirthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(stringBirthDate, formatter);
    }
}
