package cs555ws.project;


import java.io.*;
public class maingedcom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 
		Datastructure ds = new Datastructure();
		ds.readFile("Divorcebeforedeath.ged");
		ds.indicount("Divorcebeforedeath.ged");
		ds.birthBeforeDeath("Divorcebeforedeath.ged");
		ds.divorceAfterDeath("Divorcebeforedeath.ged");
		ds.divorceBeforeBirth("Divorcebeforedeath.ged");
		ds.DeathBeforeMarriage("Divorcebeforedeath.ged");
		//ds.DeathBeforeMarriage("C:/Users/Ambika/Documents/GitHub/CS-555WS-Team-1/TEAM-1-Family-2-23-Feb-2015.ged");
	
		 
	}
}
