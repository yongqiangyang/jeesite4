package com.jeesite.modules.bp.edu.packt.neuralnet;


import com.jeesite.modules.bp.edu.packt.neuralnet.math.IActivationFunction;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.Linear;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.RandomNumberGenerator;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.Sigmoid;

/**
 *
 * 神经网络控制台试验
 * 该类仅用于创建和测试Java中的第一个神经网络。
 *
 * 
 */
public class NeuralNetConsoleTest {
    public static void main(String[] args){
        
        RandomNumberGenerator.seed=0;
        
        int numberOfInputs=2;
        int numberOfOutputs=1;
        int[] numberOfHiddenNeurons= { 3 };
        IActivationFunction[] hiddenAcFnc = { new Sigmoid(1.0) } ;
        Linear outputAcFnc = new Linear(1.0);
        System.out.println("创建神经网络中。。。。。");
        NeuralNet nn = new NeuralNet(numberOfInputs,numberOfOutputs,
                numberOfHiddenNeurons,hiddenAcFnc,outputAcFnc);
        System.out.println("已创建神经网络！");
        nn.print();
        double [] neuralInput = { 1.5 , 0.5 };
        double [] neuralOutput;
        System.out.println("输入值：["+String.valueOf(neuralInput[0])+","+String.valueOf(neuralInput[1])+"]到神经网络");

        nn.setInputs(neuralInput);
        nn.calc();
        neuralOutput=nn.getOutputs();
        System.out.println("神经网络输出："+neuralOutput[0]);
        
    }
}
