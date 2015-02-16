package p03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import org.gedcom4j.parser.GedcomParserException;

public class p03 {
	//Puneet Trivedi
	public static void individual() throws IOException, GedcomParserException {
		FileReader fin= new FileReader("H:/STEVENS SEMESTERS/Sem 2/AGILE/Week 2/MaheshwariAmbikaP02/Family.ged");
		PrintWriter output = new PrintWriter(new FileWriter("H:/STEVENS SEMESTERS/Sem 2/AGILE/Week 3/p03output.txt"));
		BufferedReader br = new BufferedReader(fin);
		String line = null;
		String indi,family;
	
		while ((line = br.readLine()) != null) {
			if (line.contains("@I"))
				{
				indi= line.trim().substring(4, 5);
				if (indi.equals("5000"))
					output.println("Indiv greater than 5000");
				}
			
			if (line.contains("@F"))
				{
				family = line.trim().substring(4, 5);
				if (family.equals("1000"))
					output.println("Family greater than 1000");
				}
	//Sidharth Arabaghatte 	
			if (line.contains("NAME") && line.contains("1"))
			{
				
				String[] pair = line.split(" ");
				String fname1 = pair[2];
				 String lname1= pair[3];
				 
				 output.println(fname1 + lname1.replace("/", " "));
			}
							
		}
      br.close();
         
      output.close();
	}
	
	//Ambika Maheshwari
	public static void husbandwife() throws IOException, GedcomParserException {
		
		FileReader fin= new FileReader("H:/STEVENS SEMESTERS/Sem 2/AGILE/Week 2/MaheshwariAmbikaP02/Family.ged");
		PrintWriter output = new PrintWriter(new FileWriter("H:/STEVENS SEMESTERS/Sem 2/AGILE/Week 3/p03output.txt",true));
		BufferedReader br = new BufferedReader(fin);
		
		String line = null;
		String fname=null,lname=null,gender = null;
				
		while ((line = br.readLine()) != null){
		if (line.contains("NAME"))
			{
				
		String[] pair = line.split(" ");
		fname = pair[2];
		 lname= pair[3];
			}
		if (line.contains("SEX"))
		{
			String[] pair = line.split(" ");
			gender = pair[2];
			
		}
			if (line.contains("FAMS") && gender.equals("M"))
				output.println("Husband is " + fname + lname.replace("/", " "));
			else if (line.contains("FAMS") && gender.equals("F"))
				output.println("Wife is " + fname + lname.replace("/", " "));
			
		}
			br.close();
			output.close();
        
	}


	public static void main(String[] args) throws IOException, GedcomParserException  {
		// TODO Auto-generated method stub
		
		individual();
		 husbandwife(); 
	    }

}