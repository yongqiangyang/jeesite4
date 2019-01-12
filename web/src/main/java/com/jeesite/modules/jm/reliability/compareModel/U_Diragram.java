package com.jeesite.modules.jm.reliability.compareModel;


import com.jeesite.modules.jm.reliability.base.compareModel;
import com.jeesite.modules.jm.reliability.base.model;
import com.jeesite.modules.jm.reliability.models.Jelinski_Moranda;


import java.util.Collections;


public class U_Diragram extends compareModel {
	//private K_S KS=new K_S();
	
	//���캯��
	public U_Diragram(model mo) {
		super(mo);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void getData() {
		//����ģ�ͣ�
		this.mo.run();
		//����һ����ֵ��ʹ�����1��ʼ����
		this.X.add(0.0);
        this.Y.add(0.0);
        
        //���������
        for(int j=1;j<=mo.getNum();j++){
        	//�õ�ģ�͵ķֲ�����
            Double Fx=mo.GetF(j);
            if(Fx<0.95) {
	            this.X.add(Fx);
	            resultNum++;
            }
        }
        
        //����������
        double ly=0;
        double Height=1.0/(resultNum+1);
        for (int j = 1; j <= resultNum; j++) {
            ly+=Height;
            this.Y.add(ly);
        }

        //����
        Collections.sort(this.X);
	}

	//���бȽ�ģ��
	@Override
	public void run() {
		this.setName();
		this.getData();
		
		//ִ��K-S���飬���ڿ���̨���
		System.out.println(mo.getName()+"ģ�͵�UͼK-S���飺");
        //KS.run(this.X, this.Y);
        
	}

	//��ʾ���
	@Override
	public void showResult() {
		this.run();
		System.out.println(this.X);
	}

	//���ñȽ�ģ�͵�����
	@Override
	protected void setName() {
		// TODO Auto-generated method stub
		this.name="U_Diragram";
	}

	public static  void main(String[] args) {
		model jm=new Jelinski_Moranda();
		U_Diragram U=new U_Diragram(jm);
		U.run();
		int length=U.getResultNum();
		for(int i=0;i<length;i++){
			double X=U.getX(i);
			double Y=U.getY(i);
			System.out.println("X:"+X+"  Y:"+Y);
		}

	}

}
