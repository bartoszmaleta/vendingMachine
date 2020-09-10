package com.company.dao;

import com.company.model.Coin;

public interface CoinDao {
    Coin[] getCoinTypes();
    double getCoinValue();
    int getCoinInventory(Coin cointype);
    void setCoinInventory(int coinInv);
}
