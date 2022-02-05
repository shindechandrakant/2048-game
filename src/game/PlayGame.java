package game;

import java.util.Scanner;

public class PlayGame {


    public static void main(String[] args) {

        Game2048 game2048 = new Game2048(4);
        Scanner scanner = new Scanner(System.in);
        int userInput = 1;

        while (!game2048.nextMove(userInput)) {

            System.out.print("Your Next Move : " );
            userInput = scanner.nextInt();
        }
    }
}
