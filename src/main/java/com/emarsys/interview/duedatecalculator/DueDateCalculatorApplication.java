package com.emarsys.interview.duedatecalculator;

import com.emarsys.interview.duedatecalculator.interfaces.impl.DueDateCalculatorInterfaceImpl;

public class DueDateCalculatorApplication 
{
    public static void main( String[] args )
    {
        DueDateCalculatorInterfaceImpl calculator = new DueDateCalculatorInterfaceImpl();
        
        String result = calculator.calculateDueDate();
        
        System.out.println(result);
    }
}
