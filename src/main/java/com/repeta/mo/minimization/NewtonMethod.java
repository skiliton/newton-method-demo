package com.repeta.mo.minimization;

import com.repeta.mo.DerivativeHelper;
import com.repeta.mo.alpha.AlphaCalculationStrategy;
import org.ejml.simple.SimpleMatrix;

import java.util.function.Function;

public class NewtonMethod implements MinimizationAlgorithm {

    private double eps;

    private AlphaCalculationStrategy alphaCS;

    private double maxIter;

    public NewtonMethod(double eps, AlphaCalculationStrategy alphaCS, double maxIter) {
        this.eps = eps;
        this.alphaCS = alphaCS;
        this.maxIter = maxIter;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public AlphaCalculationStrategy getAlphaCS() {
        return alphaCS;
    }

    public void setAlphaCS(AlphaCalculationStrategy alphaCS) {
        this.alphaCS = alphaCS;
    }

    public double getMaxIter() {
        return maxIter;
    }

    public void setMaxIter(double maxIter) {
        this.maxIter = maxIter;
    }

    @Override
    public SimpleMatrix minimize(Function<SimpleMatrix, Double> f, SimpleMatrix initX) {
        double dX = eps/10;
        SimpleMatrix nextX = initX;
        SimpleMatrix prevX;
        int iter = 0;
        do {
            iter++;
            prevX = nextX;

            SimpleMatrix df = DerivativeHelper.firstDerivative(f,prevX,dX);
            SimpleMatrix d2f = DerivativeHelper.secondDerivative(f,prevX,dX);
            SimpleMatrix H = d2f.invert().mult(df);
            double alpha = alphaCS.calcAlpha(f,prevX,H,df,d2f);
            nextX = prevX.plus(H.scale(alpha));
        }while (nextX.minus(prevX).normF()>eps && iter<=maxIter);
        return nextX;
    }
}
