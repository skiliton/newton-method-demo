package com.repeta.mo;

import com.repeta.mo.alpha.AlphaCalculationStrategy;
import com.repeta.mo.alpha.ConstantAlpha;
import com.repeta.mo.alpha.FirstMethodAlpha;
import com.repeta.mo.alpha.SecondMethodAlpha;
import com.repeta.mo.minimization.MinimizationAlgorithm;
import com.repeta.mo.minimization.NewtonMethod;
import org.ejml.simple.SimpleMatrix;
import java.util.function.Function;

public class App 
{
    public static void main( String[] args )
    {

        Function<SimpleMatrix,Double> f = X -> {
            double x = X.get(0);
            double y = X.get(1);
            return 15*x*x + 18*y*y - 0.03*x*y + x - y;
        };
        NewtonMethod algorithm  = new NewtonMethod(0.001,null,300);
        SimpleMatrix initX = new SimpleMatrix(new double[][]{{10},{10}});

        algorithm.setAlphaCS(new ConstantAlpha(1));
        algorithm.minimize(f,initX).print();

        algorithm.setAlphaCS(new FirstMethodAlpha(0.001));
        algorithm.minimize(f,initX).print();
    }
}
