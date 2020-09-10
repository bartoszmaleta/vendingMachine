package com.company.dao;

import com.company.model.Coin;

public class CoinDaoImpl implements CoinDao {

    @Override
    public Coin[] getCoinTypes() {
        return Coin.values();
    }

    @Override
    public double getCoinValue() {
        return this.getCoinValue();
    }

    @Override
    public int getCoinInventory(Coin cointype) {
        return this.getCoinInventory(cointype);
    }

    @Override
    public void setCoinInventory(int coinInv) {
        this.setCoinInventory(coinInv);
    }
}
