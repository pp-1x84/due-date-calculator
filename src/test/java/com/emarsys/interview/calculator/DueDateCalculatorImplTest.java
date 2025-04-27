package com.emarsys.interview.calculator;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Unit test for simple App.
 */
public class DueDateCalculatorImplTest {
    @ParameterizedTest
    @MethodSource("provideHappyPathScenarios")
    public void test_calculateDueDate_happyPath(String condition, LocalDateTime submitDateTime, int turnaroundTime, LocalDateTime expectedDueDate) {
        DueDateCalculatorImpl calculator = new DueDateCalculatorImpl();

        LocalDateTime result = calculator.calculateDueDate(submitDateTime, turnaroundTime);
        Assertions.assertEquals(expectedDueDate, result, condition);
    }

    @ParameterizedTest
    @MethodSource("provideExceptionScenarios")
    public void test_calculateDueDate_exception(String condition, LocalDateTime submitDateTime, int turnaroundTime, Class<? extends Throwable> exceptionClass) {
        DueDateCalculatorImpl calculator = new DueDateCalculatorImpl();
        
        Assertions.assertThrows(exceptionClass, () -> calculator.calculateDueDate(submitDateTime, turnaroundTime), condition);
    }

    private static Stream<Arguments> provideHappyPathScenarios() {
        return Stream.of(
            Arguments.of(
                "Happy Path Test 1: Zero turnaround",
                LocalDateTime.of(2025, Month.APRIL, 7, 14, 12),
                0,
                LocalDateTime.of(2025, Month.APRIL, 7, 14, 12)
            ),
            Arguments.of(
                "Happy Path Test 2: Same day turnaround",
                LocalDateTime.of(2025, Month.APRIL, 7, 14, 12),
                2,
                LocalDateTime.of(2025, Month.APRIL, 7, 16, 12)
            ),
            Arguments.of(
                "Happy Path Test 3: Next day turnaround",
                LocalDateTime.of(2025, Month.APRIL, 7, 9, 0),
                9,
                LocalDateTime.of(2025, Month.APRIL, 8, 10, 0)
            ),
            Arguments.of(
                "Happy Path Test 4: Next week (weekend) turnaround",
                LocalDateTime.of(2025, Month.APRIL, 11, 15, 0),
                8,
                LocalDateTime.of(2025, Month.APRIL, 14, 15, 0)
            ),
            Arguments.of(
                "Happy Path Test 5: Multiple-week turnaround",
                LocalDateTime.of(2025, Month.APRIL, 9, 9, 0),
                48,
                LocalDateTime.of(2025, Month.APRIL, 17, 9, 0)
            ),
            Arguments.of(
                "Happy Path Test 6: Next month turnaround",
                LocalDateTime.of(2025, Month.APRIL, 30, 9, 0),
                8,
                LocalDateTime.of(2025, Month.MAY, 1, 9, 0)
            ),
            Arguments.of(
                "Happy Path Test 7: Next year turnaround",
                LocalDateTime.of(2025, Month.DECEMBER, 31, 9, 0),
                16,
                LocalDateTime.of(2026, Month.JANUARY, 2, 9, 0)
            )
        );
    }

    private static Stream<Arguments> provideExceptionScenarios() {
        return Stream.of(
            Arguments.of(
                "Exception Test 1: submitDateTime falls within non-working hours",
                LocalDateTime.of(2025, Month.APRIL, 9, 17, 15),
                8,
                IllegalArgumentException.class
            ),
            Arguments.of(
                "Exception Test 2: submitDateTime falls within non-working days",
                LocalDateTime.of(2025, Month.APRIL, 12, 9, 0),
                16,
                IllegalArgumentException.class
            )
        );
    }
}
