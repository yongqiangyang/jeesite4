package com.jeesite.modules.jm.reliability.view;

import javax.swing.JFrame;

import com.jeesite.modules.jm.reliability.base.compareModel;
import com.jeesite.modules.jm.reliability.base.model;
import com.jeesite.modules.jm.reliability.compareModel.U_Diragram;
import com.jeesite.modules.jm.reliability.compareModel.Y_Dirgram;
import com.jeesite.modules.jm.reliability.compareModel.PLR;
import com.jeesite.modules.jm.reliability.models.GO;
import com.jeesite.modules.jm.reliability.models.Jelinski_Moranda;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;




public class DiragramView {
	private compareModel compareModel= null;

    public DiragramView(compareModel compareModel){
        this.compareModel=compareModel;
    }
    
    //��Uͼ��Yͼ
    private  void draw_UY(){
        compareModel.run();
        XYSeries series = new XYSeries("xySeries");
        XYSeries series2 = new XYSeries("xySeriesxxyy");
        series.add(1,1);
        series.add(0,0);
        series2.add(0,0);
        series2.add(1,1);
        for (int  i = 1; i <= compareModel.getResultNum();i++) {
            double u = compareModel.getX(i);

            if(u<1){
                if(i>=2) {
                    series.add(u,compareModel.getY(i-1) );
                	series.add(u,compareModel.getY(i) );
                	series2.add(u,u);
                }
                if(i==compareModel.getResultNum())
                {
                    series.add(u,1);
                }
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series2);
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
        		compareModel.getName(), // chart title
                //compareModel.getKSResult(), // x axis label
                null, // y axis label
                null, dataset, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                false, // tooltips
                false // urls
        );
        ChartFrame frame = new ChartFrame(compareModel.getName(), chart);
        frame.pack(); // fit window to figure size
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //��PLRͼ
    public  void draw_PLR(){
        compareModel.run();
        XYSeries series = new XYSeries("xySeries");

        for (int  i = 1; i <= compareModel.getResultNum();i++) {
            double u = compareModel.getX(i);
            double y = compareModel.getY(i);
            series.add(u,y);
        }
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                compareModel.getName()+"-GO", // chart title
                "x", // x axis label
                "y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                false, // tooltips
                false // urls
        );
        ChartFrame frame = new ChartFrame(compareModel.getName()+"-GO", chart);
        frame.pack(); // fit window to figure size
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //���бȽ�ģ��
	public static  void main(String[] args) {
		model jm=new Jelinski_Moranda();
		model Go=new GO();
		jm.showResult();
		Go.showResult();
		compareModel U_JM=new U_Diragram(jm);
		compareModel Y_JM=new Y_Dirgram(jm);
		compareModel U_GO=new U_Diragram(Go);
		compareModel Y_GO=new Y_Dirgram(Go);
		compareModel PLR=new PLR(jm,Go);
		DiragramView viewU_JM=new DiragramView(U_JM);
		DiragramView viewY_JM=new DiragramView(Y_JM);
		DiragramView viewU_GO=new DiragramView(U_GO);
		DiragramView viewY_GO=new DiragramView(Y_GO);
		DiragramView viewPLR=new DiragramView(PLR);
		viewU_JM.draw_UY();
		viewY_JM.draw_UY();
		viewU_GO.draw_UY();
		viewY_GO.draw_UY();
		viewPLR.draw_PLR();
	}
	
}
