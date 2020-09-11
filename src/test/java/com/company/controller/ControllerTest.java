package com.company.controller;

import com.company.model.Coin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Test
    public void testAcceptableRangeForCoins() {
        Controller controller = mock(Controller.class);
        when(controller.acceptableRangeForCoins(11)).thenReturn(false);
        assertFalse(controller.acceptableRangeForCoins(11));
        verify(controller).acceptableRangeForCoins(11);
    }

    @Test
    public void testDeterminePaymentTotal() {
        Coin coinTest = Coin.QUARTER;
        Controller controller = mock(Controller.class);
        doNothing().when(controller).calculatePaymentTotal(isA(Double.class), isA(Coin.class), isA(Integer.class));
        controller.calculatePaymentTotal(2.0, coinTest, 11);
        verify(controller, times(1)).calculatePaymentTotal(2.0, coinTest, 11);
    }

    @Test
    public void testCheckCoinInventory() {
        Coin coinTest = Coin.QUARTER;
        Controller controller = mock(Controller.class);
        when(controller.checkCoinInventory(coinTest)).thenReturn(true);
        assertTrue(controller.checkCoinInventory(coinTest));
    }

    @Test
    public void testMakeChange() {
        Controller controller = mock(Controller.class);
        doNothing().when(controller).makeChange(isA(Integer.class),isA(Integer.class),isA(Integer.class) );
        controller.makeChange(8,8,8);
        verify(controller, times(1)).makeChange(8,8,8);
    }
}
