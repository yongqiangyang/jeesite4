package com.jeesite.modules.bp.edu.packt.neuralnet.math;

/**
 *
 * 激活函数接口
 *
 * 该接口表示适用于神经网络的激活函数。
 * 任何激活函数都应该实现这个接口，以便用于神经计算。
 * 
 */
public interface IActivationFunction {
    /**
     * 计算
     * 这是计算激活函数值的核心方法。
     * @param x 
     * @return 返回给定x的激活函数的结果
     */
    double calc(double x);
    
    /**
     *  枚举激活函数
     *  此枚举列出了一些常用的激活函数。实用程序是将此值存储为神经网络属性。
     */
    public enum ActivationFunctionENUM {
        STEP, LINEAR, SIGMOID, HYPERTAN
    }
    
    double derivative(double x);
    
}
