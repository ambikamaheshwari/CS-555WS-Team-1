package Gedcom;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import Gedcom.Gedcom;

public class GedcomTest extends TestCase {


	//@Test
	public void test_birthdate() {
		Gedcom s=new Gedcom();
		assert((Gedcom.deathdate>Gedcom.birthdate));
		System.out.println("Birth date accepted"); 	}
	
	

}
