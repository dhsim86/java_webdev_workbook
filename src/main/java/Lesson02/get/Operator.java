package Lesson02.get;

/**
 * Created by Dongho on 2017. 2. 27..
 */
public interface Operator {
    String getName();
    double execute(double a, double b) throws Exception;
}