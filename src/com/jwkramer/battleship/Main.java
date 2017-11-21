package com.jwkramer.battleship;

import java.io.Console;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        final List<String> letterList = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"));
        final Map<String, Integer> ships = new HashMap<>();
        {
            ships.put("DESTROYER", 2);
            ships.put("CRUISER", 3);
            ships.put("BATTLESHIP", 4);
            ships.put("CARRIER", 5);

        }

        Console console = System.console();
//        Scanner console = new Scanner(System.in);

        boolean winner = false;
        int counter = 1;

        System.out.print("\033[H\033[2J");
        System.out.println("Welcome to Battleship");
        System.out.println("Default is 1 player vs computer");
        System.out.println("Press enter to begin placing your ships");
        console.readLine();
//        console.next();

        Player player1 = new Player(1, true);
        Player player2 = new Player(2, false);

        for (Map.Entry<String, Integer> ship : ships.entrySet()) {
            boolean placementSuccess = false;
            while (!placementSuccess) {

                //TODO: solution for windows?
                System.out.print("\033[H\033[2J");

                //TODO: try pushing up project without MANIFEST.MF and see if it works still when compiling jar
                System.out.println("Ship " + (counter) + "/" + ships.size() + ": " + ship.getKey() + " (Size: " + ship.getValue() + ")");

                player1.gameBoard.display();

                System.out.println("Please enter row number (1 - 10):");
                String rowString = console.readLine();
//                String rowString = console.next();
                while (!rowString.matches("[1-9]|10")) {
                    System.out.println("Invalid Key");
                    rowString = console.readLine();
//                    rowString = console.next();
                }
                Integer row = (Integer.parseInt(rowString) - 1);

                System.out.println("Please enter column letter (a - j):");
                String columnString = console.readLine();
//                String columnString = console.next();
                while (!columnString.matches("[a-j]")) {
                    System.out.println("Invalid Key");
                    columnString = console.readLine();
//                    columnString = console.next();
                }
                int column = letterList.indexOf(columnString);

                System.out.println("Please enter orientation (h or v):");
                String orientationString = console.readLine();
//                String orientationString = console.next();
                while (!orientationString.matches("[hv]")) {
                    System.out.println("Invalid Key");
                    orientationString = console.readLine();
//                    orientationString = console.next();
                }
                char orientation = orientationString.charAt(0);

                if (player1.gameBoard.checkPlacement(ship.getValue(), row, column, orientation)) {
                    player1.gameBoard.placeShip(ship.getValue(), row, column, orientation);
                    placementSuccess = true;
                }

                if (!placementSuccess) {
                    System.out.println("Error on placement, press enter to try again:");
                    console.readLine();
//                    console.next();
                }
            }
            counter++;
        }

//        player1.gameBoard.testing(ships.values());
//        player2.gameBoard.testing(ships.values());
        player2.gameBoard.computerInitEasy(ships.values());
        System.out.print("\033[H\033[2J");

        player1.gameBoard.display();
        System.out.println("Press enter to begin");
        console.readLine();
//        console.next();
        System.out.print("\033[H\033[2J");

        player1.setHitCount(13);

        //TODO: Create two player option, and put console ui in methods

        //counter only for precaution for now
        while (!winner || (counter >= 200)) {
            if (player1.isTurn() == true) {

                System.out.println("Player1's turn:");
                player1.trackingBoard.display();

                System.out.println("Please enter row number for shot (1 - 10):");
                String rowString = console.readLine();
//                String rowString = console.next();
                while (!rowString.matches("[1-9]|10")) {
                    System.out.println("Invalid Key");
                    rowString = console.readLine();
//                    rowString = console.next();
                }
                int row = (Integer.parseInt(rowString) - 1);

                System.out.println("Please enter column letter for shot (a - j):");
                String columnString = console.readLine();
//                String columnString = console.next();
                while (!columnString.matches("[a-j]")) {
                    System.out.println("Invalid Key");
                    columnString = console.readLine();
//                    columnString = console.next();
                }
                int column = letterList.indexOf(columnString);

                boolean hit = player2.markGameBoard(row, column);
                player1.markTrackingBoard(row, column, hit);
                System.out.print("\033[H\033[2J");

                if (hit) {
                    System.out.println("hit!");
                    player1.trackingBoard.display();
                    if (player2.getHitCount() == 14) {
                        gameOver(player1);
                        return;
                    }
                } else {
                    System.out.println("miss.");
                    player1.trackingBoard.display();
                }
                System.out.println("Press enter for computer's shot:");

            } else {
                System.out.print("Computer's turn... ");
                Random random = new Random();
                int row = random.nextInt(10);
                int col = random.nextInt(10);

                while (player1.computerCheckShot(row, col)) {
                    row = random.nextInt(10);
                    col = random.nextInt(10);
                }

                boolean hit = player1.markGameBoard(row, col);
                player2.markTrackingBoard(row, col, hit);

                if (hit) {
                    System.out.println("Computer hit!");

                    if (player1.getHitCount() == 14) {
                        gameOver(player2);
                        return;
                    }

                } else {
                    System.out.println("Computer miss.");
                }
                System.out.println("");
                System.out.println("Player1's board:");
                player1.gameBoard.display();
                System.out.println("Press enter for Player1's shot:");
            }
            counter++;
            console.readLine();
//            console.next();
            System.out.print("\033[H\033[2J");
        }
    }
    //TODO: static methods ?
    private static void gameOver(Player player) {
        System.out.print("\033[H\033[2J");
        if (player.getPlayerNumber() == 1) {
            System.out.println("Congratulations Player 1, You Win!");
        } else System.out.println("Sorry, you lose.");
    }

    //TODO: reformat code in all classes
}
