package com.jeesite.modules.bp.edu.packt.neuralnet;

import com.jeesite.modules.bp.edu.packt.neuralnet.math.Linear;

import java.util.ArrayList;

/**
 *
 * 输入层 
 * 这个类扩展自NeuralLayer，表示神经网络的输入层。
 * 
 *
 *
 */
public class InputLayer extends NeuralLayer {
    
    /**
     * 输入层构造函数
     * @param numberofinputs 输入层神经元的数量
     * Network
     * @see InputLayer
     */
    public InputLayer(int numberofinputs){
        super(numberofinputs,new Linear(1));
        previousLayer=null;
        numberOfInputs=numberofinputs;
        init();
    }
    
    /**
     * 设置后继神经层
     * 这种方法把这一层链接到神经网络的下一层。
     * 
     * @param layer 后继神经层
     * @see InputLayer
     */
    @Override
    public void setNextLayer(NeuralLayer layer){
        nextLayer=layer;
        if(layer.previousLayer!=this)
            layer.setPreviousLayer(this);
    }
    
    /**
     * 设置前序神经层 
     * 此方法可以防止任何将此层链接到前一层的尝试，只要这应该是第一层
     * 
     * @param layer 假神经层
     * @see InputLayer
     */
    @Override
    public void setPreviousLayer(NeuralLayer layer){
        previousLayer=null;
    }
    
    /**
     * 初始化
     * 此方法初始化该层的所有神经元。
     * 
     * @see InputLayer
     */
    @Override
    public void init(){
        for(int i=0;i<numberOfInputs;i++){
            this.setNeuron(i,new InputNeuron());
            this.getNeuron(i).init();
        }
    }
    
    /**
     * 设置输入
     * 此方法将一个实值数组反馈到该层的输入中
     * 
     * @param inputs 要输入的数组
     * @see InputLayer
     */
    @Override
    public void setInputs(ArrayList<Double> inputs){
        if(inputs.size()==numberOfInputs){
            input=inputs;
        }
    }
    
    /**
     * 计算
     * 此方法重写超类calc，因为它只是将输入值传递给输出，前提是这是输入层
     * 
     * @see InputLayer
     */
    @Override
    public void calc(){
        if(input!=null && getListOfNeurons()!=null){
            for(int i=0;i<numberOfNeuronsInLayer;i++){
                double[] firstInput = {this.input.get(i)};
                getNeuron(i).setInputs(firstInput);
                getNeuron(i).calc();
                try{
                    output.set(i,getNeuron(i).getOutput());
                }
                catch(IndexOutOfBoundsException iobe){
                    output.add(getNeuron(i).getOutput());
                }
            }
        }
    }
    
}
