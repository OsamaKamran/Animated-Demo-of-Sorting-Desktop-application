package firtone;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import java.awt.BorderLayout;
import javax.swing.SwingWorker;

public class Sorting extends ApplicationFrame{
	
	static int array[]=new int[50];
	public static int d[]=new int[50];
	static XYSeries rand;
	
	public static int[] random(int a, int b){
		
		Random r = new Random();
		int size = b-a+1;
		array= new int[size];
 
		for(int i=0; i< size; i++){
			array[i] = a+i;
		}
		for (int i=0; i<array.length; i++) {
		    int randomPosition = r.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
		return array;
	}
	public Sorting( String applicationTitle, String chartTitle ) {
	      super(applicationTitle);
	      
	      JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle , "" , "" , createDataset() , PlotOrientation.VERTICAL, true , true , false);

	      final String q="QuickSort";
	      final String b="BubbleSort";
	      final String i="InsertionSort";
	      final String s="SelectionSort";
	      
	      Button qsort =new Button(q);
	      Button bsort =new Button(b);
	      Button isort =new Button(i);
	      Button ssort =new Button(s);
	      Button next=new Button("Next");
	      
	      qsort.setPreferredSize(new Dimension(120,50));
	      bsort.setPreferredSize(new Dimension(120,50));
	      isort.setPreferredSize(new Dimension(120,50));
	      ssort.setPreferredSize(new Dimension(120,50));
	      next.setPreferredSize(new Dimension(120,50));
	      
	      qsort.setBackground(new Color(100,125,250));
	      bsort.setBackground(new Color(100,125,250));
	      isort.setBackground(new Color(100,125,250));
	      ssort.setBackground(new Color(100,125,250));
	      next.setBackground(new Color(100,125,250));

	      qsort.addMouseListener(new MouseAdapter() {
	           
	            public void mouseClicked(MouseEvent e) {
	             
	            	for(int i=0; i < 50; i++){
	            		
	            		d[i]=array[i];
	            	}
	            	nextframe f=new nextframe(q);
	            	quickSort(d,0,49);
	            	f.run(q);
	            	
	         
	      }
	   });
	      bsort.addMouseListener(new MouseAdapter() {
	           
	            public void mouseClicked(MouseEvent e) {
	             
	            	for(int i=0; i < 50; i++){
	            		
	            		d[i]=array[i];
	            	}
	            	nextframe f=new nextframe(b);
	            	bubbleSort(d,50);
	            	f.run(b);
	      }
	   });
	      isort.addMouseListener(new MouseAdapter() {
	           
	            public void mouseClicked(MouseEvent e) {
	             
	            	for(int j=0; j < 50; j++){
	            		
	            		d[j]=array[j];
	            	}
	            	nextframe f=new nextframe(i);
	            	insertionSort(d,50);
	            	f.run(i);
	      }
	   });
	      ssort.addMouseListener(new MouseAdapter() {
	           
	            public void mouseClicked(MouseEvent e) {
	             
	            	for(int i=0; i < 50; i++){
	            		
	            		d[i]=array[i];
	            	}
	            	nextframe f=new nextframe(s);
	            	selectionSort(d,50);
	            	f.run(s);
	      }
	   });
	      
	      
	      Panel main=new Panel(new BorderLayout());
	      ChartPanel chartPanel = new ChartPanel(xylineChart);
	      Panel btmp=new Panel(new FlowLayout(FlowLayout.CENTER));
	   
	      btmp.add(qsort);
	      btmp.add(bsort);
	      btmp.add(isort);
	      btmp.add(ssort);
	         
	      NumberAxis xAxis = new NumberAxis();
	      xAxis.setTickUnit(new NumberTickUnit(1)); 
	      XYPlot plott = (XYPlot) xylineChart.getPlot();
	      plott.setDomainAxis(xAxis);
	      NumberAxis rangeAxis = new NumberAxis();
	      rangeAxis.setTickUnit(new NumberTickUnit(1));
	      plott.setRangeAxis(rangeAxis);
	      plott.setDomainGridlinesVisible(true);
	      plott.setRangeGridlinesVisible(true);
	      chartPanel.setPreferredSize( new Dimension( 800 , 600 ) );
	       plott = xylineChart.getXYPlot( );
	      
	      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
	      renderer.setSeriesPaint( 0 , Color.black );
	      
	      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
	      plott.setRenderer( renderer ); 
	      main.add(chartPanel,BorderLayout.NORTH);
	      main.add(btmp,BorderLayout.SOUTH);
	      setContentPane( main );  
	   }
	   public static XYDataset createDataset( ) {
	      rand = new XYSeries( "Random" );          
	      int c=0;
	      for(int j=0;j<50;j++){
	      rand.add( c , array[j] );
	      c++;
	      }                    
	      
	      XYSeriesCollection dataset = new XYSeriesCollection( );          
	      dataset.addSeries( rand );          
	
	      return dataset;
	   }
	   public void bubbleSort(int list[], int length)
	   {
		   int temp;
		   int iteration=1;
		   int index;
		   while (iteration < 50)
		   {
			   for (index = 0; index < 50 - iteration; index++)
			   if (d[index] > d[index + 1])
			   {
				   temp = d[index];
				   d[index] = d[index + 1];
				   d[index + 1] = temp;
				   //CyclicBarrier cb = new CyclicBarrier(1, new nextframe.biabia() );
				   //new nextframe.biabia();
			   }
			   iteration++;
		   }
	   }
	   public void selectionSort(int list[], int length)
	   {
		   int index;
		   int smallestIndex;
		   int location;
		   int temp;
		   for (index = 0; index < length - 1; index++)
		   {
			   
			   smallestIndex = index;
			   for (location = index + 1; location < length; location++)
				   if (list[location] < list[smallestIndex])
					   smallestIndex = location;
			  
			   temp = list[smallestIndex];
			   list[smallestIndex] = list[index];
			   list[index] = temp;
		   }
	   }
	   public void insertionSort(int list[], int listLength){
		   
		   int firstOutOfOrder, location;
		   int temp;
		   for (firstOutOfOrder = 1; firstOutOfOrder < listLength; firstOutOfOrder++)
			   if (list[firstOutOfOrder] < list[firstOutOfOrder - 1])
			   {
				   temp = list[firstOutOfOrder];
				   location = firstOutOfOrder;
				   do
				   {
					   list[location] = list[location - 1];
					   location--;
				   }
				   while (location > 0 && list[location - 1] > temp);
				   list[location] = temp;
			   }
	   }
	   public void quickSort(int list[], int left, int right) {
		   
		   int i = left, j = right;
		      int tmp;
		      int pivot = list[(left + right) / 2];
		      while (i <= j) {
		            while (list[i] < pivot)
		                  i++;
		            while (list[j] > pivot)
		                  j--;
		            if (i <= j) {
		                  tmp = list[i];
		                  list[i] = list[j];
		                  list[j] = tmp;
		                  i++;
		                  j--;
		            }
		      };
		      if (left < j)
		            quickSort(list, left, j);
		      if (i < right)
		            quickSort(list, i, right);
		}
	   public static void main( String[ ] args ) {
	      
		   random(0, 49);
		   Sorting chart = new Sorting("Sorting Application",
	         "Which Type of Sort Do You Want?");
	      chart.pack( );          
	      RefineryUtilities.centerFrameOnScreen( chart );          
	      chart.setVisible( true );  
	   }	   
}
class nextframe extends JPanel{
	
