package cs555ws.project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Datastructure {

	private static List<individual> individuals = new ArrayList<individual>();
	private static List<family> families = new ArrayList<family>();
	static int count = 0;

	// AM_Sprint1_USget arguments
	private String getArguments(String[] parseLine) {
		String arguments = "";
		for (int i = 2; i < parseLine.length; i++) {
			arguments = arguments + " " + parseLine[i];
		}
		return arguments.trim();
	}

	// get id
	private String getXrefId(String xrefId) {
		return xrefId.replace("@", "");
	}

	// read from a file
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
				String arguments = (parseLine.length > 2) ? getArguments(parseLine)
						: null;

				if (level == 0) {
					if ("INDI".equals(arguments)) {
						ind = new individual();
						if (ind != null) {
							individuals.add(ind);
							ind.setIdentifier(getXrefId(tag));
							isIndOrFam = true;
						}
					} else if ("FAM".equals(arguments)) {
						fam = new family();
						if (fam != null) {
							families.add(fam);
							fam.setIdentifier(getXrefId(tag));
							isIndOrFam = true;
						}
					} else {
						isIndOrFam = false;
					}
				}

				if (isIndOrFam) {
					if ("NAME".equals(tag)) {
						ind.setName(arguments);
					} else if ("GIVN".equals(tag)) {
						ind.setGivenName(arguments);
					} else if ("SURN".equals(tag)) {
						ind.setSurName(arguments);
					}

					else if ("HUSB".equals(tag)) {
						fam.setHusband(getXrefId(arguments));
					} else if ("WIFE".equals(tag)) {
						fam.setWife(getXrefId(arguments));
					} else if ("SEX".equals(tag)) {
						ind.setSex(arguments.charAt(0));
					} else if ("DEAT".equals(tag)) {
						ind.setDeceased(arguments.charAt(0));
					} else if ("FAMC".equals(tag)) {
						String FAMC = getXrefId(arguments);

						ind.setFAMC(FAMC);
					} else if ("CHIL".equals(tag)) {
						String CHIL = getXrefId(arguments);

						ind.setChil(CHIL);

					}
				}

				if (level == 1) {
					if ("BIRT".equals(tag)) {
						line = br.readLine();
						String[] nextLine = (line.split("\\s+"));
						if (nextLine[1].equals("DATE")) {
							String BirthDate = nextLine[2] + " " + nextLine[3]
									+ " " + nextLine[4];
							int year = Calendar.getInstance()
									.get(Calendar.YEAR);
							int age = year - Integer.parseInt(nextLine[4]);
							ind.setAge(age);
							ind.setBirthDate(BirthDate);
						}
					} else if ("DEAT".equals(tag)) {
						line = br.readLine();
						String[] nextLine = (line.split("\\s+"));
						if (nextLine[1].equals("DATE")) {
							String DeathDate = nextLine[2] + " " + nextLine[3]
									+ " " + nextLine[4];
							ind.setDeathDate(DeathDate);
						}
					} else if ("MARR".equals(tag)) {
						line = br.readLine();
						String[] nextLine = (line.split("\\s+"));
						if (nextLine[1].equals("DATE")) {
							String weddingDate = nextLine[2] + " "
									+ nextLine[3] + " " + nextLine[4];
							fam.setWeddingDate(weddingDate);
						}
					} else if ("DIV".equals(tag)) {
						line = br.readLine();
						String[] nextLine = (line.split("\\s+"));
						if (nextLine[1].equals("DATE")) {
							String divorceDate = nextLine[2] + " "
									+ nextLine[3] + " " + nextLine[4];
							fam.setDivorceDate(divorceDate);
						}

					}

				}
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(Datastructure.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Datastructure.class.getName()).log(Level.SEVERE,
					null, ex);

		} finally {
			try {
				br.close();

				fis.close();
			} catch (IOException ex) {
				Logger.getLogger(Datastructure.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

	// Printing whether number of individuals.
	// AM_Sprint1_US02
	public void indicount(String file) {
		int indi = individuals.size();
		System.out.println("Total number of Individual are : " + indi);
	}

	// Checking whether birth is before death
	// AM_Sprint1_US01
	public void birthBeforeDeath(String file) {
		for (int i = 0; i < individuals.size(); i++) {
			individual indObj = individuals.get(i);
			String deathdate = indObj.getDeathDate();
			if (deathdate != null) {
				String birthyear = indObj.getBirthDate();
				String deathyear[] = deathdate.split(" ");
				String year[] = birthyear.split(" ");
				if (Integer.parseInt(deathyear[2]) < Integer.parseInt(year[2]))
					System.out
							.println("Death cannot happen before birth for "
									+ indObj.getGivenName() + " "
									+ indObj.getSurName());

			}
		}
	}

	// AM_Sprint2_US06
	public void DeathBeforeMarriage(String file) {
		for (int i = 0; i < individuals.size(); i++) {
			for (int j = 0; j < families.size(); j++) {
				individual indObj = individuals.get(i);
				family famObj = families.get(j);

				String deathdate = indObj.getDeathDate();
				String weddingdate = famObj.getWeddingDate();
				if (deathdate != null && weddingdate != null) {
					String deathyear[] = deathdate.split(" ");
					String year[] = weddingdate.split(" ");

					if (Integer.parseInt(deathyear[2]) < Integer
							.parseInt(year[2]))
						System.out
								.println("Death cannot happen before Wedding for "
										+ indObj.getGivenName()
										+ " "
										+ indObj.getSurName());
					i += 1;
					j += 1;
				}
			}
		}
	}

	// AM_Sprint2_US07
	public void MarriageBeforeBirth(String file) {
		for (int i = 0; i < individuals.size(); i++) {
			for (int j = 0; j < families.size(); j++) {
				individual indObj = individuals.get(i);
				family famObj = families.get(j);

				String birthdate = indObj.getBirthDate();
				String weddingdate = famObj.getWeddingDate();
				if (birthdate != null && weddingdate != null) {
					String birthyear[] = birthdate.split(" ");

					String year[] = weddingdate.split(" ");

					if (Integer.parseInt(birthyear[2]) > Integer
							.parseInt(year[2]))
						System.out
								.println("Wedding cannot happen before birth for "
										+ indObj.getGivenName()
										+ " "
										+ indObj.getSurName());
					if (i < individuals.size())
						i += 1;
				}
			}
		}
	}

	// HB_Sprint2_US08
	public void divorceAfterDeath(String file) {
		for (int i = 0; i < individuals.size(); i++) {
			for (int j = 0; j < families.size(); j++) {
				individual indObj = individuals.get(i);
				family famObj = families.get(j);
				String deathDate = indObj.getDeathDate();
				String divorceDate = famObj.getDivorceDate();
				if (deathDate != null && divorceDate != null) {
					String deathyear[] = deathDate.split(" ");
					String year[] = divorceDate.split(" ");
					if (Integer.parseInt(deathyear[2]) < Integer
							.parseInt(year[2]))
						System.out.println("Divorce cannot happen after death"
								+ " " + indObj.getGivenName() + " "
								+ indObj.getSurName());

				}
			}
		}
	}

	// HB_Sprint2_US09
	public void divorceBeforeBirth(String file) {
		for (int i = 0; i < individuals.size(); i++) {
			for (int j = 0; j < families.size(); j++) {
				individual indObj = individuals.get(i);
				family famObj = families.get(j);
				String birthDate = indObj.getBirthDate();
				String divorceDate = famObj.getDivorceDate();
				if (birthDate != null && divorceDate != null) {
					String birthyear[] = birthDate.split(" ");
					String year[] = divorceDate.split(" ");
					if (Integer.parseInt(birthyear[2]) > Integer
							.parseInt(year[2]))
						System.out.println("Divorce cannot happen before birth"
								+ " " + indObj.getGivenName() + " "
								+ indObj.getSurName());
					i = i + 1;
				}
			}
		}
	}

	// Printing number of families
	// PT_Sprint1_US3
	public void famcount(String file) {
		int fam = families.size();
		System.out.println("Total number of Families are :" + fam);
	}

	// Printing number of birthdate's
	// PT_Sprint1_US4
	public void birthcount(String file) {
		int birthDate = individuals.size();
		System.out.println("Total number of Birthdates are :" + birthDate);
	}

	// Printing number of deathDate's
	// PT_Sprint2_US10
	public void deathcount(String file) {
		for (int i = 0; i < families.size(); i++) {
			individual indObj = individuals.get(i);

			String deathdate = indObj.getDeathDate();
			if (deathdate != null) {

				System.out.println("The DeathDates are:" + " "
						+ indObj.getDeathDate() + ", " + indObj.getGivenName()
						+ " " + indObj.getSurName());

			}
		}
	}

	// Printing number of brithDate's
	// PT_Sprint2_US11
	public void birthdatecount(String file) {
		for (int i = 0; i < families.size(); i++) {
			individual indObj = individuals.get(i);
			String birthdate = indObj.getBirthDate();

			if (birthdate != null) {

				System.out.println("The BirthDates are:" + " "
						+ indObj.getBirthDate() + ", " + indObj.getGivenName()
						+ " " + indObj.getSurName());
			}
		}
	}

	// SA_Sprint2_US12
	public void MarriageofSamePerson(String file) {
		for (int i = 0; i < individuals.size(); i++) {
			for (int j = 0; j < families.size(); j++) {
				individual indObj = individuals.get(i);
				family famObj = families.get(j);

				String birthdate = indObj.getBirthDate();
				String weddingdate = famObj.getWeddingDate();
				if (birthdate != null && weddingdate != null) {
					String birthyear[] = birthdate.split(" ");

					String year[] = weddingdate.split(" ");

					if ((Integer.parseInt(birthyear[2]) == Integer
							.parseInt(birthyear[2]))
							&& (Integer.parseInt(year[2]) == Integer
									.parseInt(year[2])))
						System.out.println("Cannot marry same person "
								+ indObj.getGivenName() + " "
								+ indObj.getSurName());
					if (i < individuals.size())
						i += 1;
				}
			}
		}
	}

	// SA_Sprint2_US13
	public void DivorceBeforeMarriage(String file) {
		for (int i = 0; i < individuals.size(); i++) {
			for (int j = 0; j < families.size(); j++) {
				individual indObj = individuals.get(i);
				family famObj = families.get(j);

				String divorcedate = famObj.getDivorceDate();
				String weddingdate = famObj.getWeddingDate();
				if (divorcedate != null && weddingdate != null) {
					String divorceyear[] = divorcedate.split(" ");
					String year[] = weddingdate.split(" ");

					if (Integer.parseInt(divorceyear[2]) < Integer
							.parseInt(year[2]))
						System.out
								.println("Divorce cannot happen before Wedding for "
										+ indObj.getGivenName()
										+ " "
										+ indObj.getSurName());
					i += 1;
					j += 1;
				}
			}
		}
	}

	// Printing number of marriage's
	// PT_Sprint3_US18
	public void weddingcount(String file) {
		for (int i = 0; i < families.size(); i++) {
			family famObj = families.get(i);

			String weddingDate = famObj.getWeddingDate();

			if (weddingDate != null) {

				System.out.println("The Wedding Dates are:" + " "
						+ famObj.getWeddingDate() + ", " + famObj.getHusband()
						+ " " + famObj.getWife());
			}
		}
	}

	// Printing number of divorce's
	// PT_Sprint3_US19
	public void divorcecount(String file) {
		for (int i = 0; i < families.size(); i++) {
			family famObj = families.get(i);

			String divorceDate = famObj.getDivorceDate();

			if (divorceDate != null) {

				System.out.println("The Divorce Dates are:" + " "
						+ famObj.getDivorceDate() + ", " + famObj.getHusband()
						+ " " + famObj.getWife());
			}
		}
	}

	// Printing Couple having same birthdate
	// AM_Sprint03_US14
	public void CoupleSameBday(String file) {

		HashMap<Integer, String> hash = new HashMap<>();
		for (int i = 0; i < individuals.size(); i++) {

			for (int j = 0; j < families.size(); j++) {
				family famObj = families.get(j);
				individual indObj = individuals.get(i);
				String Id = indObj.getIdentifier();

				String Wife = famObj.getWife();
				String Hus = famObj.getHusband();
				String birthdate = indObj.getBirthDate();

				if (Id.equals(Hus) || Id.equals(Wife)) {
					hash.put(1, indObj.getGivenName());
					hash.put(2, birthdate);
					i++;
					indObj = individuals.get(i);
					hash.put(3, indObj.getGivenName());
					hash.put(4, indObj.getBirthDate());
					if (hash.get(2).equals(hash.get(4)))

						System.out.println(hash.get(1) + " " + " and "
								+ hash.get(3) + " have same birthdate "
								+ hash.get(2));
				}
			}

		}
	}

	// Printing Couple died on same day
	// AM_Sprint03_US15
	public void CoupleSameDday(String file) {

		HashMap<Integer, String> hash = new HashMap<>();
		for (int i = 0; i < individuals.size(); i++) {
			for (int j = 0; j < families.size(); j++) {
				family famObj = families.get(j);
				individual indObj = individuals.get(i);
				String Id = indObj.getIdentifier();
				String Wife = famObj.getWife();
				String Hus = famObj.getHusband();
				String deathdate = indObj.getDeathDate();
				if (deathdate != null) {
					if (Id.equals(Hus) || Id.equals(Wife)) {
						hash.put(1, indObj.getGivenName());
						hash.put(2, deathdate);
						i++;
						indObj = individuals.get(i);
						hash.put(3, indObj.getGivenName());
						hash.put(4, indObj.getDeathDate());
						if (hash.get(2).equals(hash.get(4)))
							System.out.println(hash.get(1) + " " + " and "
									+ hash.get(3) + " have same death date "
									+ hash.get(2));
					}
				}
			}

		}
	}

	// Printing Individual born on same day
	// HB_Sprint03_US16

	public void IndividualSameBday(String file) {

		List<String> date = new ArrayList<String>();

		for (int i = 0; i < individuals.size(); i++) {
			List<String> abc = new ArrayList<String>();
			abc.add(individuals.get(i).getBirthDate());
			abc.add(individuals.get(i).getGivenName());
			if (date != null) {
				if (!date.contains(abc.get(0))) {
					int flag = 0;
					for (int j = i + 1; j < individuals.size(); j++) {
						if (individuals.get(j).getBirthDate()
								.equals(abc.get(0))) {
							abc.add(individuals.get(j).getGivenName());
							flag = 1;
						}
					}
					if (flag == 1)
						date.add(individuals.get(i).getBirthDate());

					if (abc.size() > 2) {
						System.out.print("Individuals with date of birth "
								+ abc.get(0) + ":");
						for (int k = 1; k < abc.size(); k++)
							System.out.print(" " + abc.get(k));
						System.out.println();
					}
				}
			}
		}

	}

	// Printing Individual died on same day
	// HB_Sprint03_US19

	public void IndividualSameDday(String file) {

		List<String> date = new ArrayList<String>();

		for (int i = 0; i < individuals.size(); i++) {
			if (individuals.get(i).getDeathDate() != null) {
				List<String> abc = new ArrayList<String>();
				abc.add(individuals.get(i).getDeathDate());
				abc.add(individuals.get(i).getGivenName());
				if (!date.contains(abc.get(0))) {
					int flag = 0;
					for (int j = i + 1; j < individuals.size(); j++)
						if (individuals.get(j).getDeathDate() != null)
							if (individuals.get(j).getDeathDate()
									.equals(abc.get(0))) {
								abc.add(individuals.get(j).getGivenName());
								flag = 1;
							}

					if (flag == 1)
						date.add(individuals.get(i).getDeathDate());

					if (abc.size() > 2) {
						System.out.print("Individuals with date of death "
								+ abc.get(0) + ":");
						for (int k = 1; k < abc.size(); k++)
							System.out.print(" " + abc.get(k));
						System.out.println();
					}
				}
			}
		}
	}

	/*
	 * public void NoDeathcount(String file) { int deathcount=0; for(int i=0; i
	 * < families.size(); i++){ individual indObj = individuals.get(i); String
	 * deathdate = indObj.getDeathDate(); if(deathdate != null){
	 * deathcount=deathcount+1; } } int indi = individuals.size();
	 * deathcount=indi-deathcount;
	 * System.out.println("Number of individuls having no Death date are :"
	 * +" "+deathcount ); } public void NoBirthcount(String file) { int
	 * deathcount=0; for(int i=0; i < families.size(); i++){ individual indObj =
	 * individuals.get(i); String birthdate = indObj.getBirthDate();
	 * if(birthdate != null){ count=count+1; } } int indi = individuals.size();
	 * count=indi-count;
	 * System.out.println("Number of individuls having no Birth date are :"
	 * +" "+count ); }
	 */
	// Birth in a leap year
	// SA_Sprint03

	public void birthdateleap(String file) {
		for (int i = 0; i < families.size(); i++) {
			individual indObj = individuals.get(i);
			String birthdate = indObj.getBirthDate();

			boolean value;
			int birthd = Integer.parseInt(birthdate.substring(6, 10));
			if ((birthd % 4 == 0 && birthd % 100 != 0) || birthd % 400 == 0)
				value = true;
			else
				value = false;

			if (value) {

				System.out.println("The Birth Dates on leap year are:" + " "
						+ indObj.getBirthDate() + ", " + indObj.getGivenName()
						+ " " + indObj.getSurName());
			}
		}
	}

	// Death in a leap year
	// SA_Sprint03
	public void deathdateleap(String file) {
		for (int i = 0; i < families.size(); i++) {
			individual indObj = individuals.get(i);
			String deathdate = indObj.getDeathDate();

			int deathd = Integer.parseInt(deathdate.substring(6, 10));
			boolean value;
			if ((deathd % 4 == 0 && deathd % 100 != 0) || deathd % 400 == 0)
				value = true;
			else
				value = false;

			if (value) {

				System.out.println("The Death Dates on leap year are:" + " "
						+ indObj.getDeathDate() + ", " + indObj.getGivenName()
						+ " " + indObj.getSurName());
			}
		}
	}

	// AM_Sprint4_US23
	public void printAgeChild(String file) {
		int agecountC = 0;

		for (int i = 0; i < individuals.size(); i++) {
			individual indObj = individuals.get(i);

			if (indObj.getAge() <= 12) {
				agecountC += 1;

				System.out.println("The age of Child " + indObj.getIdentifier()
						+ " " + indObj.getGivenName() + " "
						+ indObj.getSurName() + " is " + indObj.getAge());
			}
		}
		System.out
				.println("The Total Number of Children less than 12 years are "
						+ agecountC);
		System.out.println();

	}

	// AM_Sprint4_US26
	public void printAgeOld(String file) {
		int agecountO = 0;

		for (int i = 0; i < individuals.size(); i++) {
			individual indObj = individuals.get(i);
			if (indObj.getAge() > 60 && (indObj.getAge() <= 100))// AM_Sprint4_US
			{
				agecountO += 1;
				System.out.println("The age of old " + indObj.getIdentifier()
						+ " " + indObj.getGivenName() + " "
						+ indObj.getSurName() + " is " + indObj.getAge());
			}
		}
		System.out
				.println("The Total Number of Individual greater than 60 are "
						+ agecountO);
		System.out.println();
	}

	// SA_Sprint4
	// Marriage in a leap year
	public void marriagedateleap(String file) {
		for (int i = 0; i < families.size(); i++) {

			family famObj = families.get(i);
			String marriagedate = famObj.getWeddingDate();

			int marriage = Integer.parseInt(marriagedate.substring(6, 10));
			boolean value;
			if ((marriage % 4 == 0 && marriage % 100 != 0)
					|| marriage % 400 == 0)
				value = true;
			else
				value = false;

			if (value) {

				System.out.println("The Marriage Dates on leap year are:" + " "
						+ famObj.getWeddingDate());
			}
		}
	}

	// SA_Sprint4
	// Divorce in a leap year

	public void divorcedateleap(String file) {
		for (int i = 0; i < families.size(); i++) {

			family famObj = families.get(i);
			String divorcedate = famObj.getDivorceDate();

			int divorce = Integer.parseInt(divorcedate.substring(6, 10));
			boolean value;
			if ((divorce % 4 == 0 && divorce % 100 != 0) || divorce % 400 == 0)
				value = true;
			else
				value = false;

			if (value) {

				System.out.println("The Divorce Dates on leap year are:" + " "
						+ famObj.getDivorceDate());
			}
		}
	}

	// Printing number of widows
	// PT_Sprint4_US27
	public void widowcount(String file) {
		for (int i = 0; i < families.size(); i++) {
			family famObj = families.get(i);
			individual indObj = individuals.get(i);

			String deathDate = indObj.getDeathDate();

			if (deathDate != null) {

				System.out.println("The Widows are:" + " " + famObj.getWife());
				System.out.println("Husband name who died is:" + " "
						+ famObj.getHusband() + "," + indObj.getDeathDate());
			}
		}
	}

	// Printing number of widowers
	// PT_Sprint4_US28
	public void widowercount(String file) {
		for (int i = 0; i < families.size(); i++) {
			family famObj = families.get(i);
			individual indObj = individuals.get(i);
			String deathDate = indObj.getDeathDate();
			if (deathDate != null) {
				System.out.println("The Widowers are:" + " "
						+ famObj.getHusband());
				System.out.println("Wife name who died is:" + " "
						+ famObj.getWife() + "," + indObj.getDeathDate());
			}
		}
	}

	// HB_Sprint4_US24
	public void printAgeTeen(String file) {
		int agecountT = 0;
		for (int i = 0; i < individuals.size(); i++) {
			individual indObj = individuals.get(i);
			if (indObj.getAge() > 13 && (indObj.getAge() <= 18)) {
				agecountT += 1;
				System.out.println("The age of " + indObj.getIdentifier() + " "
						+ indObj.getGivenName() + " " + indObj.getSurName()
						+ " is " + indObj.getAge());
			}
		}
		System.out.println("The Total Number of Teens less than 19 are "
				+ agecountT);
		System.out.println();
	}

	// HB_Sprint4_US25
	public void printAgeAdult(String file) {
		int agecountA = 0;
		for (int i = 0; i < individuals.size(); i++) {
			individual indObj = individuals.get(i);
			if (indObj.getAge() > 18 && (indObj.getAge() <= 59))// HB_Sprint4_US
			{
				agecountA += 1;
				System.out.println("The age of adult " + indObj.getIdentifier()
						+ " " + indObj.getGivenName() + " "
						+ indObj.getSurName() + " is " + indObj.getAge());
			}
		}
		System.out
				.println("The Total Number of Adults are greater than 18 and less than 60 are "
						+ agecountA);
		System.out.println();
	}

	// Sprint_5
	// PT_Sprint5_22
	public static void abnormalage1(String file) {
		for (int i = 0; i < individuals.size(); i++) {
			individual indObj = individuals.get(i);
			if (indObj.getAge() > 200) {
				System.out.println("The age provided is an abnormal age" + " "
						+ indObj.getIdentifier() + " " + indObj.getGivenName()
						+ " " + indObj.getSurName() + " " + "is" + " "
						+ indObj.getAge());
			} else if (indObj.getAge() < 200) {
				System.out.println(" The age provided is a normal age" + " "
						+ indObj.getIdentifier() + " " + indObj.getGivenName()
						+ " " + indObj.getSurName() + " " + "is" + " "
						+ indObj.getAge());
			}
		}
	}

	// PT_Sprint5_35
	public static void abnormalage2(String file) {
		HashMap<Integer, String> hash = new HashMap<>();
		HashMap<Integer, Integer> hash1 = new HashMap<>();
		for (int i = 0; i < individuals.size(); i++) {
			for (int j = 0; j < families.size(); j++) {
				family famObj = families.get(j);
				individual indObj = individuals.get(i);
				String Id = indObj.getIdentifier();
				String Wife = famObj.getWife();
				String Hus = famObj.getHusband();
				int age = indObj.getAge();
				if (age != 0) {
					if (Id.equals(Hus) || Id.equals(Wife)) {
						hash.put(1, indObj.getGivenName());
						hash1.put(2, indObj.getAge());
						i++;
						indObj = individuals.get(i);
						hash.put(3, indObj.getGivenName());
						hash1.put(4, indObj.getAge());
						hash1.put(5, hash1.get(2) - (hash1.get(4)));
						if (hash1.get(5) >= 50)
							System.out.println(hash.get(1) + " " + "and "
									+ hash.get(3)
									+ " have abnormal age difference of" + " "
									+ hash1.get(5));
						else
							System.out.println("No abnormal age difference");
					}
				}
			}
		}
	}
//AM_Sprint5_US31
	public void abnormalChildren(String file) {
		for (int i = 0; i < families.size(); i++) {
			family famObj = families.get(i);
			for (int j = 0; j < individuals.size(); j++) {
				individual indObj = individuals.get(j);
				if (indObj.getFAMC() != null
						&& famObj.getIdentifier().equals(indObj.getFAMC())) {
					famObj.chilnum++;
				}
			}
			if (famObj.chilnum >= 10)
				System.out
						.println("Family "
								+ famObj.getIdentifier()
								+ " Has abnormal number of children's in family, count "
								+ famObj.chilnum);
		}
	}
//AM_Sprint5_US32
	public void printGeneration(String file) {
		for (int i = 0; i < individuals.size(); i++) {

			individual indObj = individuals.get(i);
			String birthdate = indObj.getBirthDate();

			if (birthdate != null) {
				String birthyear[] = birthdate.split(" ");
				if ((Integer.parseInt(birthyear[2]) <= 1900)
						|| (Integer.parseInt(birthyear[2]) >= 1999)) {

					System.out.println("Individual " + indObj.getIdentifier() +" "+
							 indObj.getGivenName() +" "+ indObj.getSurName()
							+ " have birthyear " + birthyear[2]
							+ " which is between 1900 - 1999 generation");
				}
				
				else if ((Integer.parseInt(birthyear[2]) <= 2000)
						|| (Integer.parseInt(birthyear[2]) >= 2015)) {
					System.out.println("Individual " + indObj.getIdentifier() +" "+
							 indObj.getGivenName() +" "+ indObj.getSurName() +" have birthyear " + birthyear[2]
							+ " which is in 2000 to current generation");
				} else
					System.out.println();
			}

		}

	}

}
