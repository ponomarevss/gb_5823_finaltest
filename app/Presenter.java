package app;

import app.model.*;
import app.repo.Repository;
import app.view.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Presenter {
    private final View view;
    private final Repository repository;
    private List<Animal> animals;
    private Animal pendingAnimal;
    private boolean isCanceled;


    public Presenter(View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void init() {
        animals = repository.getAnimals();
        animals.sort(Comparator.comparing(Animal::getBirthDate));
        showMainMenu();
    }

    private void showMainMenu() {
        String menu = "Main menu: 1. Show all, 2. Show stats, 3. Add animal, 4. Edit animal, 0. Quit";
        String invitation = "Choose a main menu option: ";
        view.set(menu);
        String choice = view.get(invitation);
        while (!choice.equals("0")) {
            switch (choice) {
                case "1":
                    showAll();
                    break;
                case "2":
                    showStats();
                    break;
                case "3":
                    addAnimal();
                    break;
                case "4":
                    showEditAnimalMenu();
                    break;
                default:
                    view.set("Illegal option argument");
            }
            view.set(menu);
            choice = view.get(invitation);
        }
    }

    private void showAll() {
        view.set("Here is the full list:");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private void showStats() {
        int dogs = 0, cats = 0, hamsters = 0, horses = 0, camels = 0, donkeys = 0, pets = 0, packs = 0;
        for (Animal animal : animals) {
            if (animal instanceof Dog) {
                dogs++;
            } else if (animal instanceof Cat) {
                cats++;
            } else if (animal instanceof Hamster) {
                hamsters++;
            } else if (animal instanceof Horse) {
                horses++;
            } else if (animal instanceof Camel) {
                camels++;
            } else if (animal instanceof Donkey) {
                donkeys++;
            }
            if (animal instanceof Pet) {
                pets++;
            } else if (animal instanceof PackAnimal) {
                packs++;
            }
        }
        view.set("Here is some statistics: ");
        view.set("We've got " + animals.size() + " animals total, " +
                pets + " of them are pets, and " +
                packs + " of them are pack animals.");
        view.set("Pets are: " + dogs + " dogs, " + cats + " cats, and " + hamsters + " hamsters.");
        view.set("Pack animals are: " + horses + " horses, " + camels + " camels, and " + donkeys + " donkeys.");
    }

    private void addAnimal() {
        String menu = "Add animal menu: 1. Dog, 2. Cat, 3. Hamster, 4. Horse, 5. Camel, 6. Donkey, 0. Back to main menu";
        String invitation = "Select animal to add: ";

        view.set(menu);
        String choice = view.get(invitation);

        while (!choice.equals("0")) {
            switch (choice) {
                case "1":
                    createDog();
                    break;
                case "2":
                    createCat();
                    break;
                case "3":
                    createHamster();
                    break;
                case "4":
                    createHorse();
                    break;
                case "5":
                    createCamel();
                    break;
                case "6":
                    createDonkey();
                    break;
                default:
                    view.set("Illegal option argument");
            }
            view.set(menu);
            choice = view.get(invitation);
        }
    }

    private void createDog() {
        pendingAnimal = new Dog();
        fillDataAndAdd();
    }

    private void createCat() {
        pendingAnimal = new Cat();
        fillDataAndAdd();
    }

    private void createHamster() {
        pendingAnimal = new Hamster();
        fillDataAndAdd();
    }

    private void createHorse() {
        pendingAnimal = new Horse();
        fillDataAndAdd();
    }

    private void createCamel() {
        pendingAnimal = new Camel();
        fillDataAndAdd();
    }

    private void createDonkey() {
        pendingAnimal = new Donkey();
        fillDataAndAdd();
    }

    private void fillDataAndAdd() {
        isCanceled = false;
        setPendingAnimalName();
        setPendingAnimalBirthDate();
        setPendingAnimalCommands();
        if (!isCanceled) {
            animals.add(pendingAnimal);
            view.set(String.format("Animal created: %s", pendingAnimal));
        }
    }

    private void setPendingAnimalName() {
        String value = view.get("Set "
                + pendingAnimal.getClass().getSimpleName().toLowerCase()
                + " name ('0' to cancel): ");
        if (value.equals("0")) {
            isCanceled = true;
        } else if (value.isEmpty()) {
            view.set("Name cannot be empty!");
            setPendingAnimalName();
        } else {
            pendingAnimal.setName(value);
        }
    }

    private void setPendingAnimalBirthDate() {
        if (isCanceled) return;
        String value = view.get("Set "
                + pendingAnimal.getName()
                + " birth date in format 'yyyy-MM-dd' ('0' to cancel): ");
        if (value.equals("0")) {
            isCanceled = true;
            return;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            pendingAnimal.setBirthDate(LocalDate.parse(value, formatter));
        } catch (DateTimeParseException e) {
            view.set(e.getMessage());
            setPendingAnimalBirthDate();
        }
    }

    private void setPendingAnimalCommands() {
        if (isCanceled) return;
        String value = view.get("List commands "
                + pendingAnimal.getName()
                + " knows; divide them with coma (',') ('0' to cancel, leave empty to skip for now): ");
        if (value.equals("0")) {
            isCanceled = true;
        } else {
            pendingAnimal.addCommands(value);
        }
    }

    private void showEditAnimalMenu() {
        List<Integer> filteredIndices = new ArrayList<>();
        if (setIndicesByType(filteredIndices)) return;

        for (int index : filteredIndices) {
            view.set(String.format("%s\t%s", index, animals.get(index).toString()));
        }

        if (chooseAnimal(filteredIndices)) return;

        editAnimal();
        pendingAnimal = null;
    }

    private void editAnimal() {
        String invitation = "Select option: 1. Add new command,  2. Remove animal from the list, any key to get back to main menu: ";
        view.set(String.format("Edit %s", pendingAnimal.toString()));
        String choice = view.get(invitation);
        List<String> options = List.of("1", "2");
        while (options.contains(choice)) {
            switch (choice) {
                case "1":
                    String newCommands = view.get("List new commands here: ");
                    pendingAnimal.addCommands(newCommands);
                    break;
                case "2":
                    animals.remove(pendingAnimal);
                    view.set(String.format("Pending animal (%s) removed", pendingAnimal));
                    return;
            }
            view.set(String.format("Edit %s", pendingAnimal.toString()));
            choice = view.get(invitation);
        }
    }

    private boolean chooseAnimal(List<Integer> filteredIndices) {
        String choice = view.get("Select animal # to edit, or any key to get back to main menu: ");
        int index = Integer.parseInt(choice);
        if (filteredIndices.contains(index)) {
            pendingAnimal = animals.get(index);
        } else {
            view.set("Not in listed options. Back to main menu");
            return true;
        }
        return false;
    }

    private boolean setIndicesByType(List<Integer> filteredIndices) {
        String choice = view.get("Find animal by type: 1. Dog, 2. Cat, 3. Hamster, 4. Horse, 5. Camel, 6. Donkey, any key to get back to main menu: ");
        switch (choice) {
            case "1":
                filteredIndices.addAll(filterAnimalsByType("Dog"));
                break;
            case "2":
                filteredIndices.addAll(filterAnimalsByType("Cat"));
                break;
            case "3":
                filteredIndices.addAll(filterAnimalsByType("Hamster"));
                break;
            case "4":
                filteredIndices.addAll(filterAnimalsByType("Horse"));
                break;
            case "5":
                filteredIndices.addAll(filterAnimalsByType("Camel"));
                break;
            case "6":
                filteredIndices.addAll(filterAnimalsByType("Donkey"));
                break;
            default:
                view.set("Not in listed options. Back to main menu");
                return true;
        }
        return false;
    }

    private List<Integer> filterAnimalsByType(String className) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getClass().getSimpleName().equals(className)) {
                result.add(i);
            }
        }
        return result;
    }
}
