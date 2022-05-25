package filehandling;

import enums.Discipline;
import enums.MembershipStatus;
import member.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static enums.Discipline.*;
import static enums.MembershipStatus.*;

public class FileHandlingMemberList {
  private final String databaseFolder = "database/";
  private final String idCounterFile = "idCounter.csv";
  private final String competitiveFile = "competitive.csv";
  private final String motionistFile = "motionist.csv";
  
  public boolean saveMembers(ArrayList<Member> members) {
    boolean saved;
    ArrayList<Motionist> motionists = new ArrayList<>();
    ArrayList<Competitive> competitors = new ArrayList<>();
    for (Member member : members) {
      if (member instanceof Motionist) motionists.add((Motionist) member);
      else if (member instanceof Competitive) competitors.add((Competitive) member);
    }
    
    if (!saveMotionists(motionists)) return false;
    if (!saveCompetitors(competitors)) return false;
    return true;
  }
  
  // motionist
  public ArrayList<Motionist> loadMotionists() {
    String filePath = (databaseFolder + motionistFile);
    ArrayList<Motionist> motionists = new ArrayList<>();
    Motionist motionist;
    
    try {
      Scanner fileScanner = new Scanner(new File(filePath));
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
        
        // all parameters for motionist
        int ID = Integer.parseInt(token.next());
        String name = token.next();
        LocalDate birthday = LocalDate.parse(token.next());
        double restance = Double.parseDouble(token.next());
        MembershipStatus membershipStatus = setMembershipStatus(token.next());
        
        // create Motionist
        motionist = new Motionist(ID, name, birthday, membershipStatus);
        motionist.setRestance(restance);
        // add motionist
        motionists.add(motionist);
      }
      
    } catch (Exception e) {
      // create empty file if not found
      createEmptyFile(filePath);
      
      // empty
      return motionists;
    }
    
