package Lesson02.get;

/**
 * Created by Dongho on 2017. 2. 27..
 */
public class DivideOperator implements Operator {

    @Override
    public String getName() {
        return "/";
    }

    @Override
    public double execute(double a, double b) throws Exception {
        return a / b;
    }

}