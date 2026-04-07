package gra.karciana;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Player {

    private final String name;
    private final PriorityQueue<Card> hand = new PriorityQueue<>(Comparator.comparing(Card::value));

    public Player(String name) {
        this.name = name;
    }
    public void addCard(Card card) {
        hand.add(card);
    }
    public void addCards(List<Card> cards) {
        hand.addAll(cards);
        System.out.println(String.format("Gracz %s pobiera 4 karty i ma rozmiar ręki: %s ", this.name, hand.size()));
    }

    public boolean isPlayerWinner() {
        System.out.println(String.format("========================Gracz %s ma rozmiar ręki: %s ", this.name, hand.size()));
        hand.stream().forEach(System.out::println);
        return hand.isEmpty();
    }
    public List<Card> findCardsForGivenStackCard(Card stackCard) {
        List<Card> threeOrFourTheSameRank = GameRules.throwOneOrThreeOfTheSameRank(stackCard, hand);

        if (!threeOrFourTheSameRank.isEmpty()) {
            for (Card card : threeOrFourTheSameRank) {
                hand.remove(card);
            }
            System.out.println(String.format("Gracz %s rzuca %s karty o wartości %s", this.name, threeOrFourTheSameRank.size(), threeOrFourTheSameRank.getFirst().rank()));
            return threeOrFourTheSameRank;
        }

        List<Card> oneThreeOrFourTheSameSuit = GameRules.throwOneOrThreeOrFourOfLowestValueTheSameSuit(stackCard, hand);
        if (!oneThreeOrFourTheSameSuit.isEmpty()) {
            for (Card card : oneThreeOrFourTheSameSuit) {
                hand.remove(card);
            }
            System.out.println(String.format("Gracz %s rzuca %s karty o wartości %s", this.name, oneThreeOrFourTheSameSuit.size(), oneThreeOrFourTheSameSuit.getFirst().rank()));
            return oneThreeOrFourTheSameSuit;
        }

        return Collections.emptyList();
    }
}
