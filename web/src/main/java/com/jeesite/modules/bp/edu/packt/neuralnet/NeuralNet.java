package com.jeesite.modules.bp.edu.packt.neuralnet;


import com.jeesite.modules.bp.edu.packt.neuralnet.data.NeuralDataSet;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.IActivationFunction;

import java.util.ArrayList;

/**
 *
 * 神经网络
 * 这个类表示神经网络本身。它包含神经网络具有的所有定义，包括用于计算的方法。
 * 
 *
 *
 */
public class NeuralNet {
    
    /**
     * 神经网络的输入层
     */
    private InputLayer inputLayer;
    /**
     * 隐含层的神经网络阵列，可以包含0或多个
     */
    private ArrayList<HiddenLayer> hiddenLayer;
    /**
     * 神经网络输出层
     */
    private OutputLayer outputLayer;
    
    /**
     * 隐藏层数量
     */
    private int numberOfHiddenLayers;
    /**
     * 输入数量
     */
    private int numberOfInputs;
    /**
     * 输出数量
     */
    private int numberOfOutputs;
    
    /**
     * 神经输入阵列
     */
    private ArrayList<Double> input;
    /**
     * 神经输出阵列
     */
    private ArrayList<Double> output;
    
    
    private boolean activeBias=true;
    
    /**
     * 神经网络构造函数
     * 该构造函数通过初始化所有底层及其相应的神经元来初始化神经网络。
     * 
     * @param numberofinputs 这个神经网络的输入数目
     * @param numberofoutputs 这个神经网络的输出数目
     * @param numberofhiddenneurons 包含每个隐藏层中神经元数目的数组
     * @param hiddenAcFnc 包含每个隐藏层的激活函数的数组
     * @param outputAcFnc 输出层的激活函数
     */
    public NeuralNet(int numberofinputs,int numberofoutputs,
            int [] numberofhiddenneurons,IActivationFunction[] hiddenAcFnc,
            IActivationFunction outputAcFnc){
        numberOfHiddenLayers=numberofhiddenneurons.length;
        numberOfInputs=numberofinputs;
        numberOfOutputs=numberofoutputs;
        if(numberOfHiddenLayers==hiddenAcFnc.length){
            input=new ArrayList<>(numberofinputs);
            inputLayer=new InputLayer(numberofinputs);
            if(numberOfHiddenLayers>0){
                hiddenLayer=new ArrayList<>(numberOfHiddenLayers);
            }
            for(int i=0;i<numberOfHiddenLayers;i++){
                if(i==0){
                    try{
                        hiddenLayer.set(i,new HiddenLayer(numberofhiddenneurons[i],
                            hiddenAcFnc[i],
                            inputLayer.getNumberOfNeuronsInLayer()));
                    }
                    catch(IndexOutOfBoundsException iobe){
                        hiddenLayer.add(new HiddenLayer(numberofhiddenneurons[i],
                            hiddenAcFnc[i],
                            inputLayer.getNumberOfNeuronsInLayer()));
                    }
                    inputLayer.setNextLayer(hiddenLayer.get(i));
                }
                else{
                    try{
                        hiddenLayer.set(i, new HiddenLayer(numberofhiddenneurons[i],
                             hiddenAcFnc[i],hiddenLayer.get(i-1)
                            .getNumberOfNeuronsInLayer()
                            ));
                    }
                    catch(IndexOutOfBoundsException iobe){
                        hiddenLayer.add(new HiddenLayer(numberofhiddenneurons[i],
                             hiddenAcFnc[i],hiddenLayer.get(i-1)
                            .getNumberOfNeuronsInLayer()
                            ));
                    }
                    hiddenLayer.get(i-1).setNextLayer(hiddenLayer.get(i));
                }
            }
            if(numberOfHiddenLayers>0){
                outputLayer=new OutputLayer(numberofoutputs,outputAcFnc,
                        hiddenLayer.get(numberOfHiddenLayers-1)
                        .getNumberOfNeuronsInLayer() 
                        );
                hiddenLayer.get(numberOfHiddenLayers-1).setNextLayer(outputLayer);
            }
            else{
                outputLayer=new OutputLayer(numberofoutputs, outputAcFnc,
                        numberofinputs);
                inputLayer.setNextLayer(outputLayer);
            }
        }
    }
    
    public NeuralNet(int numberofinputs,int numberofoutputs,
            IActivationFunction outputAcFnc){
        this(numberofinputs,numberofoutputs,new int[0],new IActivationFunction[0],outputAcFnc);
    }
    
