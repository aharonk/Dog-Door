package com;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    /*The results should be:

Testing adding dogs:
Complete.

Testing the door timer:
fido can't move (wait)
Fido has moved.
Door is Open (wait)
Door is Closed (wait)
fido can't move
true
true
Complete.

Testing remote functions:
The door is closed
The door is open.
The door is closed
The dog is in.
The dog is in.
5
Complete.

recognizer functions:
The dog's name is fido.
The dog's bark is woof.
The dog's position is true.

The dog's name is Harold.
The dog's bark is arf.
The dog's position is true.

[The dog's name is Fido.
The dog's bark is woof.
The dog's position is true.
, The dog's name is Harold.
The dog's bark is arf.
The dog's position is true.]

Heard a bark
true
false
Heard a quack
false
Scratching at door.
Complete.
     */

    //This is a sample Main to demonstrate the usage of the door timer. It creates a dog, and a door, adds the dog to the
    // door and open the door, engages the timer contained in the DogDoor class.
    public static void main(String[] args) throws InterruptedException {
        Dog fido = new Dog("fido", "woof", false);
        DogDoor dogDoor = new DogDoor(false, 6);
        BarkRecognizer recognizer = new BarkRecognizer(dogDoor);
        DoorRemote remote = new DoorRemote(recognizer, dogDoor);

        //testing adding dogs
        System.out.println("Testing adding dogs:");
        remote.addBarkKey(fido);
        remote.addBarkKey("Harold", "arf", true);
        System.out.println("Complete.\n");

        //testing timer function
        System.out.println("Testing door functions:");
        //makes sure the door is closed
        fido.move();
        dogDoor.openDoor();
        dogDoor.timerClose();

        //It then creates a new timer
        Timer timer = new Timer();
        //and a task to run on completion of the timer,
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //to move Fido and cancel the timer (because its task is finished)
                fido.move();
                System.out.println("Fido has moved.");
                timer.cancel();
            }
        };
        //schedules the timer to do the above task after a delay of 5000 milliseconds
        timer.schedule(task, 5000);
        //waits until 15 seconds until after the task is scheduled
        TimeUnit.SECONDS.sleep(15);
        //makes sure the door is closed
        fido.move();

        for (boolean pos : dogDoor.getDogPositions()) {
            System.out.println(pos);
        }
        System.out.println("Complete.\n");

        //testing remote functions
        System.out.println("Testing remote functions: ");
        remote.getIsOpen();
        remote.pushButton();
        remote.getIsOpen();
        remote.pushButton();
        remote.getIsOpen();
        remote.getDogIn();
        remote.setDoorDelay(5);
        System.out.println(dogDoor.getDoorLockDelay());
        System.out.println("Complete.\n");

        //testing BarkRecognizer
        System.out.println("Testing recognizer functions:");
        ArrayList<Dog> dogArray = new ArrayList<>(recognizer.getYourBarks());
        System.out.println(dogArray.get(0));
        System.out.println();
        System.out.println(dogArray.get(1));
        remote.newOwner();
        System.out.println();
        recognizer.setYourBark(dogArray);
        System.out.println(recognizer.getYourBarks().toString());
        System.out.println();
        recognizer.barkCheck(dogDoor.getDogs().get(0).getBarkSound());
        System.out.println("Heard a bark");
        System.out.println(DogDoor.isDoorOpen());
        remote.pushButton();
        System.out.println(DogDoor.isDoorOpen());
        recognizer.barkCheck("quack");
        System.out.println("Heard a quack");
        System.out.println(DogDoor.isDoorOpen());
        recognizer.checkScratch(true);
        recognizer.checkScratch(false);
        System.out.println("Complete.\n");



    }
}



