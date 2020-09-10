package com.company.model;

public enum Product {
    CHIPS(.50),
    COLA(1.00),
    CANDY(.65);

    private int productInventory = 10;

    private final double productCost;
    Product(double productCost){
        this.productCost = productCost;
    }


    public double getProductCost() {
        return productCost;
    }

    public int getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(int prodInvt){
        this.productInventory = prodInvt;
    }

}
