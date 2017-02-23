package com.example.myproject;
//Names: WaiYanPhyoHein
//Date:Jun 3rd, 2016


import java.util.*;
import java.io.*;
import java.io.File;


public class DataPoint
{
   private double[] close; // closing cost of the day
   private double[] low; // low of the day
   private double[] high;// high of the day
   private double[] open; // opening cost of the day
   private String[] date; //date
   private int lineCtr; // counts lines
   private int[] periodNum; //unique id for each element in the array/ index
   private Scanner inFile;
    
   public DataPoint(int dataIndex)
   {
   close = new double[dataIndex];
   open = new double[dataIndex];
   low = new double[dataIndex];
   high = new double[dataIndex];
   date = new String[dataIndex];
   lineCtr = 0;
   periodNum = new int[dataIndex];
   }

   public void fillArrays(String s) throws FileNotFoundException // fills all of the arrays from csv file
   {
		  String fileName = s + ".csv";
		  File file = new File(fileName);
	      inFile = new Scanner(file); 
	      inFile.nextLine(); 
	      while (inFile.hasNextLine()) // while there is a next line continue
	      {
	            String[] n = inFile.nextLine().split(","); // reads the next line and creates elements by splitting with ","
	            close[lineCtr] = Double.parseDouble(n[4]);
	            low[lineCtr] = Double.parseDouble(n[3]);
	            high[lineCtr] = Double.parseDouble(n[2]);
	            open[lineCtr] = Double.parseDouble(n[1]);
	            date[lineCtr] = n[0];
	            periodNum[lineCtr] = lineCtr;
	            lineCtr++;  
	       }
   }  
   
   public String getDate(int i)
   {
      return date[i];
   }
   
   public double getOpen(int i)
   {
      return open[i];
   }
   
   public double getClose(int i)
   {
      return close[i];
   }
   
   public double getLow(int i)
   {
      return low[i];
   }
   
   public double getHigh(int i)
   {
      return high[i];
   }
   
   public int getPeriodNum(int i)
   {
      return periodNum[i];
   }
   
   public void lineCtrReset()
   {
	   lineCtr = 0;
   }
      
   public String toString(int i)
   {
      String str = "Date: " + getDate(i) + "\n" +
                   "Open: " + getOpen(i) + "\n" +
                   "Close: " + getClose(i) + "\n" +
                   "Low: " + getLow(i) + "\n" +
                   "High: " + getHigh(i) +"\n\n";
      return str;
   }
}