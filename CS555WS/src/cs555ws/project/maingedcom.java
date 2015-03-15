package cs555ws.project;

import java.io.*;
public class maingedcom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 
		Datastructure ds = new Datastructure();
		ds.readFile("C:/Users/Ambika/Documents/GitHub/CS-555WS-Team-1/TEAM-1-Family-2-23-Feb-2015.ged");
		ds.indicount("C:/Users/Ambika/Documents/GitHub/CS-555WS-Team-1/TEAM-1-Family-2-23-Feb-2015.ged");
		//ds.birthBeforeDeath("C:/Users/Ambika/Documents/GitHub/CS-555WS-Team-1/TEAM-1-Family-2-23-Feb-2015.ged");
		ds.DeathBeforeMarriage("C:/Users/Ambika/Documents/GitHub/CS-555WS-Team-1/TEAM-1-Family-2-23-Feb-2015.ged");
		//ds.MarriageBeforeBirth("C:/Users/Ambika/Documents/GitHub/CS-555WS-Team-1/TEAM-1-Family-2-23-Feb-2015.ged");
		 
	}
}
