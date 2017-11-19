package com.jwkramer.battleship;

public class Square {

    private boolean isShip = false;
    private boolean isHit = false;
    private char view = '~';

    public Square() {
    }

    public void placeShip(char view) {
        this.isShip = true;
        this.view = view;
    }

    public void hit() {
        this.isHit = true;
        this.view = '*';
    }

    public boolean isShip() {
        return isShip;
    }

    public boolean isHit() {
        return isHit;
    }

    public char getView() {
        return view;
    }
}
