package com.jeesite.modules.bp.edu.packt.neuralnet.math;

import java.util.Random;

/**
 * 
 * 产生随机数
 * 这个类根据种子生成双精度随机数。例如，它在权重初始化中使用。
 * 
 *
 *
 */
public class RandomNumberGenerator {
    /**
     * 用于随机数生成的种子
     */
    public static long seed=0;
    /**
     * 实际生成随机数的随机单例对象
     */
    public static Random r;
    /**
     * 生成下一个
     * 返回新随机数的静态方法
     * @return 
     */
    public static double GenerateNext(){
        if(r==null)
            r = new Random(seed);
        return r.nextDouble();
    }
    
    /** 
     * 设置种子
     * 为随机数产成器设置新种子
     * @param seed 随机数产生器的新种子
     */
    public static void setSeed(long seed){
        seed=seed;
        r.setSeed(seed);
    }
}
