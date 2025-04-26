package com.emarsys.interview.duedatecalculator;

import java.time.LocalDateTime;

public interface DueDateCalculator {
    LocalDateTime calculateDueDate(LocalDateTime submitDateTime, int turnaroundTime);
}
