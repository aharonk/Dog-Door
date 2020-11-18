package com;

public class Bark {
    private String bark;

    public Bark(String bark) {
        this.bark = bark;
    }

    private Bark() {}

    public void setBark(String bark) {
        this.bark = bark;
    }

    public String getBark() {
        return bark;
    }

    @Override
    public String toString() {
        return "The Bark is '"+bark+"'.";
    }
}


