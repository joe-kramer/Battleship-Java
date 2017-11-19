package com.jwkramer.battleship;

import java.util.Random;
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


        Player player1 = new Player(1, true);
        Player player2 = new Player(2, false);

        System.out.println("Board 1 place your ships:");
        for(Enum ship : Ship.types.values()) {
            boolean placementSuccess = false;
            while (!placementSuccess) {

                //TODO: try pushing up project without MANIFEST.MF and see if it works still when compiling jar
                //TODO: display size of ship
                System.out.println("Ship " + (ship.ordinal() + 1) + "/" + Ship.types.values().length + ": " + ship);

                player1.board1.display();

                System.out.println("Please enter row number (1 - 10):");
//                int row = Integer.parseInt(console.readLine());
                String rowString = console.next();
                while (!rowString.matches("[1-9]|10")) {
                    System.out.println("Invalid Key");
                    rowString = console.next();
                }
                Integer row = Integer.parseInt(rowString);

                System.out.println("Please enter column letter (a - j):");
//                String columnString = console.readLine();
                String columnString = console.next();
                while (!columnString.matches("[a-j]")) {
                    System.out.println("Invalid Key");
                    columnString = console.next();
                }
                char column = columnString.charAt(0);

                System.out.println("Please enter orientation (h or v):");
//                String orientationString = console.readLine();
                String orientationString = console.next();
                while (!orientationString.matches("h|v]")) {
                    System.out.println("Invalid Key");
                    orientationString = console.next();
                }
                char orientation = orientationString.charAt(0);

                if (player1.board1.checkPlacement(ship, row, column, orientation)) {
                    player1.board1.placeShip(ship, row, column, orientation);
                    placementSuccess = true;
                }

                if (!placementSuccess) {
                    System.out.println("Error on placement, press enter to try again:");
//                    console.readLine();
                    console.next();
                }

                //TODO: solution for windows?
                System.out.print("\033[H\033[2J");
            }
        }

        //TODO:Computer player intialize
        player2.board1.computerInitEasy();

        System.out.println("Press enter to begin");
        console.next();

        while (!winner) {
            if (player1.isTurn() == true) {
                System.out.println("Please enter row number for shot (1 - 10):");
//                int row = Integer.parseInt(console.readLine());
                String rowString = console.next();
                while (!rowString.matches("[1-9]|10")) {
                    System.out.println("Invalid Key");
                    rowString = console.next();
                }
                Integer row = Integer.parseInt(rowString);

                System.out.println("Please enter column letter for shot (a - j):");
//                String columnString = console.readLine();
                String columnString = console.next();
                while (!columnString.matches("[a-j]")) {
                    System.out.println("Invalid Key");
                    columnString = console.next();
                }
                char column = columnString.charAt(0);

                boolean hit = player2.takeShot(row, column);
                if (hit) {
                    System.out.println("hit!");
                    player2.setHitCount(player2.getHitCount() + 1);
                    player1.markHit(row, column);
                    if (player2.getHitCount() == 14) {
                        gameOver(player1);
                        winner = true;
                    }
                } else System.out.println("miss.");
            } else {
                boolean hit = computerHitEasy(player1, player2);
                if (hit) {
                    System.out.println("hit!");
                    player1.setHitCount(player1.getHitCount() + 1);

                    if (player1.getHitCount() == 14) {
                        gameOver(player2);
                        winner = true;
                    }

                }
            }
        }
    }

    static boolean computerHitEasy(Player player1, Player player2) {
        Random random = new Random();
        int row = random.nextInt(10);
        int col = random.nextInt(10);
        while (!player2.checkShot(row, col)) {
            row = random.nextInt(10);
            col = random.nextInt(10);
        }
        player2.markHitComputer(row, col);

        if (player1.checkShotComputer(row, col)) {
            return true;
        } else return false;
    }


    private static void gameOver(Player player) {
        System.out.print("\033[H\033[2J");
        if(player.getPlayerNumber() == 1) {
            System.out.println("Congratulations Player 1, You Win!");
        } else System.out.println("Sorry, you lose.");
    }

    //TODO: reformat code in all classes
}
