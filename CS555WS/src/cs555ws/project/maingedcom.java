package cs555ws.project;


import java.io.*;
public class maingedcom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 
		Datastructure ds = new Datastructure();
		ds.readFile("final.ged");
		ds.indicount("final.ged");
		ds.birthBeforeDeath("final.ged");
        ds.famcount("final.ged");
		ds.birthcount("final.ged");
		ds.deathcount("final.ged");
		ds.birthdatecount("final.ged");
		ds.DivorceBeforeMarriage("final.ged");
		ds.DeathBeforeMarriage("final.ged");
		ds.divorceAfterDeath("final.ged");
		ds.divorceBeforeBirth("final.ged");
		//ds.MarriageofSamePerson("final.ged");
	}
}
