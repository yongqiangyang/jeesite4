package com.jeesite.modules.svm.base;

import java.io.IOException;

/**
 * @author LeoWang
 * @description svm
 * @data 2018/1/1
 */
public class svm {
    public  static void main(String[] args) throws IOException {
        String[] trainArgs = {"D:\\jeesite-demo\\src\\main\\java\\com\\jeesite\\modules\\svm\\base\\svmdata.txt"};//训练目标文件
        String modelFile = svm_train.main(trainArgs);
        /**
         * @Param
         * testArgs[0]----Directory of test file
         * testArgs[1]----Directory of model file
         * testArgs[2]----Directory of result file
         */
        String[] testArgs = {"D:\\jeesite-demo\\src\\main\\java\\com\\jeesite\\modules\\svm\\base\\datatest.txt", "D:\\jeesite-demo\\src\\main\\java\\com\\jeesite\\modules\\svm\\base\\svmdata.txt.model", "D:\\jeesite-demo\\src\\main\\java\\com\\jeesite\\modules\\svm\\base\\result.txt"};//directory of test file, model file, result file
        Double accuracy = svm_predict.main(testArgs);
    }
}
