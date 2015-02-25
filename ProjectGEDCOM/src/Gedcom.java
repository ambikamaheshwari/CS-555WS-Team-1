package Gedcom;

import java.io.*;

import org.gedcom4j.parser.GedcomParserException;

public class Gedcom {

	public static String line = null;
	public static int indi;

	// Print total number of individual in the family
	public static void individual(BufferedReader br) throws IOException {

		BufferedReader bri = br;
		while ((line = bri.readLine()) != null) {
			if (line.contains("@I") && (line.contains("INDI")))
				indi = indi + 1;
		}
		System.out.println("Total Individual in the family are " + indi);

	} // [Sprint1_US01_AM]

	public static void birthBeforeDeath(BufferedReader br1) throws IOException {
		BufferedReader brbd = br1;
		String line = brbd.readLine();
		String line2 = brbd.readLine();
		while (line != null) {

			for (String next; line != null; line = next) {
				next = brbd.readLine();

				if (line.contains("DATE") && next.contains("DEAT")
						&& next.contains("Y")) {
					line2 = line;
					String deat = brbd.readLine();

					if (next.contains("DEAT") && deat.contains("DATE")) {

						String mon = null;
						String month = line2.trim().substring(10, 13);

						switch (month) {
						case "JAN":
							mon = "01";
							break;
						case "FEB":
							mon = "02";
							break;
						case "MAR":
							mon = "03";
							break;
						case "APR":
							mon = "04";
							break;
						case "MAY":
							mon = "05";
							break;
						case "JUN":
							mon = "06";
							break;
						case "JUL":
							mon = "07";
							break;
						case "AUG":
							mon = "08";
							break;
						case "SEP":
							mon = "09";
							break;
						case "OCT":
							mon = "10";
							break;
						case "NOV":
							mon = "11";
							break;
						case "DEC":
							mon = "12";
							break;
						default:
							break;

						}
						int birthdate = Integer.parseInt(line2.trim()
								.substring(14, 18)
								+ mon
								+ line2.trim().substring(7, 9));
						month = deat.trim().substring(10, 13);
						int deathdate = Integer.parseInt(deat.trim().substring(
								14, 18)
								+ mon + deat.trim().substring(7, 9));
						if (deathdate < birthdate) {

							System.out
									.println("Death cannot happen before death, Please check the data");
						}

					}
				}
			}
		}
	} // [Sprint1_US02_AM]

	public static void main(String[] args) throws IOException,
			GedcomParserException {
		// TODO Auto-generated method stub

		FileReader fin = new FileReader(
				"C:/Users/Ambika/Documents/GitHub/CS-555WS-Team-1/Project 3/Family.ged");
		FileReader fin1 = new FileReader(
				"C:/Users/Ambika/Documents/GitHub/CS-555WS-Team-1/Project 3/Family.ged");
		// PrintWriter output = new PrintWriter(new
		// FileWriter("C:/Users/Ambika/Documents/GitHub/CS-555WS-Team-1/Project 3/p03output.txt"));
		BufferedReader br1 = new BufferedReader(fin1);
		BufferedReader br = new BufferedReader(fin);

		individual(br);
		birthBeforeDeath(br1);

		//System.exit(0);

	}

}

