package com.example.myproject;
//Names:WaiYanPhyoHein
//Date:Jun 3rd, 2016

	import java.io.*;
	
	public class WaveSmootherApp 
		{

		   public static void main(String[] args)
		   {
		      int dataIndex = 5040;   // number of days of data collected
		      String[] fileNames = {"apple", "cisco", "emc", "hp", "ibm", "intel", "micronTech", "microsoft", "oracle", 
		  				"qualcomm","sony", "texasInstruments", "westernDigital", "xerox", "yahoo"};
		      
		      CsvExporter writer = new CsvExporter(); 
		      DataPoint data = new DataPoint(dataIndex); // creates DataPoint class
		      for(int i = 0; i < 15; i++)
		      {
			      arrayFiller(data, fileNames[i]); // fills all the arrays with data from the csv file
			      DataSets dSets = new DataSets(dataIndex); // creates DataSets class
			      
			      dSets.fillHLowArr(data); //fills daily high and low array (from DataSets Class)                          
			      dSets.fillRelHLowArr(data); //fills the relative high and low array   
			      dSets.magAndLen(data);
			      writer.generateCSV(fileNames[i] + "1" + ".csv", data, dSets);
	      
			      dSets.fillHLowArrFromDataSets(dSets);
			      dSets.RestorePosition();
			      dSets.fillRelHLowArrB(data);
			      dSets.magAndLen(data);
			      writer.generateCSV(fileNames[i] + "2" + ".csv", data, dSets);
			      
			      dSets.fillHLowArrFromDataSets(dSets);
			      dSets.RestorePosition();
			      dSets.fillRelHLowArrB(data);
			      dSets.magAndLen(data);
			      writer.generateCSV(fileNames[i] + "3" + ".csv", data, dSets);
			      
			      dSets.fillHLowArrFromDataSets(dSets);
			      dSets.RestorePosition();
			      dSets.fillRelHLowArrB(data);
			      dSets.magAndLen(data);
			      writer.generateCSV(fileNames[i] + "4" + ".csv", data, dSets);
			      dSets.RestorePosition();
			      data.lineCtrReset();
		      }  
		   } 
		//____________________________________________________________________________________________//
		
		   public static void arrayFiller(DataPoint data, String fileName)  //fills all the arrays with data 
		                                                                  //from the csv file 
		   {  
		      try
		      {
		         data.fillArrays(fileName);
		      }
		      catch(FileNotFoundException e)
		      {
		         System.out.print(e);
		      }
		   }
		   
		//_________________________________________________________________________________________//

		 
		}

