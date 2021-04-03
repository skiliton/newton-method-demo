package com.repeta.mo.alpha;

import org.ejml.simple.SimpleMatrix;

import java.util.function.Function;

public interface AlphaCalculationStrategy {
    public double calcAlpha(Function<SimpleMatrix,Double> f, SimpleMatrix X, SimpleMatrix H, SimpleMatrix df, SimpleMatrix d2f);
}
