package gra.karciana;

import java.util.*;
import java.util.stream.Collectors;

public class GameRules {

    public static List<Card> throwOneOrThreeOfTheSameRank(Card topStackCard, PriorityQueue<Card> hand) {
        String rank = topStackCard.rank();
        long cardAmountOfTheSameRankInTheHand = hand.stream()
                .filter(c -> c.rank().equals(rank))
                .count();

        if (cardAmountOfTheSameRankInTheHand == 1 || cardAmountOfTheSameRankInTheHand == 3) {
            return hand.stream()
                    .filter(c -> c.rank().equals(rank))
                    .collect(Collectors.toList());
        } else if (cardAmountOfTheSameRankInTheHand == 2) {

            return List.of(hand.stream()
                    .filter(c -> c.rank().equals(rank))
                    .findFirst().get());
        } else {
            return Collections.EMPTY_LIST;

        }
    }

    public static List<Card> throwOneOrThreeOrFourOfLowestValueTheSameSuit(Card stackCard, PriorityQueue<Card> hand) {

        int valueOfLowestCard = 14;
        int valueOfLowestThreeOrFourCards = 14;
        List<Card> handOfRankEqualOrHigher = hand.stream()
                .filter(c -> c.value() >= stackCard.value())
                .toList();

        //tutaj dostajemy najniższą jedną kartę, która można rzucić
        Optional<Card> lowestOneCard = handOfRankEqualOrHigher.stream()
                .filter(c -> c.canBeOnTopOf(stackCard))
                .min(Comparator.comparing(Card::value));
        if (lowestOneCard.isPresent()) {
            valueOfLowestCard = lowestOneCard.get().value();
        }

        //tutaj obliczam najniższą możliwą trójke lub czwórkę
        Map<Integer, List<Card>> cardsGroupedByValue = handOfRankEqualOrHigher.stream()
                .collect(Collectors.groupingBy(Card::value));
        List<Card> lowestThreeOrFourCards = cardsGroupedByValue.entrySet().stream()
                .filter(cl -> cl.getValue().size() >= 3)
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue).orElse(Collections.emptyList());
        if (!lowestThreeOrFourCards.isEmpty()) {
            valueOfLowestThreeOrFourCards = lowestThreeOrFourCards.getFirst().value();
        }

        return valueOfLowestCard < valueOfLowestThreeOrFourCards ? lowestOneCard.stream().toList() : lowestThreeOrFourCards;
    }

}
