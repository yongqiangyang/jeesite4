package com.jeesite.modules.jm.reliability.compareModel;

import com.jeesite.modules.jm.reliability.base.compareModel;
import com.jeesite.modules.jm.reliability.base.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Y_Dirgram extends compareModel {
	private  int s=1;				//Yͼ����С�±�
    //private K_S KS=new K_S();
    
    //���캯��
    public Y_Dirgram(model mo) {
		super(mo);	
	}
    
    @Override
    protected void getData() {
    	List<Double> Xj=new ArrayList<Double>();//�㷨�е��м����
    	//����ģ�ͣ�
		this.mo.run();
		//����һ����ֵ��ʹ�����1��ʼ����
		Xj.add(0.0);
		this.X.add(0.0);
        this.Y.add(0.0);
        
        //����Xj
        for(int j=1;j<=mo.getNum();j++)
        {
        	//�õ�ģ�͵ķֲ�����
            Double Fx=mo.GetF(j);
            //����ȡln֮����������ֵ����������һ�������ֲ�����С��0.95������
            if(Fx<0.95) {
            	Xj.add(-Math.log(1.0-Fx));
            	resultNum++;
            }
        }
        
        //���������
        double temp1=0;
        double temp2=0;
        double temp3;
        for(int j=s;j<=this.resultNum;j++) {
        	temp1+=Xj.get(j);
        }
        for(int k=s;k<=this.resultNum;k++) {
        	temp2=0;
        	for(int j=s;j<=k;j++) {
        		temp2+=Xj.get(j);
        	}
        	temp3=temp2/temp1;
        	X.add(temp3);
        }
        
        //����������
        double ly=0;
        double Height=1.0/(resultNum+1);
        for (int j = 1; j <= resultNum; j++) {
            ly+=Height;
            this.Y.add(ly);
        }

        Collections.sort(this.X);
    }

    //���бȽ�ģ��
    @Override
    public void run() {
    	this.setName();
        this.getData();
        System.out.println(mo.getName()+"ģ�͵�YͼK-S���飺");
        //KS.run(this.X, this.Y);
        
    }

    //չʾ���
    @Override
	public void showResult() {
		run();
		System.out.println(this.X);
	}

    //��������
	@Override
	protected void setName() {
		// TODO Auto-generated method stub
		this.name="Y_Diragram";
	}}
