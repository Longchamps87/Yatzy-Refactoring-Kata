package org.codingdojo.yatzy3;

import java.util.List;

public class RepeatedCountScorer extends CategoryScorer {
    private final int count;

    public RepeatedCountScorer(int count) {
        this.count = count;
    }

    @Override
    public int calculateScore(List<Integer> dice) {
        return DICE_VALUES.stream()
            .filter(i -> frequencies(dice).get(i) >= count)
            .findFirst()
            .orElse(0) * count;
    }
}
