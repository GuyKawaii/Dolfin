package filehandling;

import enums.Discipline;
import enums.MembershipStatus;
import member.Competitive;
import member.Member;
import member.Motionist;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static enums.Discipline.*;
import static enums.MembershipStatus.*;

public class FileHandlingMemberList {
  private final String databaseFolder = "database/";
  private final String competitiveFile = "competitive.csv";
  private final String motionistFile = "motionist.csv";

  // todo make this class singleton

  public boolean saveMembers(ArrayList<Member> members) {
    boolean saved;
    ArrayList<Motionist> motionists = new ArrayList<>();
    ArrayList<Competitive> competitors = new ArrayList<>();
    for (Member member : members) {
      if (member instanceof Motionist) motionists.add((Motionist) member);
      else if (member instanceof Competitive) motionists.add((Motionist) member);
    }

    if (!saveMotionists(motionists)) return false;
    if (!saveCompetitors(competitors)) return false;
    return true;
  }

  // motionist
  public ArrayList<Motionist> loadMotionists() {
    ArrayList<Motionist> motionists = new ArrayList<>();
    Motionist motionist;
    // todo add return or mutate directly?
    // todo make controller catch and deal with exception

    try {
      Scanner fileScanner = new Scanner(new File(databaseFolder + motionistFile));
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

        // all parameters for motionist
        String name = token.next();
        LocalDate birthday = LocalDate.parse(token.next());
        Double restance = Double.valueOf(token.next());
        MembershipStatus membershipStatus = setMembershipStatus(token.next());

        // create Motionist
        motionist = new Motionist(name, birthday, membershipStatus);
        motionist.setRestance(restance);
        // add motionist
        motionists.add(motionist);
      }

    } catch (Exception e) {
      System.err.println(e);
      return motionists;
    }

