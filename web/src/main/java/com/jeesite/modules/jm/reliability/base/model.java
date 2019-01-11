package com.jeesite.modules.jm.reliability.base;

import java.util.ArrayList;
import java.util.List;

public abstract class model {
	protected int dataNum=136;			//���������е�ʧЧ���ʱ�������������Ĭ��Ϊ136��
	protected int[] t=new int[2000];		//ʧЧ����������ݣ���1��ʼ��
    protected double fx;				//��i-1��ʧЧΪ���ĵ�i��ʧЧ������ʱ��Xi���ܶȺ���
    protected double Fx;				//��i-1��ʧЧΪ���ĵ�i��ʧЧ������ʱ��Xi�ķֲ�����
    protected String modelName;         //�ÿɿ���ģ�͵�����
    
    
    public int getNum() {
        return this.dataNum;
    }
    
    public String getName() {
    	return modelName;
    }
    
    public double GetF(int i){
        CalF(i);
        return Fx;
    }
    
    public  double Getf(int i){
        Calf(i);
        return fx;
    }
    
    //���пɿ���ģ��
    public abstract void run();
    
    //����̨��ʾģ�ͽ��
    public abstract void showResult();
    
    //����ֲ�����
    protected abstract void CalF(int i);
    
    //��������ܶȺ���
    protected abstract void Calf(int i);
    
    //���ÿɿ���ģ�͵�����
    public abstract void setname();
}