    return motionists;
  }
  
  public boolean saveMotionists(ArrayList<Motionist> motionists) {
    // overwrite old database-file
    try {
      PrintStream file = new PrintStream(databaseFolder + motionistFile);
      for (Motionist motionist : motionists) {
        file.printf("%s;%s;%s;%s;%s\n",
            motionist.getID(),
            motionist.getName(),
            motionist.getBirthday(),
            motionist.getRestance(),
            motionist.getMembershipStatus());
        
      }
      // release file
      file.close();
      return true;
      
    } catch (Exception e) {
      System.err.println(e);
      return false;
    }
  }
  
  public boolean appendMotionistFile(Motionist motionist) {
    // append to end of file
    if (motionist == null) return false;
    
    try {
      // block of code that can throw
      FileWriter file = new FileWriter(databaseFolder + motionistFile, true);
      
      file.write(String.format("%s;%s;%s;%s;%s\n",
          motionist.getID(),
          motionist.getName(),
          motionist.getBirthday(),
          motionist.getRestance(),
          motionist.getMembershipStatus()));
      
      // release file
      file.close();
      return true;
      
    } catch (Exception e) {
      System.err.println(e);
      return false;
    }
  }
  
  // competitive
  public ArrayList<Competitive> loadCompetitors() {
    String filePath = (databaseFolder + competitiveFile);
    ArrayList<Competitive> competitors = new ArrayList<>();
    Competitive competitive;
    ArrayList<Discipline> disciplines = new ArrayList<>();
    
    try {
      Scanner fileScanner = new Scanner(new File(filePath));
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
        
        // all parameters for competitive
        int ID = Integer.parseInt(token.next());
        String name = token.next();
        LocalDate birthday = LocalDate.parse(token.next());
        double restance = Double.parseDouble(token.next());
        MembershipStatus membershipStatus = setMembershipStatus(token.next());
        disciplines = setDisciplines(token.next());
        
        
        // create competitive
        competitive = new Competitive(ID, name, birthday, membershipStatus, disciplines);
        competitive.setRestance(restance);
        // add competitive
        competitors.add(competitive);
      }
      
    } catch (Exception e) {
      // create empty file if not found
      createEmptyFile(filePath);
      
      // empty
      return competitors;
    }
    
    return competitors;
  }
  
  public boolean saveCompetitors(ArrayList<Competitive> competitors) {
    // overwrite old database-file
    try {
      PrintStream file = new PrintStream(databaseFolder + competitiveFile);
      for (Competitive competitive : competitors) {
        file.printf("%s;%s;%s;%s;%s;%s\n",
            competitive.getID(),
            competitive.getName(),
            competitive.getBirthday(),
            competitive.getRestance(),
            competitive.getMembershipStatus(),
            stringDisciplines(competitive.getDisciplines()));
        
      }
      // release file
      file.close();
      return true;
    } catch (FileNotFoundException e) {
      System.err.println(e);
      return false;
    }
  }
  
  public boolean appendCompetitive(Competitive competitive) {
    // append to end of file
    if (competitive == null) return false;
    try {
      FileWriter file = new FileWriter(databaseFolder + competitiveFile, true);
      file.write(String.format("%s;%s;%s;%s;%s;%s\n",
          competitive.getID(),
          competitive.getName(),
          competitive.getBirthday(),
          competitive.getRestance(),
          competitive.getMembershipStatus(),
          stringDisciplines(competitive.getDisciplines())));
      
      // release file
      file.close();
      return true;
      
    } catch (Exception e) {
      System.err.println(e);
      return false;
    }
  }
  
  // idCounter
  public boolean loadIdCounter(MemberList memberList) {
    String filePath = (databaseFolder + idCounterFile);
    
    try {
      // open file
      Scanner fileScanner = new Scanner(new File(filePath));
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
        
        // parameter
        int idCounter = Integer.parseInt(token.next());
        
        // reassign
        memberList.setIdCounter(idCounter);
        return true;
      }
      
      // release file
      fileScanner.close();
      
    } catch (Exception e) {
      // create empty file if not found
      createEmptyFile(filePath);
    }
    
    return false;
  }
  
  public boolean saveIdCounter(MemberList memberList) {
    String filePath = (databaseFolder + idCounterFile);
    
    try {
      PrintStream write = new PrintStream(filePath);
      
      write.printf("%s\n",
          memberList.getIdCounter());
      
      write.close();
      return true;
      
    } catch (Exception e) {
      System.err.println(e);
      return false;
    }
  }
  
  // helper methods
  private String stringDisciplines(ArrayList<Discipline> disciplines) {
    StringBuilder stringDisciplines = new StringBuilder();
    int amountDisciplines = disciplines.size();
    
    
    for (int i = 0; i < 4; i++) {
      if (i < amountDisciplines)
        stringDisciplines.append(disciplines.get(i).getString() + ":");
      else
        stringDisciplines.append("NULL" + ":");
    }
    
    return stringDisciplines.toString();
  }
  
  private ArrayList<Discipline> setDisciplines(String disciplinesText) {
    // Mike;2001-05-19;0.0;ACTIVE;BACK_CRAWL;CRAWL;NULL;NULL  <- saved right now
    // Mike;2001-05-19;0.0;ACTIVE;BACK_CRAWL:CRAWL:NULL:NULL: <- saved after now
    ArrayList<Discipline> disciplines = new ArrayList<>();
    String[] discipline = disciplinesText.split(":", 4);
    
    // for each text discipline
    for (String text : discipline) {
      
      switch (text) {
        case "CRAWL" -> {
          disciplines.add(CRAWL);
        }
        case "BACK_CRAWL" -> {
          disciplines.add(BACK_CRAWL);
        }
        case "BREAST_STROKE" -> {
          disciplines.add(BREAST_STROKE);
        }
        case "BUTTERFLY" -> {
          disciplines.add(BUTTERFLY);
        }
        default -> {
        }
      }
    }
    
    return disciplines;
  }
  
  private MembershipStatus setMembershipStatus(String membershipStatusText) {
    if (membershipStatusText.equals("ACTIVE"))
      return ACTIVE;
    else if (membershipStatusText.equals("PASSIVE"))
      return PASSIVE;
    else
      return null;
  }
  
  private void createEmptyFile(String filePath) {
    try {
      File newFile = new File(filePath);
      if (newFile.createNewFile()) System.out.println("File created: " + newFile.getName());
      else System.out.println("File already exists.");
    } catch (Exception ee) {
      System.out.println("An error occurred.");
      ee.printStackTrace();
    }
  }
  
}
