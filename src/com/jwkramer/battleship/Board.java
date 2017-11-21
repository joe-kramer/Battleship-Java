package com.jwkramer.battleship;

import java.util.*;

public class Board {
    private final Integer SIZE = 10;

    //TODO: Do I want to initialize here or in constructor? What is the benefit of using initializer. In spring boot repository case as well. Is it particular to main classes?
    private Square[][] board = new Square[SIZE][SIZE];

    public Board() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = new Square();
            }
        }
    }

    public boolean checkPlacement(int shipSize, int row, int col, char orientation) {
        //TODO: do i need exception here on orientation? even if i am doing that in main class?

        if (orientation == 'v') {
            for (int r = row; r < (row + shipSize); r++) {
                if (r == SIZE) return false;
                if (board[r][col].isShip()) return false;
            }
        } else {
            for (int c = col; c < col + shipSize; c++) {
                if (c == SIZE) return false;
                if (board[row][c].isShip()) return false;
            }
        }
        return true;
    }

    public void placeShip(int shipSize, int row, int col, char orientation) {
        //TODO: make array of ship views

        if (orientation == 'v') {
            for (int r = row; r < (row + shipSize); r++) {
                board[r][col].placeShip('s');
            }
        } else {
            for (int c = col; c < col + shipSize; c++) {
                board[row][c].placeShip('s');
            }
        }
    }

    public void display() {

        System.out.println();

        System.out.println("  | a | b | c | d | e | f | g | h | i | j |");
        for (int r = 0; r < SIZE - 1; r++) {
            System.out.print((r + 1) + " |");
            for (int c = 0; c < SIZE; c++) {
                System.out.print(" " + board[r][c].getView() + " |");
            }
            System.out.println();
        }

        System.out.print("10|");
        for (int c = 0; c < SIZE; c++) {
            System.out.print(" " + board[9][c].getView() + " |");
        }

        System.out.println();
    }

    public boolean hit(int row, int col) {
        this.board[row][col].hit();
        if (this.board[row][col].isShip()) {
            return true;
        } else return false;
    }

    public void track(int row, int col, boolean hit) {
        this.board[row][col].track(hit);
    }

    public void testing(Collection<Integer> ships) {

        int counter = 0;
        for (int ship : ships) {

            char orientation = 'h';
            int row = counter;
            int col = 0;

            placeShip(ship, row, col, orientation);
            counter++;
        }
    }


    /*
    * AI methods
    * */

    public void computerInitEasy(Collection<Integer> ships) {

        Random random = new Random();

        for (int ship : ships) {
            char orientation;
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            int orient = random.nextInt(2);
            orientation = (orient == 0) ? 'v' : 'h';

            while (!checkPlacement(ship, row, col, orientation)) {
                row = random.nextInt(SIZE);
                col = random.nextInt(SIZE);
            }
            placeShip(ship, row, col, orientation);
        }
    }

    public boolean checkShot(int row, int col) {
        return this.board[row][col].isHit();
    }

    public void hitComputer(int row, int col) {
        this.board[row][col].hit();
    }

    public boolean checkShotComputer(int row, int col) {
        return this.board[row][col].isShip();
    }


}
