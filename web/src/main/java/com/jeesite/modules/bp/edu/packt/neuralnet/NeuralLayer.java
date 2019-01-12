package com.jeesite.modules.bp.edu.packt.neuralnet;


import com.jeesite.modules.bp.edu.packt.neuralnet.math.IActivationFunction;

import java.util.ArrayList;

/**
 * 
 * 神经层
 * 
 * 这是任何神经层的抽象类。所有一般属性和
 * 在这个类中定义了神经元层固有的行为。
 * 
 */
public abstract class NeuralLayer {
    
    /**
     * 这一层神经元的数目
     */
    protected int numberOfNeuronsInLayer;
    /**
     * 这一层的神经元阵列
     */
    private ArrayList<Neuron> neuron;
    
    /**
     * 这一层的激活函数
     */
    protected IActivationFunction activationFnc;
    
    /**
     * 前一层，向该层提供值
     */
    protected NeuralLayer previousLayer;
    /**
     * 下一层，该层将向其提供值
     */
    protected NeuralLayer nextLayer;
    
    /**
     * 输入到此层的输入值数组
     */
    protected ArrayList<Double> input;
    /**
     * 此层将产生的输出值数组
     */
    protected ArrayList<Double> output;
    
    /**
     * 此层可以接收的输入数量
     */
    protected int numberOfInputs;
    
    /**
     * 神经层的构造函数
     * 
     * @param numberofneurons 这一层的神经元的数量
     * @see NeuralLayer
     */
    public NeuralLayer(int numberofneurons){
        this.numberOfNeuronsInLayer=numberofneurons;
        neuron = new ArrayList<>(numberofneurons);
        output = new ArrayList<>(numberofneurons);
    }
    
    /**
     * 神经层的构造函数
     * 
     * @param numberofneurons 这一层的神经元的数量
     * @param iaf 这一层所有神经元的激活功能
     * @see NeuralLayer
     */
    public NeuralLayer(int numberofneurons,IActivationFunction iaf){
        this.numberOfNeuronsInLayer=numberofneurons;
        this.activationFnc=iaf;
        neuron = new ArrayList<>(numberofneurons);
        output = new ArrayList<>(numberofneurons);
    }
    
    /**
     * 得到这一层的神经元数量
     * @return 返回此层中的神经元数量
     */
    public int getNumberOfNeuronsInLayer(){
        return numberOfNeuronsInLayer;
    }
    
    /**
     * 返回神经元的阵列
     * @return 返回该层的整个神经元阵列。
     */
    public ArrayList<Neuron> getListOfNeurons(){
        return neuron;
    }
    
    /**
     * 得到先前神经层
     * @return 返回对前一层的引用
     */
    protected NeuralLayer getPreviousLayer(){
        return previousLayer;
    }
    
    /**
     * 得到后继神经层
     * @return 返回对下一层的引用
     */
    protected NeuralLayer getNextLayer(){
        return nextLayer;
    }
    
    /**
     * 设置先前神经层
     * @param layer 设置对前一层的引用
     */
    protected void setPreviousLayer(NeuralLayer layer){
        previousLayer=layer;
    }
    
    /**
     * 设置后继神经层
     * @param layer 设置对下一层的引用 
     */
    protected void setNextLayer(NeuralLayer layer){
        nextLayer=layer;
    }
    
    /**
     * 初始化
     * 通过设置该层的所有神经元的激活函数
     * 然后初始化每个神经元来初始化神经层。
     * 
     * @see NeuralLayer
     */
    protected void init(){
        if(numberOfNeuronsInLayer>=0){
            for(int i=0;i<numberOfNeuronsInLayer;i++){
                try{
                    neuron.get(i).setActivationFunction(activationFnc);
                    neuron.get(i).init();
                }
                catch(IndexOutOfBoundsException iobe){
                    neuron.add(new Neuron(numberOfInputs,activationFnc));
                    neuron.get(i).init();
                }
            }
        }
    }
    
    /**
     * 设置输入
     * 将实际值数组设置为该层的输入
     * @param inputs 要输入到该层输入的实际值数组
     * @see NeuralInput
     */
    protected void setInputs(ArrayList<Double> inputs){
        this.numberOfInputs=inputs.size();
        this.input=inputs;
    }
    
    /**
     * 计算
     * 计算该层的所有神经元的输出。
     */
    protected void calc(){
        if(input!=null && neuron!=null){
            for(int i=0;i<numberOfNeuronsInLayer;i++){
                neuron.get(i).setInputs(this.input);
                neuron.get(i).calc();
                try{
                    output.set(i,neuron.get(i).getOutput());
                }
                catch(IndexOutOfBoundsException iobe){
                    output.add(neuron.get(i).getOutput());
                }
            }
        }
    }
    
    /**
     * 得到输出
     * @return 返回该层输出的数组 
     */
    protected ArrayList<Double> getOutputs(){
        return output;
    }
    
    /**
     * 得到神经元
     * @param i 神经元的Java索引
     * @return 返回层中的第i个Java位置的神经元
     */
    public Neuron getNeuron(int i){
        return neuron.get(i);
    }
    
    /**
     * 设置神经元
     * 在该层的输入处设置已经创建的神经元
     * @param i 将放置神经元的Java索引
     * @param _neuron 要插入或放置在层中的神经元
     */
    protected void setNeuron(int i, Neuron _neuron){
        try{
            this.neuron.set(i, _neuron);
        }
        catch(IndexOutOfBoundsException iobe){
            this.neuron.add(_neuron);
        }
    }
    public Double getWeight(int i,int j){
        return this.neuron.get(j).getWeight(i);
    }
}
