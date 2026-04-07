package gra.karciana;

record Card(Suit suit, String rank, int value) {
    static Card of(String suit, String rank, int value) {
        return new Card(Suit.valueFrom(suit), rank, value);
    }

    public boolean canBeOnTopOf(Card card) {
        if (card == null) {
            return true;
        }
        return this.suit() == card.suit() && this.value() > card.value();
    }
}