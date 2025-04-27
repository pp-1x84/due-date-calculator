package com.emarsys.interview.calculator;

import java.time.LocalDateTime;

/**
 * Class used to implement a due date calculator  
 */
public interface DueDateCalculator {
    /**
     * 
     * @param submitDateTime timestamp of when the issue was submitted, which must fall on a weekday between 9AM-5PM
     * @param turnaroundTime how long it will take for the issue to be resolved, in hours
     * @return timestamp of when the issue is due
     */
    LocalDateTime calculateDueDate(LocalDateTime submitDateTime, int turnaroundTime);
}
