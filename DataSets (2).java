package TermProject;
//Names: Francisco Montalvo && WaiYanPhyoHein
//Date:Jun 3rd, 2016

public class DataSets 
{
   double[][] hLowArr; //array of all daily highs and lows
   int[][] relHLowArr; // recycled array for relative highs and lows (stores period number)
   int[][] relHLowArr2; //final array for relative highs and lows  (stores period number)
   int[][] relHLowArrTemp; //temporary array 
   int dataIndex;
   int pos = 0; //used as a counter & index for final relative high & low array
   int posTemp;
   double [] magnitude; 
   double [] length; 
   double [] magReten;
   double [] lenReten;
   
   public DataSets(int d) //constructor
   {
	  dataIndex = d;
      hLowArr = new double[2][dataIndex+3]; 
      relHLowArr = new int[4][dataIndex];
   }
//______________________________________________________________________________//
  
   public void fillHLowArr(DataPoint d) //fills daily high and low array
   {
    
      for (int i = 0; i < dataIndex; i++) 
      {
         hLowArr[0][i] = d.getHigh(i); //takes in data from data point
         hLowArr[1][i] = d.getLow(i);        
      } 
      
    }
//______________________________________________________________________________//  
   
   public void fillRelHLowArr(DataPoint d)   //fills the initial 2d array with relative highs and lows
   {     
         for(int j = 1; j < dataIndex - 1; j++) //j is incremented by 1 
         {  
            if(hLowArr[0][j] > hLowArr[0][j+1] && hLowArr[0][j] > hLowArr[0][j-1]) //if j's value > than its prior and next 
            {                                                                    // then the corresponding periodNumber gets assigned to relHLowArr
               relHLowArr[0][pos] = d.getPeriodNum(j); //corresponding period number is copied to relHlowArr (highs)
               relHLowArr[1][pos] = d.getPeriodNum(j); //lows get copied
               relHLowArr[2][pos] = d.getPeriodNum(j); //periodNumber gets copied
               relHLowArr[3][pos] = 0; // 1 = low; //type/flag gets copied
               pos++;  // increment pos (the index and size of relHLowArr)
            }    
                  
            if(hLowArr[1][j] < hLowArr[1][j-1] && hLowArr[1][j] < hLowArr[1][j+1]) // same process as highs, but for lows
            {
               relHLowArr[0][pos] = d.getPeriodNum(j);
               relHLowArr[1][pos] = d.getPeriodNum(j);
               relHLowArr[2][pos] = d.getPeriodNum(j);
               relHLowArr[3][pos] = 1; 
               pos++;
            }
         }      
         
       
         while(deleteInnerBars(d) == true)
         {
        	 deleteInnerBars(d);
         }
         
      relHLowArr2 = new int[4][pos];   //pos creates a smaller array which eliminates all empty slots in the array
     
      for(int i = 0; i < pos; i++)  
      {
            relHLowArr2[0][i] = relHLowArr[0][i]; //copying to new array 
            relHLowArr2[1][i] = relHLowArr[1][i]; //copying to new array
            relHLowArr2[2][i] = relHLowArr[2][i]; //copying to new array
            relHLowArr2[3][i] = relHLowArr[3][i]; //copying to new array          
      }
         //relative high and low array created
   }
//______________________________________________________________________________//

   public void fillHLowArrFromDataSets(DataSets data) //fills daily high and low array
   {
	  relHLowArrTemp = new int[4][pos+1];
	  
      for (int i = 0; i < 1; i++) 
      {
         for (int j = 0; j < pos; j++) 
         {
            relHLowArrTemp[0][j] = (data.relHLowArr2[0][j]); //takes in data from data set
            relHLowArrTemp[1][j] = (data.relHLowArr2[1][j]);    
            relHLowArrTemp[2][j] = (data.relHLowArr2[2][j]);
            relHLowArrTemp[3][j] = (data.relHLowArr2[3][j]);
         }
      }
   }
   
