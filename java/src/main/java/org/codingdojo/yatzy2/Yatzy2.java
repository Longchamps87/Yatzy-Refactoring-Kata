package org.codingdojo.yatzy2;

import org.codingdojo.YatzyCalculator;
import org.codingdojo.YatzyCategory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Yatzy2 implements YatzyCalculator {

    static final List<Integer> DICE_VALUES = Arrays.asList(6, 5, 4, 3, 2, 1);

    @Override
    public int score(List<Integer> dice, String categoryName) {
        // safe checks on first argument
        if (dice == null || dice.size() != 5) {
            return -1;
        }
        for (int die : dice) {
            if (!DICE_VALUES.contains(die)) {
                return -1;
            }
        }

        Map<Integer, Integer> diceFrequencies = calculateDiceFrequencies(dice);

        return calculateScore(dice, categoryName, diceFrequencies);
    }

    private Map<Integer, Integer> calculateDiceFrequencies(List<Integer> dices) {
        Map<Integer, Integer> diceFrequencies = new HashMap<>();
        for (int i : DICE_VALUES) {
            diceFrequencies.put(i, 0);
        }
        for (int die : dices) {
            diceFrequencies.put(die, diceFrequencies.get(die) + 1);
        }
        return diceFrequencies;
    }

    private int calculateScore(List<Integer> dice, String categoryName, Map<Integer, Integer> diceFrequencies) {
        YatzyCategory category = YatzyCategory.valueOf(categoryName);
        return switch (category) {

            case CHANCE ->
                // score = sum of the dice
                sum(dice);

            case YATZY ->
                // score if all dice are the same
                diceFrequencies.containsValue(5) ? 50 : 0;

            case ONES, TWOS, THREES, FOURS, FIVES, SIXES ->
                // score = sum all the ones, or twos, or threes, ...
                scoreUniqueDiceValue(diceFrequencies, category);

            case PAIR, THREE_OF_A_KIND, FOUR_OF_A_KIND ->
                // score if two, three or four dice are the same
                scoreOfAKind(diceFrequencies, category);

            case SMALL_STRAIGHT, LARGE_STRAIGHT ->
                // score for (1,2,3,4,5) or (2,3,4,5,6)
                checkAllFrequenciesAreOne(diceFrequencies, category) ? sum(dice) : 0;

            case TWO_PAIRS ->
                // score for two pairs
                scoreForTwoPairs(diceFrequencies);

            case FULL_HOUSE ->
                // score for a pair as well as three of a kind
                diceFrequencies.containsValue(2) && diceFrequencies.containsValue(3) ? sum(dice) : 0;
        };
    }

    /**
     * @return the sum of the provided dice
     */
    private int sum(List<Integer> dice) {
        return dice.stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    /**
     * @param category is among [ONES, TWOS, THREES, FOURS, FIVES, SIXES]
     * @return the sum of the dice corresponding to the given category
     */
    private int scoreUniqueDiceValue(Map<Integer, Integer> diceFrequencies, YatzyCategory category) {
        int die = category.getAttribute();
        return diceFrequencies.get(die) * die;
    }

    /**
     * @param category is among [PAIR, THREE_OF_A_KIND, FOUR_OF_A_KIND]
     */
    private int scoreOfAKind(Map<Integer, Integer> diceFrequencies, YatzyCategory category) {
        int frequency = category.getAttribute();
        return DICE_VALUES.stream()
            .filter(i -> diceFrequencies.get(i) >= frequency)
            .findFirst()
            .orElse(0) * frequency;
    }

    /**
     * @param category is among [SMALL_STRAIGHT, LARGE_STRAIGHT]
     * @return true if all frequencies, except the one corresponding to the given category, are equal to 1, false otherwise
     */
    private boolean checkAllFrequenciesAreOne(Map<Integer, Integer> diceFrequencies, YatzyCategory category) {
        int dieToIgnore = category.getAttribute();
        return DICE_VALUES.stream().allMatch(i -> i == dieToIgnore || diceFrequencies.get(i) == 1);
    }

    private int scoreForTwoPairs(Map<Integer, Integer> diceFrequencies) {
        List<Integer> pairs = DICE_VALUES.stream()
            .filter(d -> diceFrequencies.get(d) >= 2)
            .toList();
        return pairs.size() >= 2 ? sum(pairs) * 2 : 0;
    }
}

