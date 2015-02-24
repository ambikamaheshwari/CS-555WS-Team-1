/* A short program that: 
a. Reads each line of a GEDCOM file 
b. Prints each line 
c. Prints the level number of each line 
d. Prints the tag of each line that has a valid tag for our project, or prints "Invalid tag" 

By: Ambika Maheshwari
Course: CS - 555
Project: 02
 */
package P02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.gedcom4j.parser.GedcomParserException;

public class p02 {
	
	public static void parse() throws IOException, GedcomParserException {
		FileReader fin= new FileReader("H:/STEVENS SEMESTERS/Sem 2/AGILE/Week 2/Project/Family.ged");
		PrintWriter output = new PrintWriter(new FileWriter("H:/STEVENS SEMESTERS/Sem 2/AGILE/Week 2/Project/output.txt"));
		BufferedReader br = new BufferedReader(fin);
		String line = null;
		String lvlno,tag = null;
		while ((line = br.readLine()) != null) {
			output.println(line);
			lvlno = line.trim().substring(0, 1);
			output.println("Level Number : "+ lvlno);
			tag = line.trim().substring(2, 6);
			if (line.contains("NAME") || line.contains("SEX") || line.contains("BIRT") || line.contains("DEAT") || line.contains("FAMC") || line.contains("FAMS") || line.contains("FAM") || line.contains("MARR") || line.contains("HUSB") || line.contains("WIFE") || line.contains("CHIL") || line.contains("DIV") || line.contains("DATE") || line.contains("TRLR") || line.contains("NOTE"))
			{
			output.println(tag + " is Valid Tag");
			}
			else
			output.println(tag +" is Invalid Tag");
			output.println();	
		}
        br.close();
        output.close();
	}

	public static void main(String[] args) throws IOException, GedcomParserException  {
		// TODO Auto-generated method stub
		parse();
	    }

}
