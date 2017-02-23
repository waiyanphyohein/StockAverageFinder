package edu.waiyanphyohein.lagcc;

//Name: WaiYanPhyoHein
//Date: 05/08/2016


import java.io.FileWriter;
import java.io.IOException;
public class ExportCSVfile {
	
	private final String CRLF = "\r\n";
	private String delimiter=",";
	
	
	
	public void setDelimiter(String delimiter)
	{
		this.delimiter=delimiter;
	}
//________________________________________________________//
	
	
	
//________________________________________________________//	
	public void generateCSVfile(DataSets data ,String sFilename,DataPoint dTemp)
	{
		try
		{
			
			
			FileWriter writer = new FileWriter (sFilename);
		
			writer.append("Date"+delimiter+"Open"+delimiter+"High"+delimiter+"Low"+delimiter+"Close"+delimiter+"PeriodNumber"+delimiter+"Distinguisher"+CRLF);
				for(int j = 0;j<data.pos;j++)
					{
						writer.append(data.dateStorage[(int)data.relHLowArr[2][j]]);
						writer.append(delimiter);
						
						writer.append((String.valueOf(dTemp.getOpen(data.relHLowArr[2][j]))));
						writer.append(delimiter);
						
						writer.append(String.valueOf (data.relHLowArr2[0][j]));
						writer.append(delimiter);
					
						writer.append(String.valueOf (data.relHLowArr2[1][j]));
						writer.append(delimiter);
						
						writer.append(String.valueOf(dTemp.getClose(data.relHLowArr[2][j])));
						writer.append(delimiter);
						
						writer.append(String.valueOf (data.relHLowArr2[2][j]));
						writer.append(delimiter);
						
						if(data.relHLowArr2[3][j]==1)
							{
							writer.append("High");
							}
						else if(data.relHLowArr2[3][j]==0)
							{
							writer.append("Low");
							}
						else if(data.relHLowArr2[3][j]==2)
							{
							writer.append("Both");
							}
						writer.append(delimiter);
						writer.append(CRLF);
					}
					//Add delimiter and end of the line
			
			
			writer.flush();
			writer.close();
		
		}
		catch (IOException e)
		{
			System.out.println("Error Occuried: Check your CSV file.");
			e.printStackTrace();
		}
	
	}
}
