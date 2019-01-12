package com.jeesite.modules.bp.edu.packt.neuralnet.math;

/**
 *
 * HyperTan
 * 这个类表示实现接口IActivationFunction的双曲正切激活函数
 * 
 *
 */
public class HyperTan implements IActivationFunction {
    
    /**
     * 双曲正切系数 
     */
    private double a=1.0;
    
    /**
     * HyperTan 虚拟构造函数
     */
    public HyperTan(){
        
    }
    
    /**
     * HyperTan 构造函数
     * @param value 新系数
     */
    public HyperTan(double value){
        this.setA(value);
    }
    
    /**
     * 设置A
     * 为这个函数设置一个新系数
     * @param value 新的系数
     */
    public void setA(double value){
        this.a=value;
    }
    
    /**
     * 计算
     * hyperbolic的计算方法
     * @param x 
     * @return 返回x的双曲正切。
     */
    @Override
    public double calc(double x){
        return (1.0-Math.exp(-a*x))/(1.0+Math.exp(-a*x));
    }
    
    @Override 
    public double derivative(double x){
        return (1.0)-Math.pow(calc(x),2.0);
    }
    
}
