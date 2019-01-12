package com.jeesite.modules.bp.edu.packt.neuralnet.math;

/**
 *
 * Linear
 * 这个类表示纯线性激活函数，实现
 * 接口IActivation函数
 * 
 */
public class Linear implements IActivationFunction {
    
    /**
     * 乘以x的系数
     */
    private double a=1.0;
    
    /** 
     * Linear 虚拟构造函数
     */
    public Linear(){
        
    }
    
    /**
     * Linear 构造函数
     * @param value 线性函数系数
     */
    public Linear(double value){
        this.setA(value);
    }
    
    /** 
     * 设置A
     * 为线性函数设置新系数
     * @param value 线性函数的新系数
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
        return a*x;
    }
    
    @Override 
    public double derivative(double x){
        return a;
    }
    
}