 //______________________________________________________________________________// 
   public void fillRelHLowArrB(DataPoint data) //fills in array for generation of multiple reports
   											   // same process ar fillRelHLowArr() - initial fill array method
   {
       for(int j = 2; j < posTemp-2; j++) 
       {  
	          if((relHLowArrTemp[3][j] == 0 ) && data.getHigh(relHLowArrTemp[0][j]) > data.getHigh(relHLowArrTemp[0][j-2]) 
	        	 && data.getHigh(relHLowArrTemp[0][j]) > data.getHigh(relHLowArrTemp[0][j+2]))
	    
	             { // then the index/periodNumber gets assigned to relHLowArr
	             relHLowArr[0][pos] = relHLowArrTemp[2][j];   //corresponding period number is copied to relHlowArr (highs)
	             relHLowArr[1][pos] = relHLowArrTemp[2][j]; //lows
	             relHLowArr[2][pos] = relHLowArrTemp[2][j]; //periodNumber
	             relHLowArr[3][pos] = relHLowArrTemp[3][j];
	             pos++;  // increment pos (the index and size of relHLowArr)
	             }    
	          
	          if((relHLowArrTemp[3][j] == 1 ) && data.getLow(relHLowArrTemp[1][j+1]) < data.getLow(relHLowArrTemp[1][j-1]) 
	             && data.getLow(relHLowArrTemp[1][j+1]) < data.getLow(relHLowArrTemp[1][j+3])) 
	          {
	             relHLowArr[0][pos] = relHLowArrTemp[2][j];
	             relHLowArr[1][pos] = relHLowArrTemp[2][j];
	             relHLowArr[2][pos] = relHLowArrTemp[2][j];
	             relHLowArr[3][pos] = relHLowArrTemp[3][j]; 
	             pos++;
	          }
       }   

       while(deleteInnerBars(data) == true)
       {
      	 deleteInnerBars(data);
       }
    
    
    relHLowArr2 = new int[4][pos];
    
    for(int i = 0; i < pos; i++)  
    {
          relHLowArr2[0][i] = relHLowArr[0][i]; //copying to new array 
          relHLowArr2[1][i] = relHLowArr[1][i]; //copying to new array
          relHLowArr2[2][i] = relHLowArr[2][i]; //copying to new array
          relHLowArr2[3][i] = relHLowArr[3][i];           
    }
 }

//______________________________________________________________________________//
   public double[][] getHLowArr()  
   {
      return hLowArr;
   }
//______________________________________________________________________________//
   
   public int getRelHLowArr(int i, int j)
   {
      return relHLowArr2[i][j];
   }
//______________________________________________________________________________//

   public int getRelHLowLen()
   {
      return pos;
   }
   
//______________________________________________________________________________// 
   public int getTempArr(int i, int j)
   {
	   return relHLowArrTemp[i][j];
   }
   
//______________________________________________________________________________//

   public boolean deleteInnerBars(DataPoint data)
   {
	   boolean flag = false;
	   
			  for(int i = 0; i < pos; i++)
		      {
		    	 
		    	 if ((relHLowArr[3][i] == relHLowArr[3][i+1]) && (data.getHigh(relHLowArr[0][i]) >= data.getHigh(relHLowArr[0][i+1]) 
		        	  && data.getLow(relHLowArr[1][i]) <= data.getLow(relHLowArr[1][i+1]))) //if both elements are both high or both low and "i" is not engulfed 
		    		 														//they enter the loop
		         {   
		            for(int j = i; j < pos; j++)
		            {
		               relHLowArr[0][j+1] = relHLowArr[0][j+2]; 
		               relHLowArr[1][j+1] = relHLowArr[1][j+2];
		               relHLowArr[2][j+1] = relHLowArr[2][j+2];
		               relHLowArr[3][j+1] = relHLowArr[3][j+2]; 
		            }     
		            --pos; //incrementing the size of the array as a space gets filled
		         }
    	 
		    	 else if((relHLowArr[3][i] == relHLowArr[3][i+1]) && (data.getHigh(relHLowArr[0][i]) <= data.getHigh(relHLowArr[0][i+1]) 
		         	  	   && data.getLow(relHLowArr[1][i]) >= data.getLow(relHLowArr[1][i+1])))
			         {
			        	 for(int j = i; j < pos; j++)
			             {
			                relHLowArr[0][j] = relHLowArr[0][j+1]; //moving all elements down one space
			                relHLowArr[1][j] = relHLowArr[1][j+1];
			                relHLowArr[2][j] = relHLowArr[2][j+1];
			                relHLowArr[3][j] = relHLowArr[3][j+1]; 
			             }     
			             --pos; //incrementing the size of the array as a space gets filled
			         }
	    	 
		    	 else
		    	 {	

			    	 if((relHLowArr[3][i] == 0 && relHLowArr[3][i+1] == 0) && (data.getHigh(relHLowArr[0][i]) < data.getHigh(relHLowArr[0][i+1])))
			             {
			            	 for(int j = i; j < pos; j++)
			            	 {
			                 relHLowArr[0][j] = relHLowArr[0][j+1]; //moving all elements down one space
			                 relHLowArr[1][j] = relHLowArr[1][j+1];
			                 relHLowArr[2][j] = relHLowArr[2][j+1];
			                 relHLowArr[3][j] = relHLowArr[3][j+1]; 
			            	 }     
			            	 --pos;
			             }
		    	 
			    	 if((relHLowArr[3][i] == 0 && relHLowArr[3][i+1] == 0) && (data.getHigh(relHLowArr[0][i]) > data.getHigh(relHLowArr[0][i+1])))
			         {
			        	 for(int j = i; j < pos; j++)
			        	 {
			        		 relHLowArr[0][j+1] = relHLowArr[0][j+2]; //moving all elements down one space
			                 relHLowArr[1][j+1] = relHLowArr[1][j+2];
			                 relHLowArr[2][j+1] = relHLowArr[2][j+2];
			                 relHLowArr[3][j+1] = relHLowArr[3][j+2]; 
			        	 }     
			        	 --pos;
			         }
			    	 
			    	 if((relHLowArr[3][i] == 1 && relHLowArr[3][i+1] == 1) && (data.getLow(relHLowArr[0][i]) > data.getLow(relHLowArr[0][i+1])))
	                  {
	                 	 for(int j = i; j < pos; j++)
	                 	 {
	                 		 relHLowArr[0][j] = relHLowArr[0][j+1]; //moving all elements down one space
	                         relHLowArr[1][j] = relHLowArr[1][j+1];
	                         relHLowArr[2][j] = relHLowArr[2][j+1];
	                         relHLowArr[3][j] = relHLowArr[3][j+1]; 
	                 	 }    
	                 	 --pos;
	                  }  
			    	 
		            if((relHLowArr[3][i] == 1 && relHLowArr[3][i+1] == 1) && (data.getLow(relHLowArr[0][i]) < data.getLow(relHLowArr[0][i+1])))
		            {
		            	 for(int j = i; j < pos; j++)
		            	 {
		            		relHLowArr[0][j+1] = relHLowArr[0][j+2]; //moving all elements down one space
		                    relHLowArr[1][j+1] = relHLowArr[1][j+2];
		                    relHLowArr[2][j+1] = relHLowArr[2][j+2];
		                    relHLowArr[3][j+1] = relHLowArr[3][j+2]; 
		            	 }    
		            	 --pos;
		             }  
		    	 }
		    	 
		    	 if(relHLowArr[3][i] == relHLowArr[3][i+1])
		    	 {
		    		 flag = true;
		    	 }
		      }
			  return flag;
 }
   
