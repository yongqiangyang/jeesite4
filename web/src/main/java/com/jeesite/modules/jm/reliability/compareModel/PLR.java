package com.jeesite.modules.jm.reliability.compareModel;

import com.jeesite.modules.jm.reliability.base.compareModel;
import com.jeesite.modules.jm.reliability.base.model;

import java.util.ArrayList;
import java.util.List;


public class PLR extends compareModel {
	private model mo2=null;
	private int n=10;			//��ʾn�������ܶȺ����ĳ˻�
	
	//���캯��
	public PLR(model mo,model mo2) {
		super(mo);
		this.mo2=mo2;
	}
	
	//�õ�ǰ����Ȼ����
	private double get_PLR_Data(model mo,int j) {
		double temp=1;
		for(int i=j;i<=j+this.n;i++) {
			temp*=mo.Getf(i);
		}
		return temp;
	}
	
	//����ǰ����Ȼ�����ı�ֵ
	@Override
	protected void getData() {
		List<Double> temp1=new ArrayList<Double>();
		List<Double> temp2=new ArrayList<Double>();
		temp1.add(0.0);
		temp2.add(0.0);
		X.add(0.0);
		Y.add(0.0);
		this.mo.run();
		this.mo2.run();
		for(int j=1;j<=20;j++) {
			temp1.add(get_PLR_Data(mo,j));
			temp2.add(get_PLR_Data(mo2,j));
			double temp=temp1.get(j)/temp2.get(j);
			this.Y.add(temp);
			this.X.add(j*1.0);
			this.resultNum++;
		}
	}

	//����PLR�㷨
	@Override
	public void run() {
		this.setName();
		this.getData();
	}

	//չʾ���
	@Override
	public void showResult() {
		this.run();
		System.out.println(Y);
	}

	//��������
	@Override
	protected void setName() {
		this.name="PLR";
	}

}
