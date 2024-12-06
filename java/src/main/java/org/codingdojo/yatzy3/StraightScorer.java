package org.codingdojo.yatzy3;

import java.util.List;

public class StraightScorer extends CategoryScorer {
    private final int straightExcludes;

    public StraightScorer(int straightExcludes) {
        this.straightExcludes = straightExcludes;
    }

    /**
     * @return true if all frequencies, except straightExcludes, are equal to 1, false otherwise
     */
    boolean checkAllFrequenciesAreOne(List<Integer> dice) {
        return DICE_VALUES.stream().allMatch(i -> i == straightExcludes || frequencies(dice).get(i) == 1);
    }

    @Override
    public int calculateScore(List<Integer> dice) {
        if (checkAllFrequenciesAreOne(dice)) {
            return sum(dice);
        }
        return 0;
    }
}
