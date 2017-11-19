package com.jwkramer.battleship;

public class Player {

    private boolean turn;
    private int hitCount = 0;
    private int playerNumber;
    public Board board1 = new Board();
    public Board board2 = new Board();

    public boolean isTurn() {
        return turn;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public Player(int num, boolean turn) {
        this.turn = turn;
        this.playerNumber = num;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean takeShot(int row, char col) {
        this.turn = true;
        return this.board1.hit(row, col);
    }

    public void markHit(Integer row, char column) {
        this.turn = false;
        this.board2.hit(row, column);
    }

    public boolean checkShot(int row, int col) {
        return this.board2.checkShot(row, col);
    }

    public void markHitComputer(int row, int col) {
        this.turn = false;
        this.board2.hitComputer(row, col);
    }

    public boolean checkShotComputer(int row, int col) {
        return this.board1.checkShotComputer(row, col);
    }
}
