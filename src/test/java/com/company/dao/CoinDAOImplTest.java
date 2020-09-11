package com.company.dao;

import com.company.model.Coin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CoinDAOImplTest {

    @Test
    public void testGetListOfValidCoins() {
        Coin[] testCoins = Coin.values();
        CoinDaoImpl testList = mock(CoinDaoImpl.class);
        when(testList.getCoinTypes()).thenReturn(testCoins);
        assertEquals(testCoins, testList.getCoinTypes());
        verify(testList, times(1)).getCoinTypes();
    }

    @Test
    public void testGetDimeValue() {
        CoinDaoImpl coinDaoMock = mock(CoinDaoImpl.class);
        when(coinDaoMock.getCoinValue()).thenReturn(.1);
        assertEquals(.1, coinDaoMock.getCoinValue());
        verify(coinDaoMock, times(1)).getCoinValue();
    }

    @Test
    public void testGetCoinInv() {
        Coin quarterCoin = Coin.QUARTER;
        CoinDaoImpl coinDaoMock = mock(CoinDaoImpl.class);
        when(coinDaoMock.getCoinInventory(quarterCoin)).thenReturn(50);
        assertEquals(50, coinDaoMock.getCoinInventory(quarterCoin));
        verify(coinDaoMock, times(1)).getCoinInventory(quarterCoin);
    }

    @Test
    public void testSetCoinInv() {
        CoinDaoImpl coinDaoMock = mock(CoinDaoImpl.class);
        doNothing().when(coinDaoMock).setCoinInventory(isA(Integer.class));
        coinDaoMock.setCoinInventory(15);
        verify(coinDaoMock, times(1)).setCoinInventory(15);
    }
}
