package com.nedogeek.calculate.strategy.impl;

import com.nedogeek.calculate.strategy.Strategy;

import static com.nedogeek.calculate.Constants.LOW_BLIND_COEFF;

public class LowStrategy implements Strategy{
    @Override
    public boolean isBlind(double coeff) {
        return coeff >= LOW_BLIND_COEFF;
    }
}
