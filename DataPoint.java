//Francisco Montalvo
//04/09/16 

import java.util.*;
import java.io.*;


public class DataPoint
{
   private String file; //name of in file
   private double[] close; // closing cost of the day
   private double[] low; // low of the day
   private double[] high;// high of the day
   private double[] open; // openning cost of the day
   private String[] date; 
   private int lineCtr; // counts lines
   private double[] periodNum; //unique id for each element in the array/ index
   
   public DataPoint(int dataIndex)
   {
   
   }
   
   public DataPoint(String fileName, int dataIndex)
   {
   file = fileName;
   close = new double[dataIndex];
   open = new double[dataIndex];
   low = new double[dataIndex];
   high = new double[dataIndex];
   date = new String[dataIndex];
   lineCtr = 0;
   periodNum = new double[dataIndex];
   }

   public void fillArrays() throws FileNotFoundException // fills all of the arrays from csv file
      {
      Scanner inFile = new Scanner(new File("/Users/Cisco/Desktop/" + file + ".csv")); 
      inFile.nextLine(); 
      while (inFile.hasNextLine()) // while there is a next line continue
         {
            String[] n = inFile.nextLine().split(","); // reads the next line and creates elements by spliting with ","
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
   
   public double getPeriodNum(int i)
   {
      return periodNum[i];
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