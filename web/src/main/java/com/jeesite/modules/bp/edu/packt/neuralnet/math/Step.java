package com.jeesite.modules.bp.edu.packt.neuralnet.math;

/**
 *
 * Step
 * 这个类实现接口IActivationFunction来表示HardlimitedThreshold或Step函数
 *
 *
 */
public class Step implements IActivationFunction {
    
    /**
     * 计算
     * 返回硬限制阈值函数的结果的方法
     * @param x 
     * @return 
     */
    @Override
    public double calc(double x){
        if(x<0)
            return 0.0;
        else
            return 1.0;
    }
    
    @Override
    public double derivative(double x){
        if(x==0)
            return Double.MAX_VALUE;
        else
            return 0.0;
            
    }
    
}
