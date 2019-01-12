package com.jeesite.modules.bp.edu.packt.neuralnet;


import com.jeesite.modules.bp.edu.packt.neuralnet.math.Linear;

/**
 *
 * 输入神经元
 * 这个类继承了Neuron，因为它是Neuron的一个特例，位于神经网络输入层
 * 
 *
 *
 */
public class InputNeuron extends Neuron {
    
    /**
     * InputNeuron 构造函数
     * 
     * @see InputNeuron
     */
    public InputNeuron(){
        super(1);
        setActivationFunction(new Linear(1));
        bias=0.0;
    }
    
    /**
     * 初始化
     * 用于初始化输入神经元的方法，它只是在偏置时添加具有1值和0的权重
     * 
     * @see InputNeuron
     */
    @Override
    public void init(){
        try{
            this.weight.set(0, 1.0);
            this.weight.set(1, 0.0);
        }
        catch(IndexOutOfBoundsException iobe){
            this.weight.add(1.0);
            this.weight.add(0.0);
        }
    }
    
}
