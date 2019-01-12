package com.jeesite.modules.bp.edu.packt.neuralnet;


import com.jeesite.modules.bp.edu.packt.neuralnet.math.IActivationFunction;

/**
 *
 * 输出层
 * 这个类表示神经网络的输出层，继承自NeuralLayer，它包含NeuralLayer的所有基本定义
 * 
 *
 *
 */
public class OutputLayer extends NeuralLayer {
    
    /**
     * 输出层的构造函数
     * @param numberofneurons 这一层神经元的数量
     * @param iaf 这一层的激活函数
     * @param numberofinputs 这一层的输入的数量
     */
    public OutputLayer(int numberofneurons, IActivationFunction iaf, int numberofinputs){
        super(numberofneurons,iaf);
        numberOfInputs=numberofinputs;
        nextLayer=null;
        init();
    }
    
    /**
     * 设置后继神经层
     * 此方法可以防止将此层链接到下一层的任何尝试，只要该层始终是最后一层
     * @param layer 虚假的神经层
     */
    @Override
    public void setNextLayer(NeuralLayer layer){
        nextLayer=null;
    }
    
    /**
     * 设置前序神经层
     * 此方法将此层链接到前一层
     * @param layer 前序神经层
     */
    @Override
    public void setPreviousLayer(NeuralLayer layer){
        previousLayer=layer;
        if(layer.nextLayer!=this)
            layer.setNextLayer(this);
    }
    
}
