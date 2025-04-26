package com.emarsys.interview.duedatecalculator.interfaces;

import java.time.LocalDateTime;

public interface DueDateCalculatorInterface {
    LocalDateTime calculateDueDate(LocalDateTime submitDateTime, int turnaroundTime);
}
