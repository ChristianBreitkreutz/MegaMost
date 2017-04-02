package de.christianbreitkreutz.test;

public class B {

    public int increaseByOneIfEven(final int number) {
        if(number % 2 == 0) {
            return number + 1;
        }
        return number;
    }
}