    return motionists;
  }

  public boolean saveMotionists(ArrayList<Motionist> motionists) {
    // overwrite old database-file
    try {
      PrintStream file = new PrintStream(databaseFolder + motionistFile);
      for (Motionist motionist : motionists) {
        file.printf("%s;%s;%s;%s\n",
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

    // todo what happens if motionist = null?
    try {
      // block of code that can throw
      FileWriter file = new FileWriter(databaseFolder + motionistFile, true);

      file.write(String.format("%s;%s;%s;%s\n",
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
    ArrayList<Competitive> competitors = new ArrayList<>();
    Competitive competitive;
    // todo add return or mutate directly?
    // todo make controller catch and deal with exception
    ArrayList<Discipline> disciplines = new ArrayList<>();

    try {
      Scanner fileScanner = new Scanner(new File(databaseFolder + competitiveFile));
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

        // all parameters for competitive
        String name = token.next();
        LocalDate birthday = LocalDate.parse(token.next());
        Double restance = Double.valueOf(token.next());
        MembershipStatus membershipStatus = setMembershipStatus(token.next());
        disciplines = setDisciplines(token.next());


        // create competitive
        competitive = new Competitive(name, birthday, membershipStatus, disciplines);
        competitive.setRestance(restance);
        // add competitive
        competitors.add(competitive);
      }

    } catch (Exception e) {
      System.err.println(e);
      return competitors;
    }

    return competitors;
  }

  public boolean saveCompetitors(ArrayList<Competitive> competitors) {
    // overwrite old database-file
    try {
      PrintStream file = new PrintStream(databaseFolder + competitiveFile);
      for (Competitive competitive : competitors) {
        file.printf("%s;%s;%s;%s;%s\n",
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
      file.write(String.format("%s;%s;%s;%s;%s\n",
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

  // helper methods
  public String stringDisciplines(ArrayList<Discipline> disciplines) {
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

  public ArrayList<Discipline> setDisciplines(String disciplinesText) {
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

  public MembershipStatus setMembershipStatus(String membershipStatusText) {
    if (membershipStatusText.equals("ACTIVE"))
      return ACTIVE;
    else if (membershipStatusText.equals("PASSIVE"))
      return PASSIVE;
    else
      return null;
  }

//  public void setMotionists(Motionist motionist) {
//    motionists.add(motionist);
//  }
//
//  public void setCompetitors(Competitive competitor) {
//    competitors.add(competitor);
//  }
//
//  public ArrayList<Motionist> getMotionists() {
//    return motionists;
//  }
//
//  public ArrayList<Competitive> getCompetitors() {
//    return competitors;
//  }
//
//  public void addMotionistsToFile(Motionist motionist) throws FileNotFoundException {
//    PrintStream output = new PrintStream("motionister.csv");
//    writeMotionist(output, motionist);
//  }
//
//  public void writeMotionist(PrintStream out, Motionist motionist) {
//    out.print(motionist.getName());
//    out.print(";");
//    out.print(motionist.getBirthday());
//    out.print(";");
//    out.print(motionist.getAge());
//    out.print(";");
//    out.print(motionist.getAgeGroup());
//    out.print(";");
//    out.print(motionist.getMembershipStatus());
//    out.print(";");
//    out.print(motionist.getContingent());
//    out.print(";");
//    out.print(motionist.getRestance());
//    out.print("\n");
//  }
//
//  public void addCompetitorsToFile(Competitive competitor) throws FileNotFoundException {
//    PrintStream out = new PrintStream("konkurrenceudøvere.csv");
//    writeCompetitorsToFile(out, competitor);
//  }
//
//  public void writeCompetitorsToFile(PrintStream out, Competitive competitor) {
//    out.print(competitor.getName());
//    out.print(";");
//    out.print(competitor.getBirthday());
//    out.print(";");
//    out.print(competitor.getAge());
//    out.print(";");
//    out.print(competitor.getAgeGroup());
//    out.print(";");
//    out.print(competitor.getMembershipStatus());
//    out.print(";");
//    out.print(competitor.getContingent());
//    out.print(";");
//    out.print(competitor.getRestance());
//    out.print(";");
//    out.print(competitor.getDisciplines());
//    out.print("\n");
//  }
//
//  public void loadMotionistFile() {
//    Scanner fileInput = new Scanner("motionister.csv");
//    while (fileInput.hasNextLine()) {
//      String line = fileInput.nextLine();
//      Scanner tokenScanner = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
//      String name = tokenScanner.next();
//      LocalDate birthday = LocalDate.parse(tokenScanner.next());
//      String membershipStatusText = tokenScanner.next();
//      MembershipStatus membershipStatus = setMembershipStatus(membershipStatusText);
//      double restance = tokenScanner.nextDouble();
//
//      transferMotionistsToArraylist(name, birthday, membershipStatus, restance);
//    }
//    fileInput.close();
//  }
//
//  public void transferMotionistsToArraylist(String name, LocalDate birthday,
//                                            MembershipStatus membershipStatus, double restance) {
//    Motionist motionist = new Motionist(name, birthday, membershipStatus);
//    motionist.setRestance(restance);
//    motionists.add(motionist);
//  }
//
//
//
//    /*
//    public void loadCompetitorFile(){
//        Scanner fileInput = new Scanner("konkurrenceudøvere.csv");
//        while (fileInput.hasNextLine()){
//            String line = fileInput.nextLine();
//            Scanner tokenScanner = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
//            String name = tokenScanner.next();
//            LocalDate birthday = LocalDate.parse(tokenScanner.next());
//            String membershipStatusText = tokenScanner.next();
//            MembershipStatus membershipStatus = setMembershipStatus(membershipStatusText);
//            double restance = tokenScanner.nextDouble();
//
//            ArrayList<Discipline> disciplines = new ArrayList<>();
//            for (int i = 0; i < 4; i++) {
//                String disciplineText = tokenScanner.next();
//
//                = setDisciplines(disciplineText);
//            } // TODO: 17/05/2022 virker det? tager scanner array som token?
//
//            transferCompetitorsToArraylist(name, birthday, membershipStatus, restance, disciplines);
//
//        }
//        fileInput.close();
//    } */
//
//
//  public void transferCompetitorsToArraylist(String name, LocalDate birthday,
//                                             MembershipStatus membershipStatus, double restance, ArrayList<Discipline> disciplines) {
//    Competitive competitor = new Competitive(name, birthday, membershipStatus, disciplines);
//    competitor.setRestance(restance);
//    competitors.add(competitor);
//  }
//
//
//  /*
//      public void saveCompetitors(ArrayList<Competitive> competitors) {
//          // overwrite old database-file
//          try {
//              PrintStream file = new PrintStream(competitiveFile);
//
//              for (Competitive competitive : competitors) {
//                  file.printf("%s;%s;%s;%s;%s\n",
//                          animal.getName(),
//                          animal.getDesc(),
//                          animal.getType(),
//                          animal.getAge(),
//                          animal.getWeight());
//              }
//
//              // release file
//              file.close();
//              return true;
//
//          } catch (FileNotFoundException e) {
//              System.err.println(e);
//              return false;
//          }
//
//
//      }
//   */

}
