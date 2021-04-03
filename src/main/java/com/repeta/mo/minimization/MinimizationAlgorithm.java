package com.repeta.mo.minimization;

import org.ejml.simple.SimpleMatrix;
import java.util.function.Function;

public interface MinimizationAlgorithm {
    public SimpleMatrix minimize(Function<SimpleMatrix,Double> f, SimpleMatrix initX);
}
