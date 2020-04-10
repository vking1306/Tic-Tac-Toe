package tictactoe;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static int countX = 0;
    static int countO = 0;
    static int sum;
    static boolean isItFinished = false;                            //boolean to loop until games has end

    static char[][] initField() {                                   //initialise field
        char[][] field = new char[3][3];                            // create empty field
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = ' ';
            }
        }
        return field;
    }

    static void printField(char[][] array) {                        //print the array
        System.out.println("---------");
        for (char[] x : array) {
            System.out.print("| ");
            for (char y : x) {
                System.out.print(y + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static void checkField(char[][] array) {
        int xWins = 0;
        int oWins = 0;

        if (array[0][0] == array[1][1] && array[0][0] == array[2][2] ||
                array[0][2] == array[1][1] && array[1][1] == array[2][0]) {         //check for win both diagonals
            if (array[1][1] == 'X') {
                xWins++;
            } else if (array[1][1] == 'O') {
                oWins++;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (array[i][0] == array[i][1] && array[i][0] == array[i][2]) {     //check for win on row
                if (array[i][0] == 'X') {
                    xWins++;
                } else if (array[i][0] == 'O') {
                    oWins++;
                }
            }
            if (array[0][i] == array[1][i] && array[0][i] == array[2][i]) {        //check for win on column
                if (array[0][i] == 'X') {
                    xWins++;
                } else if (array[0][i] == 'O') {
                    oWins++;
                }
            }
        }

        sum = countO + countX;                                             //count number of Xs and Os on field
        if (xWins == 0 && oWins == 0 && sum == 9) {                      //check if no win and no empty on field
            System.out.println("Draw");
            isItFinished = true;
        } else if (xWins ==1) {                                                 //check if X wins
            System.out.println("X wins");
            isItFinished = true;
        } else if (oWins ==1) {                                                 //check if O wins
            System.out.println("O wins");
            isItFinished = true;
        }
    }

    static void nextMove(char[][] array) {
        boolean correctInput = false;                                                       //boolean false to loop until true
        while (!correctInput) {
            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine().replaceAll(" ", "");                          //read the coordinates and remove spaces
            try {
                int x = Integer.parseInt(input.substring(0,1));                           //parse string to int
                int y = Integer.parseInt(input.substring(1,2));                           //parse string to int
                if (x < 1 || x > 3 || y < 1 || y > 3) {                                     //make sure input is correct
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (array[3 - y][x - 1] == ' ' && countX == countO) {      //if input correct place X
                    array[3 - y][x - 1] = 'X';
                    countX++;
                    correctInput = true;
                } else if (array[3 - y][x - 1] == ' ' && countO < countX) {      //if input correct place X
                    array[3 - y][x - 1] = 'O';
                    countO++;
                    correctInput = true;                                                    //boolean true to exit loop
                } else {
                    System.out.println("This cell is occupied! Choose another one!");       //if input already has X or O
                }                                                                           // ask again
            } catch (NumberFormatException e) {                                             // catch exception if input
                System.out.println("You should enter numbers!");                            //is not numbers
            }
        }
    }

    public static void main(String[] args) {
        char[][] field = initField();
        printField(field);

        while (!isItFinished) {
            nextMove(field);
            printField(field);
            checkField(field);
        }
    }
}
