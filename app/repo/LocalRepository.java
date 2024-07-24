package app.repo;

import app.model.*;

import java.util.ArrayList;
import java.util.List;

public class LocalRepository implements Repository {

    @Override
    public List<Animal> getAnimals() {
        ArrayList<Animal> animals = new ArrayList<>(32);
        animals.add(new Dog("Fido", "2020-01-01", "Sit, Stay, Fetch"));
        animals.add(new Cat("Whiskers", "2019-05-15", "Sit, Pounce"));
        animals.add(new Hamster("Hammy", "2021-03-10", "Roll, Hide"));
        animals.add(new Dog("Buddy", "2018-12-10", "Sit, Paw, Bark"));
        animals.add(new Cat("Smudge", "2020-02-20", "Sit, Pounce, Scratch"));
        animals.add(new Hamster("Peanut", "2021-08-01", "Roll, Spin"));
        animals.add(new Dog("Bella", "2019-11-11", "Sit, Stay, Roll"));
        animals.add(new Cat("Oliver", "2020-06-30", "Meow, Scratch, Jump"));
        animals.add(new Horse("Thunder", "2015-07-21", "Trot, Canter, Gallop"));
        animals.add(new Camel("Sandy", "2016-11-03", "Walk, Carry Load"));
        animals.add(new Donkey("Eeyore", "2017-09-18", "Walk, Carry Load, Bray"));
        animals.add(new Horse("Storm", "2014-05-05", "Trot, Canter"));
        animals.add(new Camel("Dune", "2018-12-12", "Walk, Sit"));
        animals.add(new Donkey("Burro", "2019-01-23", "Walk, Bray, Kick"));
        animals.add(new Horse("Blaze", "2016-02-29", "Trot, Jump, Gallop"));
        animals.add(new Camel("Sahara", "2015-08-14", "Walk, Run"));
        return animals;
    }
}
