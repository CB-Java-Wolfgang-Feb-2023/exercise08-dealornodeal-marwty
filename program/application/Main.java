package application;

public class Main {
    public static void main(String[] args) {
        System.out.println("$$$$$$$$$$$$$$$ Welcome to Deal or no Deal $$$$$$$$$$$$$$$");
        DealOrNoDeal game = new DealOrNoDeal();
        game.initGame();
        game.chooseInitialCase();
        for (int round = 1; round <= 9; round++) {
            game.playRound(round);
        }
        game.finalStage();
    }

}
