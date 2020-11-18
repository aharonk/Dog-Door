package com;

import java.util.*;

public class DogDoor {
    static private boolean doorOpen; //Is the door open?
    ArrayList<Boolean> dogPositions; // position dog was in last
    ArrayList<Dog> dogs; // includes bark and location
    private int doorLockDelay; //Custom delay time (in seconds)

    public DogDoor() {
        doorOpen = false;
        dogs = new ArrayList<>();
        dogPositions = new ArrayList<>();
        doorLockDelay = 6;
    }

    public DogDoor(boolean doorOpen, int doorLockDelay) {
        this.dogs = new ArrayList<>();
        DogDoor.doorOpen = doorOpen;
        this.dogPositions = new ArrayList<>();
        this.doorLockDelay = doorLockDelay;
    }

    public DogDoor(boolean doorOpen, ArrayList<Boolean> dogPositions, ArrayList<Dog> dogs, int doorLockDelay) {
        this.dogs = dogs;
        DogDoor.doorOpen = doorOpen;
        this.dogPositions = dogPositions;
        this.doorLockDelay = doorLockDelay;
    }

    @Override
    public String toString() {
        return "The door is " + (doorOpen ? "open" : "closed") +
                ".\nThe current delay before locking is " + doorLockDelay + " seconds.";
    }

    void timerClose(){
        //when the task is run, close the door
        doorTimer timer = new doorTimer();
        //having the door watcher/timer on a separate thread allows the dog to move on the main thread during the timer
        Thread doorWatcher = new Thread(timer);
        doorWatcher.start();
        //ends the timer thread once it stops doing instructions
        //doorWatcher.join();
    }

    void openDoor() {
        //open door
        for (int i = 0; i < dogs.size(); i++) {
            dogPositions.set(i, dogs.get(i).isInHouse());
        }
        doorOpen = true;
    }

    void closeDoor() {
        doorOpen = false;
    }

    void clearDogs() {
        dogs = new ArrayList<>();
        dogPositions = new ArrayList<>();
    }

    void setDogs(ArrayList<Dog> dogs) {
        this.dogs = dogs;
        for (Dog dog : dogs) {
            dogPositions.add(dog.isInHouse());
        }
    }

    public static boolean isDoorOpen() {
        return doorOpen;
    }

    public ArrayList<Boolean> areDogsIn() {
        return dogPositions;
    }

    void addDog(Dog dog) {
        dogs.add(dog);
        dogPositions.add(dog.isInHouse());
    }

    void removeBark(Dog dog) {
        dogs.remove(dog);
    }

    public ArrayList<Dog> getDogs() {return dogs;}

    public ArrayList<Boolean> getDogPositions() {return dogPositions;}

    public int getDoorLockDelay() {
        return doorLockDelay;
    }

    void setDoorLockDelay(int seconds) {
        doorLockDelay = seconds;
    }

    class doorTimer implements Runnable {

        @Override
        public void run() {
            Timer timer = new Timer("Door Lock Timer");
            TimerTask task = new TimerTask() {
                boolean keepOpen;

                @Override
                public void run() {
                    if (doorOpen) {
                        keepOpen = false;
                        for (int i = 0; i < dogs.size(); i++) {
                            if (dogs.get(i).isInHouse() != dogPositions.get(i)) {
                                System.out.println("Door is Open");
                                dogPositions.set(i, dogs.get(i).isInHouse());
                                keepOpen = true;
                                break;
                            }
                        }
                        if (!keepOpen) {
                            closeDoor();
                            System.out.println("Door is Closed");
                            timer.cancel();
                        }
                    } else {
                        timer.cancel();
                        timer.purge();
                    }
                }
            };
            timer.schedule(task, doorLockDelay*1000, doorLockDelay*1000);
        }
    }
}


