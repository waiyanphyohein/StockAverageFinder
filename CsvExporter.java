package com.example.myproject;
import java.io.File;
//Names: WaiYanPhyoHein
//Date:Jun 3rd, 2016

import java.io.FileWriter;
import java.io.IOException;

public class CsvExporter 
{
	final String delimiter = ",";
    final String newLine = "\n";
    String fileHeader = "Period Number, Date,High,Low,Type,Magnitude,Length,Mag Ret,Len Ret";
    FileWriter writer;
    File file;
    
    public CsvExporter()
    {
    	
    }
    
    public void generateCSV(String fileName, DataPoint dPoint, DataSets dSets)
    { 
    	writer = null;
    	file = new File(fileName);

    	try
    	{
    		writer = new FileWriter(file);  
	        writer.append(fileHeader);  
	        writer.append(newLine);
	       
	        for(int i = 0; i < dSets.pos; i++)
	        {
	        	  writer.append(String.valueOf(dSets.getRelHLowArr(2,i)));
		          writer.append(delimiter);
		          writer.append(dPoint.getDate(dSets.getRelHLowArr(2,i)));
		          writer.append(delimiter);
		          writer.append(String.valueOf(dPoint.getHigh(dSets.getRelHLowArr(0,i))));
		          writer.append(delimiter);
		          writer.append(String.valueOf(dPoint.getLow(dSets.getRelHLowArr(1,i))));
		          writer.append(delimiter);
		          if(dSets.getRelHLowArr(3,i)==0)
						writer.append("High");
					else if(dSets.getRelHLowArr(3,i)==1)
						writer.append("Low");
		          writer.append(delimiter);
		          writer.append(String.valueOf(dSets.getMagnitude(i)));
		          writer.append(delimiter);
		          writer.append(String.valueOf(dSets.getLength(i)));
		          writer.append(delimiter);
		          writer.append(String.valueOf(dSets.getMagReten(i)));
		          writer.append(delimiter);
		          writer.append(String.valueOf(dSets.getLenReten(i)));
		          writer.append(newLine);
          
	        }
       
	        System.out.println("CSV file created successfully");
	        System.out.printf("File is located at %s%n", file.getAbsolutePath());
	    }
	    catch(Exception e)
	    {
	       System.out.println("Error in CSVFileWritter");
	       e.printStackTrace();
	    }
	    
	    finally 
	    {
	    	try 
	    	{
	           writer.flush();
	           writer.close();
	
	    	} 
	    	catch (IOException e) 
	    	{
	    		System.out.println("Error while flushing/closing fileWriter !!!");
	    		e.printStackTrace();
	    	}  
	    }  
    }
    
    
}

