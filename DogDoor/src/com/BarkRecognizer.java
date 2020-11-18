package com;

import java.util.*;

public class BarkRecognizer {

    private DogDoor dogDoor;

    public BarkRecognizer(DogDoor dogDoor) {
        this.dogDoor = dogDoor;
    }

    private BarkRecognizer() {}

    public ArrayList<Dog> getYourBarks() {
        return dogDoor.getDogs();
    }


    //the owner must set their animal's bark
    public void setYourBark(ArrayList<Dog> dogs) {
        dogDoor.setDogs(dogs);
    }

    //adds a set bark
    public void addBark(Dog dog){
        dogDoor.addDog(dog);
    }

    //removes a set bark
    public void removeBark(Dog dog){
        dogDoor.removeBark(dog);
    }

    public void clearBarks() {dogDoor.clearDogs(); }

    //animalSound comes from the great outdoors, no way to preset
    public void barkCheck(String animalSound) {
        for (Dog dog : dogDoor.getDogs()) {
            if (dog.getBarkSound().equals(animalSound)) {
                dogDoor.openDoor();
                dogDoor.timerClose();
            }
        }
    }

    public void checkScratch(boolean scratch) {
        if (scratch) {
            DoorRemote.announceScratch();
        }
    }
}
