package org.codingdojo.yatzy3;

import java.util.List;

public class TwoPairsScorer extends CategoryScorer {
    @Override
    public int calculateScore(List<Integer> dice) {
        List<Integer> pairs = DICE_VALUES.stream()
            .filter(d -> frequencies(dice).get(d) >= 2)
            .toList();
        return pairs.size() >= 2 ? sum(pairs) * 2 : 0;
    }
}
