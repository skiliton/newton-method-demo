package com.repeta.mo.alpha;

import com.repeta.mo.minimization.MinimizationAlgorithm;
import com.repeta.mo.minimization.NewtonMethod;
import org.ejml.simple.SimpleMatrix;

import java.util.function.Function;

public class SecondMethodAlpha implements AlphaCalculationStrategy{

    private MinimizationAlgorithm algorithm;

    public SecondMethodAlpha(MinimizationAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public MinimizationAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(MinimizationAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public double calcAlpha(Function<SimpleMatrix, Double> f, SimpleMatrix Xk, SimpleMatrix Hk, SimpleMatrix df, SimpleMatrix d2f) {
        Function<SimpleMatrix,Double> fAk =  Ak -> {
            double alpha = Ak.get(0);
            return f.apply(Xk.plus(Hk.scale(alpha)));
        };
        return algorithm.minimize(fAk,new SimpleMatrix(new double[][]{{1}})).get(0);
    }

}
