package com.jeesite.modules.bp.edu.packt.neuralnet.learn;


import com.jeesite.modules.bp.edu.packt.neuralnet.NeuralException;
import com.jeesite.modules.bp.edu.packt.neuralnet.NeuralNet;
import com.jeesite.modules.bp.edu.packt.neuralnet.data.NeuralDataSet;

public abstract class LearningAlgorithm {
    
    protected NeuralNet neuralNet;
    
    /**
     *
     * 学习模式
     *
     */
    public enum LearningMode {ONLINE,BATCH};
    
    /**
     *
     * 学习方式
     *
     */
    protected enum LearningParadigm {SUPERVISED,UNSUPERVISED};
    
    protected LearningMode learningMode;
    
    protected LearningParadigm learningParadigm;
    
    /**
     *
     * 最大迭代次数
     *
     */
    protected int MaxEpochs=100;
    
    /**
     *
     * 当前迭代次数
     *
     */
    protected int epoch=0;
    
    /**
     *
     * 最小总误差
     *
     */
    protected double MinOverallError=0.001;
    
    /**
     *
     * 学习速率
     *
     */
    protected double LearningRate=0.1;
    
    /**
     *
     * 训练数据集
     *
     */
    protected NeuralDataSet trainingDataSet;
    
    /**
     *
     * 测试数据集
     *
     */
    protected NeuralDataSet testingDataSet;
    
    /**
     *
     * 验证数据集
     *
     */
    protected NeuralDataSet validatingDataSet;
    
    public boolean printTraining=false;
    
    public abstract void train() throws NeuralException;
    
    public abstract void forward() throws NeuralException;
    
    public abstract void forward(int i) throws NeuralException;
    
    public abstract Double calcNewWeight(int layer,int input,int neuron) throws NeuralException;
    
    public abstract Double calcNewWeight(int layer,int input,int neuron,double error) throws NeuralException;
    
    public abstract void test() throws NeuralException;
    
    public abstract void test(int i) throws NeuralException;
    
    public abstract void print();
    
    public void setMaxEpochs(int _maxEpochs){
        this.MaxEpochs=_maxEpochs;
    }
    
    public int getMaxEpochs(){
        return this.MaxEpochs;
    }
    
    public int getEpoch(){
        return epoch;
    }
    
    public void setMinOverallError(Double _minOverallError){
        this.MinOverallError=_minOverallError;
    }
    
    public Double getMinOverallError(){
        return this.MinOverallError;
    }
    
    public void setLearningRate(Double _learningRate){
        this.LearningRate=_learningRate;
    }
    
    public Double getLearningDate(){
        return this.LearningRate;
    }
    
    public void setLearningMode(LearningMode _learningMode){
        this.learningMode=_learningMode;
    }
    
    public LearningMode getLearningMode(){
        return this.learningMode;
    }
    
    public void setTestingDataSet(NeuralDataSet _testingDataSet){
        this.testingDataSet=_testingDataSet;
    }
    
}