    /**
     * 设置输入
     * 将一组实值馈送给神经网络的输入
     * @param inputs 一组实值数组
     */
    public void setInputs(ArrayList<Double> inputs){
        if(inputs.size()==numberOfInputs){
            this.input=inputs;
        }
    }
    
    /**
     * 设置输入
     * 将双精度值的向量设置为神经网络输入
     * @param inputs 要输入神经输入的值向量
     */
    public void setInputs(double[] inputs){
        if(inputs.length==numberOfInputs){
            for(int i=0;i<numberOfInputs;i++){
                try{
                    input.set(i, inputs[i]);
                }
                catch(IndexOutOfBoundsException iobe){
                    input.add(inputs[i]);
                }
            }
        }
    }

    public ArrayList<Double> getArrayInputs(){
        return input;
    }
    
    public Double getInput(int i){
        return input.get(i);
    }
    
    public Double[] getInputs(){
        Double[] result=new Double[numberOfInputs];
        for(int i=0;i<numberOfInputs;i++){
            result[i]=input.get(i);
        }
        return result;
    }
    
    /**
     * 计算
     * 此方法计算每一层的输出并将所有值转发到下一层。
     */
    public void calc(){
        inputLayer.setInputs(input);
        inputLayer.calc();
        if(numberOfHiddenLayers>0){
            for(int i=0;i<numberOfHiddenLayers;i++){
                HiddenLayer hl = hiddenLayer.get(i);
                hl.setInputs(hl.getPreviousLayer().getOutputs());
                hl.calc();
            }
        }
        outputLayer.setInputs(outputLayer.getPreviousLayer().getOutputs());
        outputLayer.calc();
        this.output=outputLayer.getOutputs();
    }
    
    /**
     * 获取数组输出
     * @return 以数组的形式返回神经输出
     */
    public ArrayList<Double> getArrayOutputs(){
        return output;
    }
    
    /**
     * 获得输出
     * @return 以向量的形式返回神经输出
     */
    public double[] getOutputs(){
        double[] _outputs = new double[numberOfOutputs];
        for(int i=0;i<numberOfOutputs;i++){
            _outputs[i]=output.get(i);
        }
        return _outputs;
    }
    
    public double getOutput(int i){
        return output.get(i);
    }
    
    /**
     * print
     * Method to print the neural network information
     */
    public void print(){
        System.out.println("神经网络: "+this.toString());
        System.out.println("\t输入层:"+String.valueOf(this.numberOfInputs));
        System.out.println("\t输出层:"+String.valueOf(this.numberOfOutputs));
        System.out.println("\t隐藏层: "+String.valueOf(numberOfHiddenLayers));
        for(int i=0;i<numberOfHiddenLayers;i++){
            System.out.println("\t\t隐藏层 "+
                    String.valueOf(i)+": "+
                    String.valueOf(this.hiddenLayer.get(i)
                            .numberOfNeuronsInLayer)+" 神经元");
        }
        
    }
    
    public void setNeuralDataSet(NeuralDataSet _neuralDataSet){
        _neuralDataSet.neuralNet=this;
    }
    
    public int getNumberOfHiddenLayers(){
        return numberOfHiddenLayers;
    }
    
    public int getNumberOfInputs(){
        return numberOfInputs;
    }
    
    public int getNumberOfOutputs(){
        return numberOfOutputs;
    }
    
    public InputLayer getInputLayer(){
        return inputLayer;
    }
    
    public HiddenLayer getHiddenLayer(int i){
        return hiddenLayer.get(i);
    }
    
    public ArrayList<HiddenLayer> getHiddenLayers(){
        return hiddenLayer;
    }
    
    public OutputLayer getOutputLayer(){
        return outputLayer;
    }
    
    public void deactivateBias(){
        if(numberOfHiddenLayers>0){
            for(HiddenLayer hl:hiddenLayer){
                for(Neuron n:hl.getListOfNeurons()){
                    n.deactivateBias();
                }
            }
        }
        for(Neuron n:outputLayer.getListOfNeurons()){
            n.deactivateBias();
        }
    }
    
    public void activateBias(){
        for(HiddenLayer hl:hiddenLayer){
            for(Neuron n:hl.getListOfNeurons()){
                n.activateBias();
            }
        }
        for(Neuron n:outputLayer.getListOfNeurons()){
            n.activateBias();
        }
    }
    
    public boolean isBiasActive(){
        return activeBias;
    }
    
}
