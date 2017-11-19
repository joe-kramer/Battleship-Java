package com.jwkramer.battleship;

import java.util.*;

public class Board {
    private final List<Character> letterList = new ArrayList<>(Arrays.asList('a','b','c','d','e','f','g','h','i','j'));
    private final Map<String, Integer> shipMap = new HashMap<>();
    {   shipMap.put("CARRIER", 5);
        shipMap.put("BATTLESHIP", 4);
        shipMap.put("CRUISER", 3);
        shipMap.put("DESTROYER", 2);
    };
    private final Integer SIZE = 10;

    //TODO: Do I want to initialize here or in constructor?
    private Square[][] board = new Square[SIZE][SIZE];

    public Board() {
        for(int r = 0; r < SIZE; r++) {
            for(int c = 0; c < SIZE; c++) {
                board[r][c] = new Square();
            }
        }
    }

    public boolean checkPlacement(Enum ship, int row, char column, char orientation) {
        int col = letterList.indexOf(column);
        int shipSize = shipMap.get(ship.toString());

        //TODO: do i need exception here on orientation? even if i am doing that in main class?

        if(orientation == 'v') {
            for(int r = row - 1; r < (row + shipSize - 1); r++) {
                if(r == SIZE) return false;
                if(board[r][col].isShip()) return false;
            }
        } else {
            for(int c = col; c < col + shipSize; c++) {
                if(c == SIZE) return false;
                if(board[row - 1][c].isShip()) return false;
            }
        }
        return true;
    }

    public void placeShip(Enum ship, int row, char column, char orientation) {
        int col = letterList.indexOf(column);
        int shipSize = shipMap.get(ship.toString());

        //TODO: make array of ship views
        if(orientation == 'v') {
            for(int r = row - 1; r < (row + shipSize - 1); r++) {
                board[r][col].placeShip('s');
            }
        } else {
            for(int c = col; c < col + shipSize; c++) {
                board[row - 1][c].placeShip('s');
            }
        }
    }

    public void display() {

        System.out.println();

        System.out.println("  | a | b | c | d | e | f | g | h | i | j |");
        for(int r = 0; r < SIZE - 1; r++) {
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
}
