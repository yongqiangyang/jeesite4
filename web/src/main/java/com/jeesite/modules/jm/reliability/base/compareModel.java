package com.jeesite.modules.jm.reliability.base;

import java.util.ArrayList;
import java.util.List;

public abstract class compareModel {
	protected String name;			//�Ƚ�ģ�͵�����
	protected model mo=null;		//ģ��
	protected int resultNum =0;    //���������
	protected List<Double> X=new  ArrayList<Double>();   //���ĺ�����
	protected List<Double> Y=new  ArrayList<Double>();   //����������
	
	//���캯��
	protected compareModel(model mo){
        this.mo=mo;
    }
	
	public String getName() {
		return this.name+" "+mo.getName();
	}
	
	public int getResultNum() {
        return resultNum;
    }
    
    public double getX(int i) {
    	return X.get(i);
    }
    
    public double getY(int i) {
    	return Y.get(i);
    }
    
    //��������
    protected abstract void getData();
    
    //���бȽ�ģ��
    public abstract void run();
    
    //չʾ�Ƚ�ģ�͵Ľ��
    public abstract void showResult();
    
    //���ñȽ�ģ�͵�����
    protected abstract void setName();
}
