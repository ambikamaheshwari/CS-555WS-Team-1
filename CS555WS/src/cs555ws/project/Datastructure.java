package cs555ws.project;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Datastructure {

	private List<individual> individuals = new ArrayList<individual>();
	 private List<family> families = new ArrayList<family>();
	 static int count=0; 
	 // AM_Sprint1_USget arguments
	 private String getArguments(String[] parseLine){
	  String arguments = "";
	  for(int i=2; i < parseLine.length; i++){
	   arguments = arguments + " " + parseLine[i];
	  }
	  return arguments.trim();
	 }
	 // get id
	 private String getXrefId(String xrefId){
	  return xrefId.replace("@", "");
	 }
	//read from a file 
	 public void readFile(String file) throws IOException {

	  FileInputStream fis = null;
	  BufferedReader br = null;

	  try {

	   fis = new FileInputStream(file);
	   br = new BufferedReader(new InputStreamReader(fis));
	   System.out.println();

	   String line = null;
	   individual ind = null;
	   family fam = null;
	   boolean isIndOrFam = false;
	   
	   while ((line = br.readLine()) != null) {
	    String[] parseLine = (line.split("\\s+"));
	    int level = Integer.valueOf(parseLine[0]);
	    String tag = parseLine[1];
	    String arguments = (parseLine.length > 2) ? getArguments(parseLine) : null;
	    
	    
	    if(level == 0) {
	     if("INDI".equals(arguments)){
	      ind = new individual();
	      if(ind != null){
	       individuals.add(ind);
	       ind.setIdentifier(getXrefId(tag));
	       isIndOrFam = true;
	      }
	     }
	     else if("FAM".equals(arguments)){
	      fam = new family();
	      if(fam != null){
	       families.add(fam);
	       fam.setIdentifier(getXrefId(tag));
	       isIndOrFam = true;
	      }
	     } else {
	      isIndOrFam = false;
	     }
	    }
	    
	    if(isIndOrFam){
	     if("NAME".equals(tag)){
	      ind.setName(arguments);
	      } 
	     else if("GIVN".equals(tag))
	     {
	    	 ind.setGivenName(arguments);
	     }
	     else if("SURN".equals(tag))
	     {
	    	 ind.setSurName(arguments);
	     }
	     
	     else if("HUSB".equals(tag)){
	      fam.setHusband(getXrefId(arguments));
	     } else if("WIFE".equals(tag)){
	      fam.setWife(getXrefId(arguments));
	     } 
	     else if("SEX".equals(tag)){
	      ind.setSex(arguments.charAt(0));
	     } 
	     else if("DEAT".equals(tag)){  
	      ind.setDeceased(arguments.charAt(0));
	     }
	     	          else if("FAMC".equals(tag)){
	                    String FAMC= getXrefId(arguments);
	                   
	                    ind.setFAMC(FAMC);
	           }
	     else if("CHIL".equals(tag)){
	                    String CHIL= getXrefId(arguments);
	                
	                     ind.setChil(CHIL);
	                     
	                         }
	    }
	    
	      if(level == 1) {
	    	if("BIRT".equals(tag)){
	    		line = br.readLine();
	    		  String[] nextLine = (line.split("\\s+"));
	    		  if(nextLine[1].equals("DATE")){
	    		   String BirthDate = nextLine[2]+" "+nextLine[3]+" "+nextLine[4];
	    		   int year = Calendar.getInstance().get(Calendar.YEAR);
	  		   int age = year - Integer.parseInt(nextLine[4]);
	  		   ind.setAge(age);
 				ind.setBirthDate(BirthDate);
	    		  }	
				}else if("DEAT".equals(tag)) {
					line = br.readLine();
		    		  String[] nextLine = (line.split("\\s+"));
		    		  if(nextLine[1].equals("DATE")){
		    				   String DeathDate = nextLine[2]+" "+nextLine[3]+" "+nextLine[4];
		  				ind.setDeathDate(DeathDate);
		  				}}
		  				else if ("MARR".equals(tag)){
		  					line = br.readLine();
				    		  String[] nextLine = (line.split("\\s+"));
				    		  if(nextLine[1].equals("DATE")){
				    			String weddingDate = nextLine[2]+" "+nextLine[3]+" "+nextLine[4];
				  				fam.setWeddingDate(weddingDate);
		  				}
		  		
				}
			  }	
	    	}
	   
	    
	  } catch (FileNotFoundException ex) {
	   Logger.getLogger(Datastructure.class.getName()).log(Level.SEVERE, null, ex);
	  } catch (IOException ex) {
	   Logger.getLogger(Datastructure.class.getName()).log(Level.SEVERE, null, ex);

	  } finally {
	   try {
	    br.close();
	   
	    fis.close();
	   } catch (IOException ex) {
	    Logger.getLogger(Datastructure.class.getName()).log(Level.SEVERE, null, ex);
	   }
	  }
	 }
	 
//Printing whether number of individuals.
//AM_Sprint1_US02
public void indicount(String file) {
	// TODO Auto-generated method stub
	int indi = individuals.size();
	System.out.println("Total number of Individual are : "+indi);
}

//Checking whether birth is before death
//AM_Sprint1_US01
public void birthBeforeDeath(String file){
	for(int i=0; i < individuals.size(); i++){
	 individual indObj = individuals.get(i);
	 String deathdate = indObj.getDeathDate();
	 if(deathdate != null){
	 String birthyear = indObj.getBirthDate();
	 String deathyear[] = deathdate.split(" ");
	 String year[] = birthyear.split(" ");
	 if (Integer.parseInt(deathyear[2]) < Integer.parseInt(year[2]))
	 System.out.println("Death cannot happen before birth for " + indObj.getGivenName() +" "+ indObj.getSurName());
	 
	 }
	}
}
//AM_Sprint2_US0
public void DeathBeforeMarriage(String file){
	for(int i=0; i < individuals.size(); i++){
		for(int j=0;j<families.size();j++)
		{
	 individual indObj = individuals.get(i);
	 family famObj = families.get(j);
	 
	String deathdate= indObj.getDeathDate();
	String weddingdate= famObj.getWeddingDate();
	if(deathdate != null && weddingdate!=null){
	String deathyear[] = deathdate.split(" ");
	String year[] = weddingdate.split(" ");

	if (Integer.parseInt(deathyear[2]) < Integer.parseInt(year[2]))
	System.out.println("Death cannot happen before Wedding for "+ indObj.getGivenName()+" " +indObj.getSurName());
	i+=1;
	j+=1;
	}
	 }}
}


public void MarriageBeforeBirth(String file){
	for(int i=0; i < individuals.size(); i++){
		for(int j=0;j<families.size();j++)
		{
	 individual indObj = individuals.get(i);
	 family famObj = families.get(j);
	 
	String birthdate= indObj.getBirthDate();
	String weddingdate= famObj.getWeddingDate();
	if(birthdate != null && weddingdate!=null){
		 String birthyear[] = birthdate.split(" ");
		
	String year[] = weddingdate.split(" ");

	if (Integer.parseInt(birthyear[2]) > Integer.parseInt(year[2]))
		 System.out.println("Wedding cannot happen before birth for " + indObj.getGivenName()+" "+ indObj.getSurName());
	if(i < individuals.size())
		i+=1;
	}
	 }}
}

}