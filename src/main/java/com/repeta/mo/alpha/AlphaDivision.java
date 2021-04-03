package com.repeta.mo.alpha;

import org.ejml.simple.SimpleMatrix;

import java.util.function.Function;

public class AlphaDivision implements AlphaCalculationStrategy{

    private double eps;

    public AlphaDivision(double eps) {
        this.eps = eps;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    @Override
    public double calcAlpha(Function<SimpleMatrix, Double> f, SimpleMatrix Xk, SimpleMatrix Hk, SimpleMatrix df, SimpleMatrix d2f) {
        double alpha = 2;
        double fXk = f.apply(Xk);
        double fX;
        do{
            alpha=alpha/2;
            fX = f.apply(Xk.plus(Hk.scale(alpha)));
        }while (fX - fXk > eps*alpha*df.dot(Hk));
        return alpha;
    }
}
