package org.codingdojo;

import java.util.List;

public interface YatzyCalculator {

    /**
     * Calculate the score of a given dice for a given category
     */
    int score(List<Integer> dice, String categoryName);
}
