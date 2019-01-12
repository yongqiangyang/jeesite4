/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeesite.modules.bp.edu.packt.neuralnet;


import com.jeesite.modules.bp.edu.packt.neuralnet.data.NeuralDataSet;
import com.jeesite.modules.bp.edu.packt.neuralnet.learn.Hebbian;
import com.jeesite.modules.bp.edu.packt.neuralnet.learn.LearningAlgorithm;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.RandomNumberGenerator;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.Sigmoid;


/**
 *
 * 未测试--无监督学习算法Hebbian
 */
public class NeuralNetHebbianTest {
    public static void main(String[] args){
        
        RandomNumberGenerator.seed=0;
        
        int numberOfInputs=2;
        int numberOfOutputs=1;
        
        Sigmoid outputAcFnc = new Sigmoid(1.0);
        System.out.println("Creating Neural Network...");
        NeuralNet nn = new NeuralNet(numberOfInputs,numberOfOutputs,
                outputAcFnc);
        nn.deactivateBias();
        System.out.println("Neural Network created!");
        nn.print();
        
        Double[][] _neuralDataSet = new Double[10][2];
        
        for (Double[] _neuralDataSet1 : _neuralDataSet) {
            for (int j = 0; j < _neuralDataSet1.length; j++) {
                _neuralDataSet1[j] = RandomNumberGenerator.GenerateNext();
            }
        }
        
        NeuralDataSet neuralDataSet = new NeuralDataSet(_neuralDataSet,1);
        
        System.out.println("Dataset created");
        neuralDataSet.printInput();
        
        System.out.println("Getting the first output of the neural network");
        
        Hebbian hebbian=new Hebbian(nn,neuralDataSet
                , LearningAlgorithm.LearningMode.ONLINE);
        
        hebbian.printTraining=true;
        hebbian.setLearningRate(0.3);
        hebbian.setMaxEpochs(1000);
        
        try{
            hebbian.forward();
            neuralDataSet.printNeuralOutput();
        
            System.out.println("Beginning training");
            
            hebbian.train();
            
            System.out.println("End of training");
            System.out.println("Epochs of training:"
                        +String.valueOf(hebbian.getEpoch()));
            
            System.out.println("Neural Output after training:");
            hebbian.forward();
            neuralDataSet.printNeuralOutput();
            
        }
        catch(NeuralException ne){
            
        }
        
        
    }    
    
}
