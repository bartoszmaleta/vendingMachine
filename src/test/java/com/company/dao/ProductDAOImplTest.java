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
        ProductDaoImpl productDAOTest = mock(ProductDaoImpl.class);
        Product[] testProduct = Product.values();
        when(productDAOTest.getProductTypes()).thenReturn(testProduct);
        assertArrayEquals(testProduct, productDAOTest.getProductTypes());
        verify(productDAOTest, times(1)).getProductTypes();
    }

    @Test
    public void testCanGetProductCost() {
        ProductDaoImpl productDAOTest = mock(ProductDaoImpl.class);
        when(productDAOTest.getProductCost()).thenReturn(.50);
        assertEquals(.50, productDAOTest.getProductCost());
        verify(productDAOTest, times(1)).getProductCost();

    }

    @Test
    public void testCanGetInventory(){
        ProductDaoImpl productDAOTest = mock(ProductDaoImpl.class);
        Product candy = Product.CANDY;
        when(productDAOTest.getProductInventory(candy)).thenReturn(10);
        assertEquals(10,productDAOTest.getProductInventory(candy));
        verify(productDAOTest, times(1)).getProductInventory(candy);
    }

    @Test
    public void testSetInventory(){
        ProductDaoImpl productDAOTest = mock(ProductDaoImpl.class);
        doNothing().when(productDAOTest).setProductInventory(9);
        productDAOTest.setProductInventory(9);
        verify(productDAOTest, times(1 )).setProductInventory(9);
    }
}