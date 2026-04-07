package gra.karciana;

import java.util.Arrays;

public enum Suit {
    HEART("Heart"),
    DIAMONDS("Diamond"),
    CLUB("Club"),
    SPADE("Spade"),
    NULL(null);

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public static Suit valueFrom(String suit) {
        return Arrays.stream(values())
                .filter(value -> value.getName().equals(suit))
                .findFirst()
                .orElse(NULL);
    }

    public String getName() {
        return name;
    }
}
