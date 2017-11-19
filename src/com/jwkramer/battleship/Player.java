package com.jwkramer.battleship;

public class Player {

    private boolean turn;
    private int hitCount = 0;
    private int playerNumber;
    public Board gameBoard = new Board();
    public Board trackingBoard = new Board();

    public Player(int num, boolean turn) {
        this.turn = turn;
        this.playerNumber = num;
    }

    public boolean isTurn() {
        return turn;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean markGameBoard(int row, int column) {
        this.turn = true;
        if (this.gameBoard.hit(row, column)) {
            this.hitCount++;
            return true;
        }
        return false;
    }

    public void markTrackingBoard(int row, int column, boolean hit) {
        this.turn = false;
        this.trackingBoard.track(row, column, hit);
    }

    public boolean computerCheckShot(int row, int col) {
        return this.trackingBoard.checkShot(row, col);
    }
}
