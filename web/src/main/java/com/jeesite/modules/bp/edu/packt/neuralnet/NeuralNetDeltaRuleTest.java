package com.jeesite.modules.bp.edu.packt.neuralnet;


import com.jeesite.modules.bp.edu.packt.neuralnet.data.NeuralDataSet;
import com.jeesite.modules.bp.edu.packt.neuralnet.learn.DeltaRule;
import com.jeesite.modules.bp.edu.packt.neuralnet.learn.LearningAlgorithm;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.HyperTan;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.Linear;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.RandomNumberGenerator;


/**
 *
 *  Delta规则学习算法
 *
 */
public class NeuralNetDeltaRuleTest {
    
    public static void main(String[] args){
        
        RandomNumberGenerator.seed=0;
        
        int numberOfInputs=1;
        int numberOfOutputs=1;
        
        Linear outputAcFnc = new Linear(1.0);
        HyperTan htAcFnc = new HyperTan(0.85);
        System.out.println("创建神经网络中...");
        NeuralNet nn = new NeuralNet(numberOfInputs,numberOfOutputs,
                //outputAcFnc);
                htAcFnc);
        System.out.println("神经网络已被创建");
        nn.print();
        
        Double[][] _neuralDataSet = {
            {1.2 , fncTest(1.2)}
        ,   {0.3 , fncTest(0.3)}
        ,   {-0.5 , fncTest(-0.5)}
        ,   {-2.3 , fncTest(-2.3)}
        ,   {1.7 , fncTest(1.7)}
        ,   {-0.1 , fncTest(-0.1)}
        ,   {-2.7 , fncTest(-2.7)}
        };
        
        int[] inputColumns = {0};
        int[] outputColumns = {1};
        
        NeuralDataSet neuralDataSet = new NeuralDataSet(_neuralDataSet,inputColumns,outputColumns);
        
        System.out.println("数据集被创建");
        neuralDataSet.printInput();
        neuralDataSet.printTargetOutput();
        
        System.out.println("正在得到神经网络的第一个输出");
        
        DeltaRule deltaRule=new DeltaRule(nn,neuralDataSet
                , LearningAlgorithm.LearningMode.ONLINE);
        
        deltaRule.printTraining=true;
        deltaRule.setLearningRate(0.3);
        deltaRule.setMaxEpochs(1000);
        deltaRule.setGeneralErrorMeasurement(DeltaRule.ErrorMeasurement.SimpleError);
        deltaRule.setOverallErrorMeasurement(DeltaRule.ErrorMeasurement.MSE);
        deltaRule.setMinOverallError(0.00001);
        
        try{
            deltaRule.forward();
            neuralDataSet.printNeuralOutput();
            
            Double weight = nn.getOutputLayer().getWeight(0, 0);
            Double bias = nn.getOutputLayer().getWeight(1, 0);
            
            System.out.println("初始权重:"+String.valueOf(weight));
            System.out.println("初始阈值:"+String.valueOf(bias));
        
            System.out.println("开始训练");
            
            deltaRule.train();
            
            System.out.println("训练结束");
            if(deltaRule.getMinOverallError()>=deltaRule.getOverallGeneralError()){
                System.out.println("训练成功!");
            }
            else{
                System.out.println("训练不成功");
            }
            System.out.println("总误差:"
                        +String.valueOf(deltaRule.getOverallGeneralError()));
            System.out.println("最小总误差:"
                        +String.valueOf(deltaRule.getMinOverallError()));
            System.out.println("训练次数:"
                        +String.valueOf(deltaRule.getEpoch()));
            
            System.out.println("期望输出:");
            neuralDataSet.printTargetOutput();
            
            System.out.println("训练后的神经网络输出:");
            deltaRule.forward();
            neuralDataSet.printNeuralOutput();
            
            weight = nn.getOutputLayer().getWeight(0, 0);
            bias = nn.getOutputLayer().getWeight(1, 0);
            
            System.out.println("发现权重:"+String.valueOf(weight));
            System.out.println("发现阈值:"+String.valueOf(bias));
            
            Double[][] _testDataSet ={
                {-1.7 , fncTest(-1.7) }
              , {-1.0 , fncTest(-1.0) }
              , {0.0 , fncTest(0.0) }
              , {0.8 , fncTest(0.8) }
              , {2.0 , fncTest(2.0) }
            };
            
            NeuralDataSet testDataSet = new NeuralDataSet(_testDataSet, inputColumns, outputColumns);
            
            System.out.println("测试数据集输出：");
            deltaRule.setTestingDataSet(testDataSet);
            deltaRule.test();
            testDataSet.printNeuralOutput();
        }
        catch(NeuralException ne){
            
        }
        
        
    }
    
    public static double fncTest(double x){
        return 0.11*x;
    }
    
}
