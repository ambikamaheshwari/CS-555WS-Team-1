package Gedcom;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.gedcom4j.parser.GedcomParserException;

public class Test {

static Scanner scan;
static BufferedWriter outFile;
static int birthYear = 0;
static int birthMonth = 0;
static String birthDay = "";
static int deathYear = 0;
static int deathMonth = 0;
static int deathDay = 0;
static String name = "";
static String person = "";
static String sex = "";
static String famC = "";
static String famS = "";
static String man = "";
static String woman = "";
static String child = "";

public static void parse() throws IOException {
    scan = new Scanner(new FileReader("C:/Users/Puneet_lampard/Desktop/CS-555WS-Team-1/TEAM-1-Family-2-23-Feb-2015.ged"));
    outFile = new BufferedWriter(new FileWriter("C:/Users/Puneet_lampard/Desktop/CS-555WS-Team-1/output.txt"));
    String reader = scan.nextLine();
    int count = 0;

    while (scan.hasNextLine()) {

        if (reader.contains("NAME") && count < 1) {
            reader = reader.substring(1).replace("/", "");
            count++;
            System.out.println(reader);
            name = reader.replace("NAME", "");
        }

        if (reader.startsWith("0")) {
            person = reader.trim().substring(birthYear).replace("@", "")
                    .replace("I", "").trim().toLowerCase();
            System.out.print(person);
            count = 0;
        }
        if (reader.contains("BIRT")) {
            scan.nextLine();
            birthDay = Integerreader.substring(birthDay).trim();
        }

        if (reader.equalsIgnoreCase("") || reader.equalsIgnoreCase(" ")) {
            outFile.write("name(" + person + ", " + "'" + name.trim() + "'"
                    + ")." + "\n" + birthDay);

        }

        reader = scan.nextLine();
    }
}

public static void main(String[] args) throws IOException {
    parse();

}
}