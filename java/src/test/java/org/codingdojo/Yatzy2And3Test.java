package org.codingdojo;

import org.codingdojo.yatzy2.Yatzy2;
import org.codingdojo.yatzy3.Yatzy3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class Yatzy2And3Test {

    static Stream<YatzyCalculator> yatzyProvider() {
        return Stream.of(new Yatzy2(), new Yatzy3());
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_invalid_parameters(YatzyCalculator calculator) {
        assertEquals(-1, calculator.score(null, "CHANCE"));
        assertEquals(-1, calculator.score(List.of(1, 2), "YATZY"));
        assertEquals(-1, calculator.score(List.of(1, 2, 3, 4, 5, 6, 5, 4), "ONES"));
        assertEquals(-1, calculator.score(List.of(1, 3, 8, 5, 4), "LARGE_STRAIGHT"));
        assertThrows(IllegalArgumentException.class, () -> calculator.score(List.of(1, 3, 1, 5, 4), "INVALID_CATEGORY"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_chance(YatzyCalculator calculator) {
        assertEquals(15, calculator.score(List.of(2, 3, 4, 5, 1), "CHANCE"));
        assertEquals(16, calculator.score(List.of(3, 3, 4, 5, 1), "CHANCE"));
        assertEquals(30, calculator.score(List.of(6, 6, 6, 6, 6), "CHANCE"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_yatzy(YatzyCalculator calculator) {
        assertEquals(50, calculator.score(List.of(4, 4, 4, 4, 4), "YATZY"));
        assertEquals(50, calculator.score(List.of(6, 6, 6, 6, 6), "YATZY"));
        assertEquals(0, calculator.score(List.of(6, 6, 6, 6, 3), "YATZY"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_ones(YatzyCalculator calculator) {
        assertEquals(1, calculator.score(List.of(1, 2, 3, 4, 5), "ONES"));
        assertEquals(2, calculator.score(List.of(1, 2, 1, 4, 5), "ONES"));
        assertEquals(0, calculator.score(List.of(6, 2, 2, 4, 5), "ONES"));
        assertEquals(5, calculator.score(List.of(1, 1, 1, 1, 1), "ONES"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_twos(YatzyCalculator calculator) {
        assertEquals(4, calculator.score(List.of(1, 2, 3, 2, 6), "TWOS"));
        assertEquals(10, calculator.score(List.of(2, 2, 2, 2, 2), "TWOS"));
        assertEquals(0, calculator.score(List.of(3, 5, 5, 1, 4), "TWOS"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_threes(YatzyCalculator calculator) {
        assertEquals(6, calculator.score(List.of(1, 2, 3, 2, 3), "THREES"));
        assertEquals(12, calculator.score(List.of(2, 3, 3, 3, 3), "THREES"));
        assertEquals(0, calculator.score(List.of(2, 1, 2, 6, 5), "THREES"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_fours(YatzyCalculator calculator) {
        assertEquals(12, calculator.score(List.of(4, 4, 4, 5, 5), "FOURS"));
        assertEquals(8, calculator.score(List.of(4, 4, 5, 5, 5), "FOURS"));
        assertEquals(4, calculator.score(List.of(4, 5, 5, 5, 5), "FOURS"));
        assertEquals(0, calculator.score(List.of(1, 5, 6, 3, 3), "FOURS"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_fives(YatzyCalculator calculator) {
        assertEquals(10, calculator.score(List.of(4, 4, 4, 5, 5), "FIVES"));
        assertEquals(15, calculator.score(List.of(4, 4, 5, 5, 5), "FIVES"));
        assertEquals(20, calculator.score(List.of(4, 5, 5, 5, 5), "FIVES"));
        assertEquals(0, calculator.score(List.of(3, 2, 4, 4, 1), "FIVES"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_sixes(YatzyCalculator calculator) {
        assertEquals(0, calculator.score(List.of(4, 4, 4, 5, 5), "SIXES"));
        assertEquals(6, calculator.score(List.of(4, 4, 6, 5, 5), "SIXES"));
        assertEquals(18, calculator.score(List.of(6, 5, 6, 6, 5), "SIXES"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_pair(YatzyCalculator calculator) {
        assertEquals(6, calculator.score(List.of(3, 4, 3, 5, 6), "PAIR"));
        assertEquals(10, calculator.score(List.of(5, 3, 3, 3, 5), "PAIR"));
        assertEquals(6, calculator.score(List.of(2, 3, 3, 3, 2), "PAIR"));
        assertEquals(12, calculator.score(List.of(5, 3, 6, 6, 5), "PAIR"));
        assertEquals(0, calculator.score(List.of(1, 2, 4, 5, 6), "PAIR"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_two_pairs(YatzyCalculator calculator) {
        assertEquals(16, calculator.score(List.of(3, 3, 5, 4, 5), "TWO_PAIRS"));
        assertEquals(16, calculator.score(List.of(3, 3, 5, 5, 5), "TWO_PAIRS"));
        assertEquals(0, calculator.score(List.of(3, 3, 3, 3, 5), "TWO_PAIRS"));
        assertEquals(0, calculator.score(List.of(3, 1, 3, 4, 5), "TWO_PAIRS"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_three_of_a_kind(YatzyCalculator calculator) {
        assertEquals(9, calculator.score(List.of(3, 3, 3, 4, 5), "THREE_OF_A_KIND"));
        assertEquals(15, calculator.score(List.of(5, 3, 5, 4, 5), "THREE_OF_A_KIND"));
        assertEquals(9, calculator.score(List.of(3, 3, 3, 3, 5), "THREE_OF_A_KIND"));
        assertEquals(0, calculator.score(List.of(6, 2, 1, 2, 4), "THREE_OF_A_KIND"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_four_of_a_kind(YatzyCalculator calculator) {
        assertEquals(12, calculator.score(List.of(3, 3, 3, 3, 5), "FOUR_OF_A_KIND"));
        assertEquals(20, calculator.score(List.of(5, 5, 5, 4, 5), "FOUR_OF_A_KIND"));
        assertEquals(12, calculator.score(List.of(3, 3, 3, 3, 3), "FOUR_OF_A_KIND"));
        assertEquals(0, calculator.score(List.of(5, 4, 2, 1, 2), "FOUR_OF_A_KIND"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_small_straight(YatzyCalculator calculator) {
        assertEquals(15, calculator.score(List.of(1, 2, 3, 4, 5), "SMALL_STRAIGHT"));
        assertEquals(0, calculator.score(List.of(2, 3, 4, 5, 6), "SMALL_STRAIGHT"));
        assertEquals(0, calculator.score(List.of(1, 2, 3, 5, 6), "SMALL_STRAIGHT"));
        assertEquals(15, calculator.score(List.of(2, 3, 4, 5, 1), "SMALL_STRAIGHT"));
        assertEquals(0, calculator.score(List.of(1, 2, 2, 4, 5), "SMALL_STRAIGHT"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_large_straight(YatzyCalculator calculator) {
        assertEquals(20, calculator.score(List.of(6, 2, 3, 4, 5), "LARGE_STRAIGHT"));
        assertEquals(0, calculator.score(List.of(1, 2, 3, 4, 5), "LARGE_STRAIGHT"));
        assertEquals(0, calculator.score(List.of(1, 2, 3, 5, 6), "LARGE_STRAIGHT"));
        assertEquals(20, calculator.score(List.of(2, 3, 4, 5, 6), "LARGE_STRAIGHT"));
        assertEquals(0, calculator.score(List.of(1, 2, 2, 4, 5), "LARGE_STRAIGHT"));
    }

    @ParameterizedTest
    @MethodSource("yatzyProvider")
    public void test_full_house(YatzyCalculator calculator) {
        assertEquals(18, calculator.score(List.of(6, 2, 2, 2, 6), "FULL_HOUSE"));
        assertEquals(0, calculator.score(List.of(2, 3, 4, 5, 6), "FULL_HOUSE"));
        assertEquals(0, calculator.score(List.of(4, 4, 4, 4, 6), "FULL_HOUSE"));
        assertEquals(0, calculator.score(List.of(5, 5, 5, 5, 5), "FULL_HOUSE"));
    }

}