 //______________________________________________________________________________//
   public void magAndLen(DataPoint data)
   {
	   magnitude = new double[pos+1]; 
	   length = new double[pos+1]; 
	   magReten = new double[pos+1];
	   lenReten = new double[pos+1];
	   
	   if(relHLowArr[3][0] == 0)
	   {
		   for(int i = 0; i < pos; i++)
		   {
			   magnitude[i] = Math.abs((data.getLow(relHLowArr[0][i+1])) - (data.getHigh(relHLowArr[0][i])));
			   length[i] = (relHLowArr[2][i+1]) - (relHLowArr[2][i]) + 1;
			   
		   }
	   }
	   
	   if(relHLowArr[3][0] == 1)
	   {
		   for(int i = 0; i < pos; i++)
		   {
			   magnitude[i] = Math.abs((data.getHigh(relHLowArr[0][i+1])) - (data.getLow(relHLowArr[0][i])));
			   length[i] = (relHLowArr[2][i+2]) - (relHLowArr[2][i+1]) + 1;
		   }
	   }
	   
	
	   for(int i = 0; i < pos; i++)
			   
	   {
		  magReten[i] = magnitude[i+1]/magnitude[i];
	      lenReten[i] = length[i+1]/length[i]; 
	   }
	 
   }

 //______________________________________________________________________________//
  
   public double getMagnitude(int i)
   {
	   return magnitude[i];
   }
   
 //______________________________________________________________________________//
   
   public double getLength(int i)
   {
	   return length[i];
   }
   
 //______________________________________________________________________________//
   public double getMagReten(int i)
   {
	   return magReten[i];
   }
   
 //______________________________________________________________________________//
 
   public double getLenReten(int i)
   {
	   return lenReten[i];
   }
   
 //______________________________________________________________________________//
      
   public void RestorePosition()
   {
	   posTemp = pos;
	   pos = 0;
   }   
//______________________________________________________________________________//

   public String toString(DataPoint data, int i)
   {
      String str = "High: " + data.getHigh(getRelHLowArr(0,i)) + "\n" +
    		  	   "Low: " + data.getLow(getRelHLowArr(0,i)) + "\n" +
                   "Period Number: " + relHLowArr[2][i] + "\n" +
                   "Type: " + relHLowArr[3][i] + "\n"; 
      				
      return str;
   }
   
}