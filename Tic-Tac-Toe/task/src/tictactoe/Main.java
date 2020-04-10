package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

enum GameStatus {
    NOT_FINISHED,
    DRAW,
    X_WIN,
    O_WIN,
    IMPOSSIBLE
}

public class Main {
    static int[][] gameWinPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
    static int[] gameLoop = new int[9];
    static int[][] coordinates = new int[4][4];
    static int x, o;
    static Scanner scanner;
    //Let 1 for X and 2 for O and 0 for _
    static GameStatus gameStatus = GameStatus.NOT_FINISHED;

    public static void main(String[] args) {
        // write your code here
        System.out.print("Enter cells: ");
        scanner = new Scanner(System.in);
        String symbols = scanner.nextLine();

        TransferSymbolToArrayOfNumber(symbols.toCharArray());
        SetCorrespondIndexInCoordinatesArray();

        PrintFiled(gameLoop);
        GetCoordinates();


    }

    private static void TransferSymbolToArrayOfNumber(char[] symbols) {
        int index = 0;
        //add symbol to gameloop
        for (char cr : symbols) {
            AddToList(cr, index);
            index++;
        }
    }

    private static void AddToList(char cr, int i) {

        switch (cr) {
            case 'X':
                gameLoop[i] = 1;
                x += 1;
                break;
            case 'O':
                gameLoop[i] = 2;
                o += 1;
                break;
            case '_':
                gameLoop[i] = 0;
                break;
        }


    }

    private static void CheckTheWinner() {
        int win = 0;
        int winner = 0;
        int diff = x - o;
        int sum = x + o;
        if (diff >= 2 || diff < -1) {
            gameStatus = GameStatus.IMPOSSIBLE;

            return;
        }
        for (int[] pos : gameWinPosition) {
            if (gameLoop[pos[0]] == gameLoop[pos[1]] && gameLoop[pos[2]] == gameLoop[pos[1]]) {

                if (gameLoop[pos[0]] != 0) {
                    win++;
                    winner = gameLoop[pos[0]];
                }
            }
        }
        if (win >= 2) {
            gameStatus = GameStatus.IMPOSSIBLE;

            return;
        }
        if (winner == 1) {
            gameStatus = GameStatus.X_WIN;
            return;
        } else if (winner == 2) {
            gameStatus = GameStatus.O_WIN;
            return;
        } else if (sum == 9) {
            System.out.println(winner);
            gameStatus = GameStatus.DRAW;
        }
    }

    private static void PrintStates(GameStatus state) {
        switch (state) {
            case NOT_FINISHED:
                System.out.println("Game not finished");
                break;
            case DRAW:
                System.out.println("Draw");
                break;
            case X_WIN:
                System.out.println("X wins");
                break;
            case O_WIN:
                System.out.println("O wins");
                break;
            case IMPOSSIBLE:
                System.out.println("Impossible");
                break;

        }
    }

    private static void PrintFiled(int[] fields) {
        System.out.println("---------");
        for (int i = 0; i < fields.length; i += 3) {
            System.out.println("| " + GetSymbol(fields[i]) + " " + GetSymbol(fields[i + 1]) + " " + GetSymbol(fields[i + 2]) + " " + "|");

        }
        System.out.println("---------");
    }

    private static String GetSymbol(int i) {
        switch (i) {
            case 0:
                return "_";

            case 1:
                return "X";

            case 2:
                return "O";

            default:
                return "";
        }
    }

    private static void GetCoordinates() {
        int x, y;
        boolean done = false;
        do {
            try {
                System.out.print("Enter the coordinates: ");
                y = scanner.nextInt();
                x = scanner.nextInt();
                if ((x == 1 || x == 2 || x == 3) && (y == 1 || y == 2 || y == 3)) {

                    if (gameLoop[coordinates[x][y]] != 0) {
                        System.out.println("This cell is occupied! Choose another one!");

                    } else {
                        gameLoop[coordinates[x][y]] = 1;
                        PrintFiled(gameLoop);
                        done = true;
                    }

                } else
                {
                    System.out.println("Coordinates should be from 1 to 3!");
                }


            } catch (InputMismatchException e) {
                System.out.println("Invalid integer input");
                scanner.next();
            }


        } while (done == false);


    }

    private static void SetSymbolToCorespandCooridnat() {
        boolean notCorrect = true;
        int x = 0;
        int y = 0;
        do {
            System.out.print("Enter the coordinates: ");
            if (scanner.hasNextInt()) {
                y = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    x = scanner.nextInt();
                }

            } else {
                System.out.print("You should enter numbers!");
                continue;
            }


            if (gameLoop[coordinates[x][y]] != 0) {
                System.out.println("This cell is occupied! Choose another one!");

            } else {
                gameLoop[coordinates[x][y]] = 1;
                PrintFiled(gameLoop);
                notCorrect = false;
            }


        } while (notCorrect);

    }

    private static void SetCorrespondIndexInCoordinatesArray() {
        int index = 0;
        for (int i = 3; i > 0; i--) {
            for (int j = 1; j < 4; j++) {
                coordinates[i][j] = index;
                index++;
            }
        }
    }
}
