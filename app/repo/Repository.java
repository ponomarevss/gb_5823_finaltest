package app.repo;

import app.model.Animal;

import java.util.List;

public interface Repository {
    List<Animal> getAnimals();
}
