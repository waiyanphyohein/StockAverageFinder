package edu.waiyanphyohein.lagcc;


//Name: WaiYanPhyoHein	(MainBase by Francisco)
//Date: 05/08/2016

import java.io.FileNotFoundException;
import java.util.Date;

public class Main
{
	
public static void main(String[]args) throws FileNotFoundException
   {
	ExportCSVfile writer = new ExportCSVfile (); 


	
	 // int dataIndex = 13686;   // number of days of data collected
      
      DataPoint data = new DataPoint("table",13789); // creates DataPoint class      
      data.fillArrays();

      
      DataSets dSets = new DataSets(); // creates DataSets class
                              
  
      dSets.fillHLowArr(data); //fills daily high and low array (from DataSets Class)  
      dSets.fillRelHLowArr(data); //fills the relative high and low array
      
     

     
      displayRelHLowArr(dSets, dSets.getRelHLowLen());//prints all data from relHLowArr in DataSets class
      writer.generateCSVfile(dSets,"Result11.csv",data);
      
      dSets.fillHLowArrFromDataSets(dSets,dSets.pos);
      dSets.RestorePosition();
      dSets.fillRelHLowArrDataSet(dSets);
      
      displayRelHLowArr(dSets,dSets.getRelHLowLen());
      writer.generateCSVfile(dSets, "Result22.csv",data);
     
     
    	  	dSets.fillHLowArrFromDataSets(dSets,dSets.pos);
	      	dSets.RestorePosition();
	      	dSets.fillRelHLowArrDataSet(dSets);
	      
	      	displayRelHLowArr(dSets,dSets.getRelHLowLen());
	      	writer.generateCSVfile(dSets, "Result33.csv",data);
	  
    	  	dSets.fillHLowArrFromDataSets(dSets,dSets.pos);
	      	dSets.RestorePosition();
	      	dSets.fillRelHLowArrDataSet(dSets);
	      
	      	displayRelHLowArr(dSets,dSets.getRelHLowLen());
	      	writer.generateCSVfile(dSets, "Result44.csv",data);
	
	      	for(int i = 0;i<3;i++)
	      	{
		  	dSets.fillHLowArrFromDataSets(dSets,dSets.pos);
	      	dSets.RestorePosition();
	      	dSets.fillRelHLowArrDataSet(dSets);
	      
	      	displayRelHLowArr(dSets,dSets.getRelHLowLen());
	      	writer.generateCSVfile(dSets, "Result55.csv",data);
	      	}
	  
	      int i = (int) (new Date().getTime()/1000);
	      System.out.println("Interger: "+ i);
	      System.out.println("Long:" + new Date().getTime());
	      System.out.println("Long Date: " + new Date(new Date().getTime()));
	      System.out.println("Int Date: "+ new Date(((long) i)*1000L));
	      
	  
	      
	      
   }




  
//____________________________________________________________________________________________//

   public static void arrayFiller(DataPoint data, int dataIndex)  //fills all the arrays with data 
                                                                  //from the csv file 
   {  
      try
      {
         data.fillArrays();
      }
      catch(FileNotFoundException e)
      {
         System.out.print(e);
      }
   }
   
//___________________________________________________________________________________________//

   public static void displayAllData(DataPoint data, int dataIndex)
   {
      for(int i = 0; i < dataIndex; i++)
      {
         System.out.print(data.toString(i));
      }
   } 
//__________________________________________________________________________________________//

   public static void displayRelHLowArr(DataSets dSets, int dataIndex)
   {
      for(int i = 0; i < dataIndex; i++)
      {
         System.out.println(dSets.toString(i));
      }
   }
//_________________________________________________________________________________________//
}
