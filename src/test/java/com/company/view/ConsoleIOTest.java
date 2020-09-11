package com.company.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleIOTest {

    @Test
    public void testQueryUserInt() {
        ConsoleIO cons = mock(ConsoleIO.class);
        when(cons.queryUserInt("How many would you like?")).thenReturn(5);
        assertEquals(5, cons.queryUserInt("How many would you like?"));
        verify(cons, times(1)).queryUserInt("How many would you like?");
    }

    @Test
    public void testQueryUserIntRange() {
        ConsoleIO cons = mock(ConsoleIO.class);
        when(cons.queryUserIntRange("How many would you like?", 1, 10)).thenReturn(6);
        assertEquals(6, cons.queryUserIntRange("How many would you like?", 1, 10));
        verify(cons, times(1)).queryUserIntRange("How many would you like?", 1, 10);
    }

    @Test
    public void testQueryUserString() {
        ConsoleIO cons = mock(ConsoleIO.class);
        when(cons.queryUserString("Which product would you like?")).thenReturn("chips");
        assertEquals("chips", cons.queryUserString("Which product would you like?"));
        verify(cons, times(1)).queryUserString("Which product would you like?");
    }
}