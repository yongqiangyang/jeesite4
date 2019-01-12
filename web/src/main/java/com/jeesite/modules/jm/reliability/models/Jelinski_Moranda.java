package com.jeesite.modules.jm.reliability.models;

import com.jeesite.modules.jm.reliability.base.model;

import static java.lang.Math.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class Jelinski_Moranda extends model {
	private double P;				   //JM�㷨�е��м����
	private double left;               //JM�㷨��ʹ�õ��м����
	private double right;			   //JM�㷨��ʹ�õ��м����
	private double root;			   //JM�㷨��ʹ�õ��м����
	private double InherentErrorNumber;//�����еĹ��д�����
	private double EX;				   //���ȿ���ֵ
	private double EY;				   //���ȿ���ֵ
	private double constant;		   //�����еĳ���
	
	//���ļ��������������
	private  void intFileTOData(File file) throws Exception {
	    InputStreamReader reader = new InputStreamReader(new FileInputStream(file)); // ����һ������������reader
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
	    String line = "";
	    String num="";
	    line = br.readLine();
	    while (line != null) {
	    line=line.trim();//ȥ����ͷ�ͽ�β�Ŀո�
	    num=line.substring(4);//��������
	    int a = Integer.parseInt(num);
	    String xiabiao = line.substring(0,3);//�����±�
	    xiabiao=xiabiao.trim();
	    int i=Integer.parseInt(xiabiao);
	    t[i]=a;
	    line = br.readLine(); // һ�ζ���һ������
	    }      
	}
	
	//JM�㷨������P
	private void calculateP() {
		double num=0;
		for(int i=1;i<=this.dataNum;i++) {
			num+=(i-1)*(t[i]-t[i-1]);
		}
		this.P=num/this.t[this.dataNum];
	}
	
	//JM�㷨������f(N)
	private double fun(double N) {
		double num=0;
		for(int i=1;i<=this.dataNum;i++) {
			num+=1/(N-i+1);
		}
		num=num-this.dataNum/(N-this.P);
		return num;
	}
	
	//JM�㷨������һ
	private void stepOne() {
		if(this.P>(this.dataNum-1)/2) {
			this.left=this.dataNum-1;
			this.right=this.dataNum;
			stepTwo();
		}
		else {
			//��ֹ����
		}
		
	}
	
	//JM�㷨�������
	private void stepTwo() {
		double num=fun(this.right);
		double NEX=0-this.EX;
		if(num>this.EY) {
			this.left=this.right;
			this.right++;
			stepTwo();
		}
		else if(num>=NEX && num<=this.EY) {
			this.root=this.right;
			stepFive();
		}
		else {
			stepThree();
		}
	}
	
	//JM�㷨��������
	private void stepThree() {
		double num=Math.abs(this.right-this.left);
		this.root=(this.right+this.left)/2;
		if(num<this.EX) {
			stepFive();
		}
		else if(num>this.EX){
			stepFour();
		}
	}
	
	//JM�㷨��������
	private void stepFour() {
		double num=fun(this.root);
		double NEV=0-this.EY;
		if(num>this.EY) {
			this.left=this.root;
			stepThree();
		}
		else if(num>=NEV && num<=this.EY) {
			stepFive();
		}
		else {
			this.right=this.root;
			stepThree();
		}
	}
	
	//JM�㷨��������
	private void stepFive() {
		this.InherentErrorNumber=this.root;
		double num=0;
		for(int i=1;i<=this.dataNum;i++) {
			num+=(i-1)*(this.t[i]-this.t[i-1]);
		}
		this.constant=this.dataNum/(this.InherentErrorNumber*this.t[dataNum]-num);
	}
	
	//ͨ������������ȿ���ֵ����ִ��JM�㷨
	@Override
	public void run(){ 
		this.setname();
		try {
			String pathName="D:\\jeesite-demo\\src\\main\\java\\com\\jeesite\\modules\\jm\\reliability\\data\\Test.txt";
			File filename=new File(pathName);
			intFileTOData(filename);
			calculateP();
			this.EX=random();
			this.EY=random();
			stepOne();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��ʾJM�㷨�Ľ��
	@Override
	public void showResult() {
		this.run();
		System.out.println("EX="+this.EX+",EY="+this.EY+",���д�����="+this.InherentErrorNumber+",���̳���="+this.constant);
	}
	
	//����ֲ�����
	@Override
	protected void CalF(int i) {
		double temp=this.constant*(this.InherentErrorNumber-i+1);
		this.Fx=1-exp(-temp*t[i]);
	}
	
	//��������ܶȺ���
	@Override
	protected void Calf(int i) {
		double temp=this.constant*(this.InherentErrorNumber-i+1);
		this.fx=temp*exp(-temp*t[i]);
	}
	
	//���ÿɿ���ģ�͵�����
	@Override
	public void setname() {
		// TODO Auto-generated method stub
		this.modelName="JM";
	}


	public double getInherentErrorNumber() {
		return InherentErrorNumber;
	}

	public void setInherentErrorNumber(double inherentErrorNumber) {
		InherentErrorNumber = inherentErrorNumber;
	}

	public double getEX() {
		return EX;
	}

	public double getEY() {
		return EY;
	}

	public double getConstant() {
		return constant;
	}
	public int[] getT() {
		return t;
	}
	public int getDataNum() {
		return dataNum;
	}
}
