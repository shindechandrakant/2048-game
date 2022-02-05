package game;

import java.util.Scanner;

public class PlayGame {


    public static void main(String[] args) {

        Game2048 game2048 = new Game2048(4);
        Scanner scanner = new Scanner(System.in);
        int userInput;

        while (true) {

            System.out.print("Enter Your Move : " );
            userInput = scanner.nextInt();
            if(game2048.nextMove(userInput)) {
                System.out.println("GAME OVER");
                break;
            }
        }
    }
}
