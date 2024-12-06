package org.codingdojo.yatzy3;

import org.codingdojo.YatzyCalculator;

import java.util.List;

public class Yatzy3 implements YatzyCalculator {

    @Override
    public int score(List<Integer> dice, String categoryName) {

        // safe checks on first argument
        if (dice == null || dice.size() != 5) {
            return -1;
        }
        for (int die : dice) {
            if (!CategoryScorer.DICE_VALUES.contains(die)) {
                return -1;
            }
        }

        return CategoryScorer.createInstance(categoryName).calculateScore(dice);
    }
}
