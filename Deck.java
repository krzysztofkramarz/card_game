package gra.karciana;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
public class Deck {
    private final List<Card> deck = new ArrayList<>(54);
    public Deck() {

        String[] suits = {"Heart", "Diamond", "Club", "Spade" };
        Map<String, Integer> ranks = Map.ofEntries(
                Map.entry("2", 2),
                Map.entry("3", 3),
                Map.entry("4", 4),
                Map.entry("5", 5),
                Map.entry("6", 6),
                Map.entry("7", 7),
                Map.entry("8", 8),
                Map.entry("9", 9),
                Map.entry("10", 10),
                Map.entry("J", 11),
                Map.entry("Q", 12),
                Map.entry("K", 13),
                Map.entry("A", 14)
        );

        for (String suit : suits) {
            for (String rank : ranks.keySet()) {
                Card card = Card.of(suit, rank, ranks.get(rank));
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        return deck.removeLast();
    }

    public Card drawStartingCard() {
        Card card = deck.stream()
                .filter(c -> c.suit().equals(Suit.HEART) && c.value() == 2)
                .findFirst()
                .get();

        int indexOfStartingCard = deck.indexOf(card);
        deck.remove(indexOfStartingCard);
        return card;
    }
}
