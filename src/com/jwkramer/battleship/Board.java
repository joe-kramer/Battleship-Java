package com.jwkramer.battleship;

import java.util.*;

public class Board {
    private final Integer SIZE = 10;
    private final List<Character> letterList = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));
    private final Map<String, Integer> shipMap = new HashMap<>();

    {
        shipMap.put("CARRIER", 5);
        shipMap.put("BATTLESHIP", 4);
        shipMap.put("CRUISER", 3);
        shipMap.put("DESTROYER", 2);
    }

    //TODO: Do I want to initialize here or in constructor?
    private Square[][] board = new Square[SIZE][SIZE];

    public Board() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = new Square();
            }
        }
    }

    public boolean checkPlacement(Enum ship, int row, char column, char orientation) {
        int col = letterList.indexOf(column);
        int shipSize = shipMap.get(ship.toString());

        //TODO: do i need exception here on orientation? even if i am doing that in main class?

        if (orientation == 'v') {
            for (int r = row - 1; r < (row + shipSize - 1); r++) {
                if (r == SIZE) return false;
                if (board[r][col].isShip()) return false;
            }
        } else {
            for (int c = col; c < col + shipSize; c++) {
                if (c == SIZE) return false;
                if (board[row - 1][c].isShip()) return false;
            }
        }
        return true;
    }

    public void placeShip(Enum ship, int row, char column, char orientation) {
        int col = letterList.indexOf(column);
        int shipSize = shipMap.get(ship.toString());

        //TODO: make array of ship views
        if (orientation == 'v') {
            for (int r = row - 1; r < (row + shipSize - 1); r++) {
                board[r][col].placeShip('s');
            }
        } else {
            for (int c = col; c < col + shipSize; c++) {
                board[row - 1][c].placeShip('s');
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

    public void computerInitEasy() {
        Random random = new Random();
        for (Enum ship : Ship.types.values()) {
            char orientation;
            int row = random.nextInt(SIZE);
            int column = random.nextInt(SIZE);
            int orient = random.nextInt(2);
            orientation = (orient == 0) ? 'v' : 'h';

            while (!checkPlacement(ship, row, letterList.get(column), orientation)) {
                row = random.nextInt(SIZE);
                column = random.nextInt(SIZE);
            }
            placeShip(ship, row, letterList.get(column), orientation);
        }
    }

    public boolean hit(int row, char col) {
        this.board[row][letterList.indexOf(col)].hit();
        if(this.board[row][letterList.indexOf(col)].isShip()) {
            return true;
        } else return false;
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
