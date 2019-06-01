package com.charles.util;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-05-29
 * Time: 10:31
 */
public class OperationMul extends Operation{

    @Override
    public Double getResult() {
        Double result = 0.0;
        result = getNumberA() * getNumberB();
        return result;
    }
}
