package com.nedogeek.calculate.strategy.impl;

import com.nedogeek.calculate.strategy.Strategy;

import static com.nedogeek.calculate.Constants.HIGH_BLIND_COEFF;

public class HighStrategy  implements Strategy {

    @Override
    public boolean isBlind(double coeff) {
        return coeff >= HIGH_BLIND_COEFF;
    }
}
