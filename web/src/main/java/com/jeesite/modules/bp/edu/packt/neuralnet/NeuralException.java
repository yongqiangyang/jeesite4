package com.jeesite.modules.bp.edu.packt.neuralnet;

/**
 *
 * 神经异常
 * 这个类将用于在编程神经网络时抛出和捕捉任何异常
 * 
 *
 *
 */
public class NeuralException extends Exception {
    
    /**
     * NeuralException 构造函数
     * 
     * @param message 显示有异常的消息
     * @see NeuralException
     */
    public NeuralException(String message){
        super(message);
    }
}
