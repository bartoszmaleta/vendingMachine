package com.company.dao;

import com.company.model.Product;

public interface ProductDao {
    Product[] getProductTypes();
    double getProductCost();
    int getProductInventory(Product productType);
    void setProductInventory(int productInventory);
}
