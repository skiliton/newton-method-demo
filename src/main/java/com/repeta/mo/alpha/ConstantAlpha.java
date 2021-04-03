package com.repeta.mo.alpha;

import org.ejml.simple.SimpleMatrix;

import java.util.function.Function;

public class ConstantAlpha implements AlphaCalculationStrategy{

    private double alpha;

    public ConstantAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    @Override
    public double calcAlpha(Function<SimpleMatrix, Double> f, SimpleMatrix X, SimpleMatrix H, SimpleMatrix df, SimpleMatrix d2f) {
        return alpha;
    }
}
