package de.christianbreitkreutz.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ATest {
    @InjectMocks
    A sut;

    @Mock
    B b;

    @Before
    public void before() {
        when(b.increaseByOneIfEven(Matchers.eq(1))).thenReturn(1);
        when(b.increaseByOneIfEven(Matchers.eq(2))).thenReturn(3);
        when(b.increaseByOneIfEven(Matchers.eq(3))).thenReturn(3);

    }
    @Test
    public void testDrink() {
        assertEquals("If odd reaction", "You have to drink", sut.drink(1));
        assertEquals("If even reaction", "Stay sober, bastard!", sut.drink(2));
        assertEquals("If even reaction", "Stay sober, bastard!", sut.drink(3));
    }
}