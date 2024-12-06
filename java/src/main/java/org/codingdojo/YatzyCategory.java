package org.codingdojo;

public enum YatzyCategory {

    CHANCE(0), YATZY(0),
    ONES(1), TWOS(2), THREES(3), FOURS(4), FIVES(5), SIXES(6),
    PAIR(2), THREE_OF_A_KIND(3), FOUR_OF_A_KIND(4),
    SMALL_STRAIGHT(6), LARGE_STRAIGHT(1),
    TWO_PAIRS(0), FULL_HOUSE(0);

    // depending on the enum value it can represent a die value, a frequency, a die to ignore or nothing if it is 0
    private final int attribute;

    YatzyCategory(int attribute) {
        this.attribute = attribute;
    }

    public int getAttribute() {
        return attribute;
    }
}
