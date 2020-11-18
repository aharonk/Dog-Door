package com;

public class Dog{
    private final String name; //can't teach an old dog new names?
    private Bark bark;
    private boolean inHouse;

    public Dog(String name, String bark, boolean inHouse) {
        this.name = name;
        this.bark = new Bark(bark);
        this.inHouse = inHouse;
    }

    public void move() {
        if (DogDoor.isDoorOpen()) {
            inHouse = !inHouse;
        } else {
            System.out.println(name+" can't move.");
        }
    }

    @Override
    public String toString() {
        String inHouseString = this.inHouse? "inside" : "outside";
        return String.format("The dog's name is %s.%nThe dog's bark is %s.%nThe dog is %s.%n",
                name, getBarkSound(), inHouseString);
    }

    public boolean isInHouse() {
        return inHouse;
    }

    public Bark getBark() {
        return bark;
    }

    public void setBark(Bark bark) {
        this.bark = bark;
    }

    public void setBark (String bark) {
        this.bark.setBark(bark);
    }

    public void setInHouse(boolean inHouse) {
        this.inHouse = inHouse;
    }

    public String getBarkSound() {
        return bark.getBark();
    }

    public String getName() {return name;}
}


