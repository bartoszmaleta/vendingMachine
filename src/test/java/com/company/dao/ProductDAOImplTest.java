package com.company.dao;

import com.company.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ProductDAOImplTest {

    @Test
    public void testCanGetAProduct() {
        ProductDaoImpl productDaoMock = mock(ProductDaoImpl.class);
        Product[] testProducts = Product.values();
        when(productDaoMock.getProductTypes()).thenReturn(testProducts);
        assertArrayEquals(testProducts, productDaoMock.getProductTypes());
        verify(productDaoMock, times(1)).getProductTypes();
    }

    @Test
    public void testCanGetProductCost() {
        ProductDaoImpl productDaoMock = mock(ProductDaoImpl.class);
        when(productDaoMock.getProductCost()).thenReturn(.50);
        assertEquals(.50, productDaoMock.getProductCost());
        verify(productDaoMock, times(1)).getProductCost();

    }

    @Test
    public void testCanGetInventory(){
        ProductDaoImpl productDaoMock = mock(ProductDaoImpl.class);
        Product candy = Product.CANDY;
        when(productDaoMock.getProductInventory(candy)).thenReturn(10);
        assertEquals(10,productDaoMock.getProductInventory(candy));
        verify(productDaoMock, times(1)).getProductInventory(candy);
    }

    @Test
    public void testSetInventory(){
        ProductDaoImpl productDaoMock = mock(ProductDaoImpl.class);
        doNothing().when(productDaoMock).setProductInventory(9);
        productDaoMock.setProductInventory(9);
        verify(productDaoMock, times(1 )).setProductInventory(9);
    }
}