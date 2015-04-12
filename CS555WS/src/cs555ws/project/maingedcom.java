package cs555ws.project;


import java.io.*;
public class maingedcom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 
		Datastructure ds = new Datastructure();
		ds.readFile("Project08.ged");
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
		ds.CoupleSameBday("final1.ged");
		ds.CoupleSameDday("final1.ged");
		ds.IndividualSameBday("final1.ged");
		ds.weddingcount("final1.ged");
		ds.divorcecount("final1.ged");
		ds.IndividualSameDday("final1.ged");
		ds.birthdateleap("final1.ged");
		ds.deathdateleap("final1.ged");
		ds.printAgeChild("Project08.ged");//AM
		ds.printAgeOld("Project08.ged");//AM
		ds.widowcount("Project08.ged");//PT
		ds.widowercount("Project08.ged");//PT
		ds.printAgeTeen("Project08.ged");//HB
		ds.printAgeAdult("Project08.ged");//HB
		ds.marriagedateleap("Project08.ged");//SA
		ds.divorcedateleap("Project08.ged");//SA
	}
}
