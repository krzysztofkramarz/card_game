package gra.karciana;

import java.util.List;

public class Game {

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game();

        Deck deck = new Deck();
        Player player1 = new Player("pierwszy");
        Player player2 = new Player("drugi");

        Stack stack = new Stack();
        stack.pushCard(deck.drawStartingCard());
        game.giveCards(deck, player1, 26);
        game.giveCards(deck, player2, 25);

        System.out.println("Zaczynamy gre od karty: " + stack.showTopStack());

        Card cardFromTopStack;
        while (true) {
            if (player1.isPlayerWinner()) {
                System.out.println("Wygrał gracz 1");
                return;
            }
            if (player2.isPlayerWinner()) {
                System.out.println("Wygrał gracz 2");
                return;
            }

            cardFromTopStack = stack.showTopStack();
            List<Card> cardsFromPlayer1 = player1.findCardsForGivenStackCard(cardFromTopStack);
            stack.pushCards(cardsFromPlayer1);
            stack.removeFourTopRanks();
            if (cardsFromPlayer1.isEmpty()) {
                player1.addCards(stack.getCardsBack(3));
            }

            cardFromTopStack = stack.showTopStack();
            List<Card> cardsFromPlayer2 = player2.findCardsForGivenStackCard(cardFromTopStack);
            stack.pushCards(cardsFromPlayer2);
            stack.removeFourTopRanks();
            if (cardsFromPlayer2.isEmpty()) {
                player2.addCards(stack.getCardsBack(3));
            }

        }
    }

    private void giveCards(Deck deck, Player player, int handSize) {
        for (; handSize > 0; handSize--) {
            player.addCard(deck.drawCard());
        }
    }
}
