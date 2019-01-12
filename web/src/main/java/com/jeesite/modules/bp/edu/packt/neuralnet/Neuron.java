package com.jeesite.modules.bp.edu.packt.neuralnet;


import com.jeesite.modules.bp.edu.packt.neuralnet.math.IActivationFunction;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.RandomNumberGenerator;

import java.util.ArrayList;


/**
 *
 * 人工神经元
 * 
 *
 * 
 */
public class Neuron {
    
    /**
     * 此神经元的权重
     */
    protected ArrayList<Double> weight;
    /**
     * 神经元的输入
     */
    private ArrayList<Double> input;
    /**
     *  这个神经元的输出，由激活函数产生。
     */
    private Double output;
    /**
     *  传递给激活函数的值
     */
    private Double outputBeforeActivation;
    
    /**
     * 输入数量。如果是0，则表示神经元尚未初始化。
     */
    private int numberOfInputs = 0;
    
    /**
     * 神经元的精度，在这里它总是等于1
     */
    protected Double bias = 1.0;
    /**
     * 这个神经元的激活功能
     */
    private IActivationFunction activationFunction;
    
    
    /**
     * 神经元构造函数
     */
    public Neuron(){
        
    }
    /**
     * 神经元的构造函数
     * @param numberofinputs 输入数量 
     */
    public Neuron(int numberofinputs){
        numberOfInputs=numberofinputs;
        weight=new ArrayList<>(numberofinputs+1);
        input=new ArrayList<>(numberofinputs);
    }
    /**
     * 神经元的构造函数
     * @param numberofinputs 输入数量
     * @param iaf 激活函数
     */
    public Neuron(int numberofinputs,IActivationFunction iaf){
        numberOfInputs=numberofinputs;
        weight=new ArrayList<>(numberofinputs+1);
        input=new ArrayList<>(numberofinputs);
        activationFunction=iaf;
    }
    
    /**
     * 初始化
     * 该方法通过随机设置神经元的权重来初始化神经元。
     */
    public void init(){
        if(numberOfInputs>0){
            for(int i=0;i<=numberOfInputs;i++){
                double newWeight = RandomNumberGenerator.GenerateNext();
                try{
                    this.weight.set(i, newWeight);
                }
                catch(IndexOutOfBoundsException iobe){
                    this.weight.add(newWeight);
                }
            }
        }
    }
    
    /**
     * 设置输入
     * 将双精度值的向量设置为神经元输入 
     * @param values 在神经元输入处应用的值向量
     */
    public void setInputs(double [] values){
        if(values.length==numberOfInputs){
            for(int i=0;i<numberOfInputs;i++){
                try{
                    input.set(i, values[i]);
                }
                catch(IndexOutOfBoundsException iobe){
                    input.add(values[i]);
                }
            }
        }
    }
    
    /**
     * 设置输入
     * 将值数组设置为神经元的输入
     * @param values 
     */
    public void setInputs(ArrayList<Double> values){
        if(values.size()==numberOfInputs){
            input=values;
        }
    }
    
    /**
     * 得到数组输入
     * @return 返回ArrayList中神经元的输入 
     */
    public ArrayList<Double> getArrayInputs(){
        return input;
    }
    
    /** 
     * 得到输入
     * @return 在向量中返回神经元的输入
     */
    public double[] getInputs(){
        double[] inputs = new double[numberOfInputs];
        for (int i=0;i<numberOfInputs;i++){
            inputs[i]=this.input.get(i);
        }
        return inputs;
    }
    
    /**
     * 设置输入
     * 在神经元输入的第i个位置设置实际值 
     * @param i Java的索引
     * @param value 要在输入中设置的值
     */
    public void setInput(int i,double value){
        if(i>=0 && i<numberOfInputs){
            try{
                input.set(i, value);
            }
            catch(IndexOutOfBoundsException iobe){
                input.add(value);
            }
        }
    }
    
    /**
     * 得到输入
     * @param i 输入时的Java位置
     * @return 返回第i个Java输入
     */
    public double getInput(int i){
        return input.get(i);
    }
    
    /**
     * 得到权重
     * @return 以向量的形式返回神经元的权重
     */
    public double[] getWeights(){
        double[] weights = new double[numberOfInputs+1];
        for(int i=0;i<=numberOfInputs;i++){
            weights[i]=weight.get(i);
        }
        return weights;
    }
    
    public Double getWeight(int i){
        return weight.get(i);
    }
    
    public Double getBias(){
        return weight.get(numberOfInputs);
    }
    
