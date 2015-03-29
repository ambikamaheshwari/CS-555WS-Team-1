package cs555ws.project;


import java.io.*;
public class maingedcom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 
		Datastructure ds = new Datastructure();
		ds.readFile("final.ged");
		ds.indicount("final.ged");
		ds.famcount("final.ged");
		ds.birthdatecount("final.ged");
		ds.deathcount("final.ged");
		ds.birthcount("final.ged");
		ds.birthBeforeDeath("final.ged");
		ds.DivorceBeforeMarriage("final.ged");
		ds.DeathBeforeMarriage("final.ged");
		ds.MarriageBeforeBirth("final.ged");
		ds.divorceAfterDeath("final.ged");
		ds.divorceBeforeBirth("final.ged");
		ds.MarriageofSamePerson("final.ged");
		ds.weddingcount("final.ged");
		ds.divorcecount("final.ged");
		ds.CoupleSameBday("final.ged");
		ds.CoupleSameDday("final.ged");
		ds.IndividualSameBday("final.ged");
		ds.IndividualSameDday("final.ged");
	}
}
