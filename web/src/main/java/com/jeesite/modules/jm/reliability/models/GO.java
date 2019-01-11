package com.jeesite.modules.jm.reliability.models;

import com.jeesite.modules.jm.reliability.base.model;

import java.io.File;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class GO  extends model {
	private double Xl;                   //GO�㷨��ʹ�õ��м����
	private double Xr;					//GO�㷨��ʹ�õ��м����
	private double Xm;					//GO�㷨��ʹ�õ��м����
	private double b;					//�����еĲ���
	private double a;					//�����еĲ���
	private double mt;					//�ۼ�ʧЧ��������
	private double deviation=0.5;		//�㷨�е�������ֵ
	private double D;					//�㷨�е��������ֵ
	
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
	
	//GO�㷨������D
	private void calculateD() {
		double CUSUM=0;
		for(int i=1;i<=dataNum;i++) {
			CUSUM+=t[i];
		}
		D=CUSUM/(dataNum*t[dataNum]);
	}
	
	//GO�㷨������һ
	private void stepOne() {
		calculateD();
		if(D>0&&D<0.5) {
			Xl=(1-2*D)/2;
			Xr=1/D;
			stepTwo();
		}
		else {
			stepFive();
		}
	}
	
	//GO�㷨�������
	private void stepTwo() {
		Xm=(Xr+Xl)/2;
		if(Math.abs(Xr-Xl)<=deviation) {
			stepFour();
		}
		else {
			stepThree();
		}
	}
	
	//GO�㷨��������
	private void stepThree() {
		double f;
		f=(1-D*Xm)*Math.exp(Xm)+(D-1)*Xm-1;
		if(f>deviation) {
			Xl=Xm;
			stepTwo();
		}
		else if(f<(0-deviation)){
			Xr=Xm;
			stepTwo();
		}
	}

	//GO�㷨��������
	private void stepFour() {
		b=Xm/t[dataNum];
		double temp=0-b*t[dataNum];
		a=dataNum/(1-Math.exp(temp));
	}
	
	//GO�㷨��������
	private void stepFive() {
		System.out.println("���������޽⣡");
	}		
	
	//����ģ��
	@Override
	public void run(){
		// TODO Auto-generated method stub
		this.setname();
		try {
			String pathName="D:\\jeesite-demo\\src\\main\\java\\com\\jeesite\\modules\\jm\\reliability\\data\\Test.txt";
			File filename=new File(pathName);
			intFileTOData(filename);
			stepOne();
			double temp=0-b*t[dataNum];
			mt=a*(1-Math.exp(temp));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//չʾ���
	@Override
	public void showResult() {
		// TODO Auto-generated method stub
		this.run();
		System.out.println("a="+a+",b="+b);
		System.out.println("136��֮����ۼƴ���������������m��t��="+mt);
	}
	
	//����ֲ�����
	@Override
	protected void CalF(int i) {
		double temp=this.a*(1-exp(-this.b*t[i-1]));
        Fx= 1-exp(-temp*exp(-this.b*t[i]));
	}

	//��������ܶȺ���
	@Override
	protected void Calf(int i) {
		int x=t[i];
        double temp=(this.a*(1-exp((-this.b)*x )));
        this.fx= (pow(temp,i)/getFactorial(i))*exp(-temp);
    }
	
	//��������ܶȺ������Ӻ���
	private int getFactorial(int i)
    {
        int factorial=1;
        for (int j = 2; j <=i ; j++) {
        	factorial*=j;
        }
        return factorial;
    }
	
	//���ÿɿ���ģ�͵�����
	@Override
	public void setname() {
		// TODO Auto-generated method stub
		this.modelName="GO";
	}
	
}