    /**
     * 获取数组权重
     * @return 以数组列表的形式返回神经元的权重
     */
    public ArrayList<Double> getArrayWeights(){
        return weight;
    }
    
    /**
     * 更新权重
     * 用于在学习期间更新权重的方法
     * @param i 权重的Java位置
     * @param value 要根据权重更新的值
     */
    public void updateWeight(int i, double value){
        if(i>=0 && i<=numberOfInputs){
            weight.set(i, value);
        }
    }
    
    /**
     * 得到输入的数量
     * @return 返回输入的数量
     */
    public int getNumberOfInputs(){
        return this.numberOfInputs;
    }
    
    /**
     * 设置权重
     * 
     * @param i 第i个Java的位置
     * @param value 设置第i个Java位置的权重
     * @throws NeuralException 
     */
    public void setWeight(int i,double value) throws NeuralException{
        if(i>=0 && i<numberOfInputs){
            this.weight.set(i, value);
        }
        else{
            throw new NeuralException("Invalid weight index");
        }
    }
    
    /**
     * 获得输出
     * @return 返回神经元的输出
     */
    public double getOutput(){
        return output;
    }
    
    /**
     * 计算
     * 计算神经元的输出
     */
    public void calc(){
        outputBeforeActivation=0.0;
        if(numberOfInputs>0){
            if(input!=null && weight!=null){
                for(int i=0;i<=numberOfInputs;i++){
                    outputBeforeActivation+=(i==numberOfInputs?bias:input.get(i))*weight.get(i);
                }
            }
        }
        output=activationFunction.calc(outputBeforeActivation);
    }
    
    public Double calc(ArrayList<Double> _input){
        Double _outputBeforeActivation=0.0;
        if(numberOfInputs>0){
            if(weight!=null){
                for(int i=0;i<=numberOfInputs;i++){
                    _outputBeforeActivation+=(i==numberOfInputs?bias:_input.get(i))*weight.get(i);
                }
            }
        }
        return activationFunction.calc(_outputBeforeActivation);
    }
    
    public Double calc(Double[] _input){
        Double _outputBeforeActivation=0.0;
        if(numberOfInputs>0){
            if(weight!=null){
                for(int i=0;i<=numberOfInputs;i++){
                    _outputBeforeActivation+=(i==numberOfInputs?bias:_input[i])*weight.get(i);
                }
            }
        }
        return activationFunction.calc(_outputBeforeActivation);
    }
    
    public Double derivative(Double[] _input){
        Double _outputBeforeActivation=0.0;
        if(numberOfInputs>0){
            if(weight!=null){
                for(int i=0;i<=numberOfInputs;i++){
                    _outputBeforeActivation+=(i==numberOfInputs?bias:_input[i])*weight.get(i);
                }
            }
        }
        return activationFunction.derivative(_outputBeforeActivation);
    }
    
    public ArrayList<Double> calcBatch(ArrayList<ArrayList<Double>> _input){
        ArrayList<Double> result = new ArrayList<>();
        for(int i=0;i<_input.size();i++){
            result.add(0.0);
            Double _outputBeforeActivation=0.0;
            for(int j=0;j<numberOfInputs;j++){
                _outputBeforeActivation+=(j==numberOfInputs?bias:_input.get(i).get(j))*weight.get(j);
            }
            result.set(i,activationFunction.calc(_outputBeforeActivation));
        }
        return result;
    }
    
    public ArrayList<Double> derivativeBatch(ArrayList<ArrayList<Double>> _input){
        ArrayList<Double> result = new ArrayList<>();
        for(int i=0;i<_input.size();i++){
            result.add(0.0);
            Double _outputBeforeActivation=0.0;
            for(int j=0;j<numberOfInputs;j++){
                _outputBeforeActivation+=(j==numberOfInputs?bias:_input.get(i).get(j))*weight.get(j);
            }
            result.set(i,activationFunction.derivative(_outputBeforeActivation));
        }
        return result;
    }
    
    /**
     * 设置激活函数
     * 设置此神经元的激活函数
     * @param iaf 激活函数
     */
    public void setActivationFunction(IActivationFunction iaf){
        this.activationFunction=iaf;
    }
    
    /**
     * 返回传递给激活函数的值
     * @return 返回输入乘以权重的加权和。
     */
    public double getOutputBeforeActivation(){
        return outputBeforeActivation;
    }

    public void deactivateBias(){
        this.bias=0.0;
    }
    
    public void activateBias(){
        this.bias=1.0;
    }
    
}
