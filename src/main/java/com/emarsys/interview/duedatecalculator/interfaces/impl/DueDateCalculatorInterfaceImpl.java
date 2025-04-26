package com.emarsys.interview.duedatecalculator.interfaces.impl;

import java.time.LocalDateTime;

import com.emarsys.interview.duedatecalculator.interfaces.DueDateCalculatorInterface;

public class DueDateCalculatorInterfaceImpl implements DueDateCalculatorInterface {
    @Override
    public LocalDateTime calculateDueDate(LocalDateTime submitDateTime, int turnaroundTime) {
        return LocalDateTime.now();
    }
}
