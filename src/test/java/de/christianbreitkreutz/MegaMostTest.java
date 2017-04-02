package de.christianbreitkreutz;

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
    MegaMost sut;

    @Mock
    MegaMostClientBuilder clientBuilder;

    @Before
    public void before() {
        when(b.increaseByOneIfEven(Matchers.eq(1))).thenReturn(1);

    }

    @Test
    public void testStuff() {

        assertEquals("description", "result", sut.stuff(1));
    }
}