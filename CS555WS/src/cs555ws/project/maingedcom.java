package cs555ws.project;


import java.io.*;
public class maingedcom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 
		Datastructure ds = new Datastructure();
		ds.readFile("final1.ged");
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
		ds.CoupleSameBday("final1.ged"); //AM
		ds.CoupleSameDday("final1.ged"); //AM
		ds.IndividualSameBday("final1.ged"); //HB
		ds.weddingcount("final1.ged"); //PT
		ds.divorcecount("final1.ged");//PT
		ds.IndividualSameDday("final1.ged");//HB
		ds.birthdateleap("final1.ged");//SA
		ds.deathdateleap("final1.ged");//SA
		
	
	}
}
