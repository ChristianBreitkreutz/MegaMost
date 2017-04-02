package de.christianbreitkreutz.test;

import javax.inject.Inject;

public class A {
    @Inject
    private B b;


    public String drink(final int number) {
        if (b.increaseByOneIfEven(number) == number) {
            return "You have to drink";
        }
        return "Stay sober, bastard!";
    }
}
