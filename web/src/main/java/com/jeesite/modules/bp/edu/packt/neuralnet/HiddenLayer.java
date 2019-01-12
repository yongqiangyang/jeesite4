
package com.jeesite.modules.bp.edu.packt.neuralnet;


import com.jeesite.modules.bp.edu.packt.neuralnet.math.IActivationFunction;

/**
 *
 * 隐藏层
 * 这个类扩展自NeuralLayer，表示神经网络中的隐藏层。
 * 
 *
 *
 */
public class HiddenLayer extends NeuralLayer {
    
    /**
     * 隐藏层的构造函数
     * 
     * @param numberofneurons 隐藏层神经元的数量
     * @param iaf 这一层所有神经元的激活函数
     * @param numberofinputs 这一层输入的数量
     * @see HiddenLayer
     */
    public HiddenLayer(int numberofneurons, IActivationFunction iaf,
                       int numberofinputs){
        super(numberofneurons,iaf);
        numberOfInputs=numberofinputs;
        init();
    }
    
    /**
     * 设置前序神经层
     * 此方法将此层链接到神经网络的前一层。
     * 
     * @param previous 前序神经层
     * @see HiddenLayer
     */
    @Override
    public void setPreviousLayer(NeuralLayer previous){
        this.previousLayer=previous;
        if(previous.nextLayer!=this)
            previous.setNextLayer(this);
    }
    
    /**
     * 设置后继神经层
     * 这种方法把这一层链接到神经网络的下一层。
     * 
     * @param next 后继神经层
     * @see HiddenLayer
     */
    @Override
    public void setNextLayer(NeuralLayer next){
        nextLayer=next;
        if(next.previousLayer!=this)
            next.setPreviousLayer(this);
    }
    
}
