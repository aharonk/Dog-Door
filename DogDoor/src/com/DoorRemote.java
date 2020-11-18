package com;

public class DoorRemote {
//maybe change getIsOpen() to displayIsOpen(), it may be clearer


    private BarkRecognizer barkRecognizer;
    private DogDoor dogDoor;


    //must be assigned to door
    private DoorRemote() {
    }

    public DoorRemote(BarkRecognizer barkrecognizer, DogDoor dogDoor) {
        this.dogDoor = dogDoor;
        this.barkRecognizer = barkrecognizer;
    }

    // Ask the door to open
    public void pushButton() {
//isOpen = true? close():open();
        if (DogDoor.isDoorOpen()) {
            close();
        } else {
            open();
        }
    }

    // Ask door to close
    private void open() {
        dogDoor.openDoor();
        dogDoor.timerClose();
    }

    // Ask the door to close
    private void close() {
        dogDoor.closeDoor();
    }

    // Getter for isOpen
    public void getIsOpen() {
        //System.out.println(isOpen = true ? “The door is open.": "The door is closed");
        if (DogDoor.isDoorOpen()) {
            System.out.println("The door is open.");
        } else {
            System.out.println("The door is closed");
        }
    }

    // Getter for isDogIn
    public void getDogIn() {
        for (boolean isDogIn : dogDoor.areDogsIn()) {
            if (isDogIn) {
                System.out.println("The dog is inside.");
            } else {
                System.out.println("The dog is outside.");
            }
        }
    }

    public void addBarkKey(Dog dog) {
        dogDoor.addDog(dog);
    }

    public void addBarkKey(String name, String bark, boolean position) {
        dogDoor.addDog(new Dog(name, bark, position));
    }

    public void setDoorDelay(int seconds) {
        dogDoor.setDoorLockDelay(seconds);
    }

    public static void announceScratch() {
        System.out.println("Scratching at door");
    }

    public void newOwner() {
        dogDoor.clearDogs();
    }
}

