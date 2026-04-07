package gra.karciana;

import java.util.*;

public class Stack {
    private final Deque<Card> stack = new ArrayDeque<>();
    public void pushCard(Card card) {
        stack.push(card);
    }

    public void pushCards(List<Card> cards) {
        for (Card card : cards) {
            stack.push(card);
        }
        System.out.println(String.format("Kupka dostała %s karty. Rozmiar kupki: %s a na górze %s", cards.size(), stack.size(), stack.peek()));
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeFourTopRanks() {
        int value = stack.peek().value();

        long numberOfCardsForTopRank = stack.stream()
                .filter(c -> c.value() == value)
                .count();
        if (numberOfCardsForTopRank == 4) {
            System.out.println(String.format("usuwamy gotową czwórkę %s w ilości oczywiście: %s ", stack.peek().rank(), numberOfCardsForTopRank));
            stack.removeIf(c -> c.value() == value && c.value()!=2);
        }
    }

    public Card showTopStack() {
        return stack.peek();
    }

    public List<Card> getCardsBack(int numberOfBack) {
        List<Card> cardsToBeGetBack = new ArrayList<>();

        if(stack.peek().value()==2){
            return Collections.emptyList();
        }
        for (int i = 0; i < numberOfBack; i++) {
            cardsToBeGetBack.add(stack.poll());
        }
        return cardsToBeGetBack;
    }
}
