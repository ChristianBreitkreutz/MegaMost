package de.christianbreitkreutz.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class BTest {

    @Test
    public void test() {
        B b = new B();
        assertEquals("1 = 1", 1, b.increaseByOneIfEven(1));
        assertEquals("2 = 3", 3, b.increaseByOneIfEven(2));
    }

}
