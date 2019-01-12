package com.jeesite.modules.bp.edu.packt.neuralnet.math;

/**
 *
 * Sigmoide
 * 这个类表示sigmoid激活函数，实现接口IActivationFunction
 * 
 */
public class Sigmoid implements IActivationFunction {
    /**
     * sigmoid函数中的系数
     */
    private double a=1.0;
    
    /**
     * sigmoid 虚拟构造函数
     */
    public Sigmoid(){
        
    }
    
    /**
     * Sigmoid 构造函数
     * @param value new coefficient for the sigmoid function
     */
    public Sigmoid(double value){
        this.setA(value);
    }
    /**
     * 设置a
     * 为sigmoid构造函数设置新系数
     * @param value 
     */
    public void setA(double value){
        this.a=value;
    }
    
    /**
     * 计算
     * 执行此函数的计算
     * @param x
     * @return 返回此函数的结果
     */
    @Override
    public double calc(double x){
        return 1.0/(1.0+Math.exp(-a*x));
    }
    
    @Override 
    public double derivative(double x){
        return calc(x)*(1-calc(x));
    }
}
