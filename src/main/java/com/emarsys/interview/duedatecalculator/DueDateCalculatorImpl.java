package com.emarsys.interview.duedatecalculator;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DueDateCalculatorImpl implements DueDateCalculator {

    int TOTAL_WORKING_HOURS_IN_DAY = 8;
    
    LocalTime WORKING_HOURS_START = LocalTime.of(9, 0);
    LocalTime WORKING_HOURS_END = LocalTime.of(17, 0);

    @Override
    public LocalDateTime calculateDueDate(LocalDateTime submitDateTime, int turnaroundTime) throws IllegalArgumentException {
        boolean isWeekend = isWeekend(submitDateTime);
        boolean isNotWorkingHours = isNotWorkingHours(submitDateTime.toLocalTime());

        if (isWeekend || isNotWorkingHours) {
            throw new IllegalArgumentException("Invalid argument encountered! submitDateTime must fall on weekdays between 9:00 AM - 5:00 PM");
        }

        int remainingDays = turnaroundTime / TOTAL_WORKING_HOURS_IN_DAY;
        int remainingHours = turnaroundTime % TOTAL_WORKING_HOURS_IN_DAY;

        LocalDateTime dueDateTime = submitDateTime;

        // Update due date in full-day increments
        dueDateTime = addWorkingDays(dueDateTime, remainingDays);

        // Update due date with remaining hours
        dueDateTime = addRemainingWorkingHours(dueDateTime, remainingHours);

        return dueDateTime;
    }

    private LocalDateTime addWorkingDays(LocalDateTime dateTime, int workingDaysToAdd) {
        int daysAdded = 0;

        while (daysAdded < workingDaysToAdd) {
            dateTime = dateTime.plusDays(1);
            if (!isWeekend(dateTime)) {
                daysAdded++;
            }
        }

        return dateTime;
    }

    private LocalDateTime addRemainingWorkingHours(LocalDateTime dateTime, int remainingHours) {
        // If the remaining hours don't fit within today's working hours, move to the next valid working day
        while (remainingHours > 0) {
            LocalTime time = dateTime.toLocalTime();
            long availableHoursToday = Duration.between(time, WORKING_HOURS_END).toHours();

            if (remainingHours <= availableHoursToday) {
                dateTime = dateTime.plusHours(remainingHours);
                remainingHours = 0;
            } else {
                remainingHours -= availableHoursToday;
                dateTime = dateTime.plusDays(1);
                // Skip non-working days
                while (isWeekend(dateTime)) {
                    dateTime = dateTime.plusDays(1);
                }
            }
        }

        return dateTime;
    }

    private boolean isWeekend(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }

    private boolean isNotWorkingHours(LocalTime time) {
        return time.isBefore(WORKING_HOURS_START) || time.isAfter(WORKING_HOURS_END);
    }
}
