package org.krissssxxxx;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {
    StringCalculator calculator = new StringCalculator();
    @Test
    public void step1(){
        assertEquals("add empty string", 0, calculator.add(""));
        assertEquals("add one number", 1, calculator.add("1"));
        assertEquals("add two numbers", 3, calculator.add("1,2"));
    }
    @Test
    public void step2(){
        assertEquals("add any number of numbers", 33, calculator.add("1,3,4,7,8,10"));
        assertEquals("add any number of numbers", 1108, calculator.add("1000,0,34,74"));
    }
    @Test
    public void step3(){
        assertEquals("add new line as a delimiter", 102, calculator.add("12\n23,67"));
        assertEquals("add new line as a delimiter", 6, calculator.add("1\n2\n3"));
    }
    @Test
    public void step4(){
        assertEquals("add any user`s delimiter", 3, calculator.add("//;\n1;2"));
        assertEquals("add any user`s delimiter", 30, calculator.add("//%\n7,8%5\n10"));
    }
    @Test(expected = RuntimeException.class)
    public void step5(){
        calculator.add("-1,-2,-3");
    }
    @Test
    public void step6(){
        assertEquals("ignoring numbers greater than 1000", 1999, calculator.add("1000,999,1001"));
    }
    @Test
    public void step7(){
        assertEquals("free length for delimiter", 57, calculator.add("//[**]\n54**2**0,1"));
        assertEquals("free length for delimiter", 106, calculator.add("//[%^(@]\n98%^(@7,1\n0"));
    }
    @Test
    public void step8(){
        assertEquals("available any number of delimiters", 23, calculator.add("//[%][!][*]\n6%1,0!6*7\n3"));
    }
    @Test
    public void step9(){
        assertEquals("available any number of free length delimiters", 24, calculator.add("//[%%%][yyy][)]\n7\n0%%%8yyy9"));
    }
}