	String title;
	static JFrame f1;
	
	nextframe(String title){
	
		this.title=title;
		f1=new JFrame(title);
		f1.setSize(800, 600);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void update(String name) {
		
		//CyclicBarrier cbar;
		//public static String name;
	 	//update(CyclicBarrier c, String n){
	 		
	 		//cbar = c;
	 		//name = n;
	 		//new Thread(this).start();
		   JFreeChart xylinechart = ChartFactory.createXYLineChart(name , "" , "" , Sorting.createDataset() , PlotOrientation.VERTICAL, true , true , false);
		   ChartPanel chartPanel = new ChartPanel(xylinechart);
		   
		   NumberAxis xAxis = new NumberAxis();
		   xAxis.setTickUnit(new NumberTickUnit(1)); 
		   XYPlot plott = (XYPlot) xylinechart.getPlot();
		   plott.setDomainAxis(xAxis);
           NumberAxis rangeAxis = new NumberAxis();
		   rangeAxis.setTickUnit(new NumberTickUnit(1));
		   plott.setRangeAxis(rangeAxis);
		      
		   chartPanel.setPreferredSize( new Dimension( 800 , 600 ) );
		   plott = xylinechart.getXYPlot( );
		      
		   XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
		   renderer.setSeriesPaint( 0 , Color.blue );
		   renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
		   plott.setRenderer( renderer ); 
		   f1.setContentPane( chartPanel );
		   //try {
			   //cbar.await();
			   //} catch (BrokenBarrierException exc) {
			   //System.out.println(exc);
			   //} catch (InterruptedException exc) {
			   //System.out.println(exc);
			   //}
	   }
		
	  public void run(String name){
		  
		  JFreeChart xylinechart = ChartFactory.createXYLineChart(name , "" , "" , CreateDataSet() , PlotOrientation.VERTICAL, true , true , false);
		  xylinechart.fireChartChanged();
		   ChartPanel chartPanel = new ChartPanel(xylinechart);
		   NumberAxis xAxis = new NumberAxis();
		   xAxis.setTickUnit(new NumberTickUnit(1)); 
		   XYPlot plott = (XYPlot) xylinechart.getPlot();
		   plott.setDomainAxis(xAxis);
          NumberAxis rangeAxis = new NumberAxis();
		   rangeAxis.setTickUnit(new NumberTickUnit(1));
		   plott.setRangeAxis(rangeAxis);
		   ValueAxis xaxis = plott.getDomainAxis();
	        xaxis.setAutoRange(true);
		   chartPanel.setPreferredSize( new Dimension( 800 , 600 ) );
		   plott = xylinechart.getXYPlot( );
		      
		   XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
		   renderer.setSeriesPaint( 0 , Color.blue );
		   renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
		   plott.setRenderer( renderer );
		   chartPanel.repaint();
		   chartPanel.removeAll();
		   chartPanel.revalidate();
		   f1.setContentPane( chartPanel );
	  }
	  private XYDataset CreateDataSet(){
		   
		   XYSeries rand = new XYSeries( "Sorted" );
		   rand.fireSeriesChanged();
		      int c=0;
		      for(int j=0;j<50;j++){
		      rand.add( c , Sorting.d[j] );
		      c++;
		      }
		      XYSeriesCollection dataset = new XYSeriesCollection( );          
		      dataset.addSeries( rand );
		      //xylinechart.setDataset(dataset);
		      return dataset;
	   }
}