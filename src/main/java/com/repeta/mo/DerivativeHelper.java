package com.repeta.mo;

import org.ejml.simple.SimpleMatrix;
import java.util.function.Function;

public class DerivativeHelper {
    public static SimpleMatrix firstDerivative(Function<SimpleMatrix,Double> f, SimpleMatrix X, double dX){
        int n = X.numRows();
        SimpleMatrix dF = new SimpleMatrix(n,1);
        for (int i=0; i<n; i++){
            SimpleMatrix h = new SimpleMatrix(n,1);
            h.set(i,dX);
            double dFdXi = (f.apply(X.plus(h))- f.apply(X))/dX;
            dF.set(i,dFdXi);
        }
        return dF;
    }
    public static SimpleMatrix secondDerivative(Function<SimpleMatrix,Double> f, SimpleMatrix X, double dX){
        assert X.numRows()!=2 : "Second derivative works only with functions of 2 or less variables";
        int n = X.numRows();
        SimpleMatrix d2F = new SimpleMatrix(n,n);
        for (int i=0; i<n; i++){
            SimpleMatrix h = new SimpleMatrix(n,1);
            h.set(i,dX);
            double d2FdXi2 = (-f.apply(X.minus(h))+2*f.apply(X)-f.apply(X.plus(h)))/(dX*dX);
            d2F.set(i,i,d2FdXi2);
        }
        if(n==2){
            SimpleMatrix Hplusplus = new SimpleMatrix(n,1);
            Hplusplus.set(0,dX);
            Hplusplus.set(1,dX);
            SimpleMatrix Hplusminus = new SimpleMatrix(n,1);
            Hplusminus.set(0,dX);
            Hplusminus.set(1,-dX);

            double temp = (f.apply(X.plus(Hplusplus)) - f.apply(X.plus(Hplusminus)) - f.apply(X.minus(Hplusminus)) + f.apply(X.minus(Hplusplus)))/(4*dX*dX);
            d2F.set(0,1,temp);
            d2F.set(1,0,temp);
        }
        return d2F;
    }
}
