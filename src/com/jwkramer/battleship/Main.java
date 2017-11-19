package com.jwkramer.battleship;

import java.io.Console;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        Console console = System.console();
        Scanner console = new Scanner(System.in);

        boolean winner = false;

        System.out.print("\033[H\033[2J");
        System.out.println("Welcome to Battleship");
        System.out.println("Default is 1 player");
        System.out.println("Press enter to begin");
        //TODO: try/catch or other error handling on user inputs


        Player player1 = new Player();
        Player player2 = new Player();
        Board board1 = new Board();
        Board board2 = new Board();

        System.out.println("Player 1 place your ships:");
        for(Enum ship : Ship.types.values()) {
            boolean placementSuccess = false;
            while(!placementSuccess) {

                //TODO: display size of ship
                System.out.println("Ship " + (ship.ordinal() + 1) + "/" + Ship.types.values().length + ": " + ship);

                board1.display();

                System.out.println("Please enter row number (1 - 10):");
                int row = Integer.parseInt(console.next());
//                int row = 2;

                System.out.println("Please enter column letter (a - j):");
                String columnString = console.next();
                char column = columnString.charAt(0);
//                char column = 'b';

                System.out.println("Please enter orientation (h or v):");
                String orientationString = console.next();
                char orientation = orientationString.charAt(0);
//                char orientation = 'v';


                if(board1.checkPlacement(ship, row, column, orientation)) {
                    board1.placeShip(ship, row, column, orientation);
                    placementSuccess = true;
                }

                if(!placementSuccess) {
                    System.out.println("Error on placement, press enter to try again:");
                    console.next();
                }

                //TODO: solution for windows?
                System.out.print("\033[H\033[2J");
            }
        }

        while(winner == false) {
            if(player1.turn == true) {

                System.out.println("stop");


            } else {

            }
        }
    }
}
