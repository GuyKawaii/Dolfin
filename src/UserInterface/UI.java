package UserInterface;

import enums.AgeGroup;
import enums.Discipline;
import member.*;
import other.Team;
import record.RecordTraining;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

import static enums.Discipline.*;
import static enums.Discipline.BUTTERFLY;
import static enums.MembershipStatus.*;

public class UI {
  static Scanner scanner = new Scanner(System.in);
  
  public static void invalidInputMessage() {
    System.out.println(Color.TEXT_RED + "Invalid input" + Color.TEXT_RESET);
  }
  
  public static void printMainMenu() {
    System.out.print("""
        
        MAIN MENU:
        - Cashier Menu                      -> 1
        - Trainer Menu                      -> 2
        - Formand Menu                      -> 3
        - Exit                              -> Enter
        SELECT:\040""");
  }
  
  public static Integer inputPositiveNumber() {
    String input;
    Integer num = 0;
    
    while (num < 1) {
      // input
      input = scanner.nextLine();
      if (input.isEmpty()) return null;
      
      // number
      try {
        num = Integer.parseInt(input);
        
        if (num < 1) System.out.println(Color.TEXT_RED + "smallest valid number is 1" + Color.TEXT_RESET);
      } catch (Exception e) {
        System.out.println(Color.TEXT_RED + input + " is not valid" + Color.TEXT_RESET);
      }
    }
    
    return num;
  }
  
  public static LocalDate inputDate() {
    String input;
    LocalDate date = null;
    
    do {
      input = UI.capitalizeStringInput();
      if (input.isEmpty()) return null;
      
      try {
        date = LocalDate.parse(input);
      } catch (Exception e) {
        System.out.println(input + " is not valid date format");
      }
      
    } while (date == null);
    
    return date;
  }
  
  public static String capitalizeStringInput() { // todo maybe make real nice
    String input;
    Scanner scanner = new Scanner(System.in);
    input = scanner.nextLine();
    if (input.equals("")) {
      return "";
    }
    
    return input.substring(0, 1).toUpperCase() + input.substring(1);
  }

  public static String stringInput() { // todo maybe make real nice
    String input;
    Scanner scanner = new Scanner(System.in);
    input = scanner.nextLine();
    return input;
  }
  
  public static  String uppercaseStringInput() {
    String input;
    input = scanner.nextLine();
    if (input.equals("")) {
      return "";
    }
    
    return input.toUpperCase();
  }
  
  
  public static AgeGroup selectAgeGroup() {
    AgeGroup ageGroup;
    
    
    
    return null;
  }
  
  public static Competitive findActiveCompetitive(MemberList memberList) {
    String name;
    Competitive competitive = null;
    boolean foundActiveCompetitive = false;
    
    while (!foundActiveCompetitive) {
      // user input
      name = scanner.nextLine();
      if (name.isEmpty()) return null;
      
      competitive = memberList.getCompetitive(name);
      
      // tests
      if (competitive == null)
        System.out.printf(Color.TEXT_RED + "CANNOT FIND %s competitive member\n" + Color.TEXT_RESET, name);
      else if (competitive.getMembershipStatus() == PASSIVE)
        System.out.printf(Color.TEXT_RED + "%s - IS A PASSIVE MEMBER AND THEREFORE NOT ON A TEAM\n" + Color.TEXT_RESET, name);
      else
        foundActiveCompetitive = true;
    }
    
    return competitive;
  }
  
  
  public static void printMembers(ArrayList<Member> members) {
    for (Member member : members) {
      printMember(member);
    }
  }
  
  public static void printMember(Member member) {
    System.out.printf("%-15s %s, con:%7s, res:%7s, %7s, %s, ",
        member.getName(),
        member.getBirthday(),
        member.getContingent(),
        member.getRestance(),
        member.getMembershipStatus(),
        member.getAgeGroup());
    if (member instanceof Motionist) System.out.println("MOTIONIST");
    if (member instanceof Competitive) System.out.println("COMPETITIVE " + ((Competitive) member).getDisciplines());
  }
  
  public static void printCompetitors(ArrayList<Competitive> competitors) {
    for (Competitive competitive : competitors) {
      printCompetitive(competitive);
    }
  }
  
  public static void printCompetitive(Competitive competitive) {
    System.out.printf("%-15s %s, con:%7s, res:%7s, %7s, %s, ",
        competitive.getName(),
        competitive.getBirthday(),
        competitive.getContingent(),
        competitive.getRestance(),
        competitive.getMembershipStatus(),
        competitive.getAgeGroup());
    System.out.println("COMPETITIVE " + ((Competitive) competitive).getDisciplines());
  }
  
  public static void printMotionist(Motionist motionist) {
    System.out.printf("%-15s %s, con:%7s, res:%7s, %7s, %s\n",
        motionist.getName(),
        motionist.getBirthday(),
        motionist.getContingent(),
        motionist.getRestance(),
        motionist.getMembershipStatus(),
        motionist.getAgeGroup());
  }
  
  public static void printMotionists(ArrayList<Motionist> motionists) {
    for (Motionist motionist : motionists) {
      printMotionist(motionist);
    }
  }
  
  public static Discipline selectCompetitorDiscipline(Competitive competitive) {
    // TODO can we split this method up? If not where does it belong?
    // only get discipline if competitive has it
    Discipline discipline = null;
    String input;
    
    System.out.printf("""
        
        CAN SELECT: %s
        
        DISCIPLINE
        crawl -> c | back crawl -> bc | breast stroke -> bs | butterfly -> b
        - Return to main menu -> Enter
        SELECT:\040""", competitive.getDisciplines());
    
    getDiscipline:
    while (true) {
      input = scanner.nextLine();
      discipline = null;
      
      switch (input) {
        case "1", "crawl", "c" -> discipline = CRAWL;
        case "2", "back crawl", "bc" -> discipline = BACK_CRAWL;
        case "3", "breast stroke", "bs" -> discipline = BREAST_STROKE;
        case "4", "butterfly", "b" -> discipline = BUTTERFLY;
        case "" -> {
          return null;
        }
        default -> {
          System.out.printf("%sCANNOT SELECT BY \"%s\"%s - SELECT BY: crawl -> c | back crawl -> bc | breast stroke -> bs | butterfly -> b\n",
              Color.TEXT_RED,
              input,
              Color.TEXT_RESET);
        }
      }
      
      if (competitive.hasDiscipline(discipline)) break getDiscipline;
      else if (discipline != null) System.out.printf("%sMEMBER DOES NOT HAVE \"%s\"%s - MEMBER ONLY HAS: %S\n",
          Color.TEXT_RED,
          discipline,
          Color.TEXT_RESET,
          competitive.getDisciplines());
    }
    
    return discipline;
  }
  
  
  public static void printTopFiveDiscipline(Discipline discipline, Team team) {
    ArrayList<RecordTraining> records = team.topFiveForDiscipline(discipline);
    
    // heading
    System.out.printf("\n%s - %s", team.getAgeGroup().getString(), discipline.getString());
    if (records.size() == 0) System.out.printf(" [NA]\n");
    else {
      System.out.printf("\n%-15s  %-10s  %s\n", "NAME", "TIME (sec)", "DATE");
      
      // records
      for (RecordTraining record : records)
        System.out.printf("%-15s  %-10s  %s\n", record.getName(), record.getTimeInSeconds(), record.getDate());
    }
  }
  
  public static void printTeam(AgeGroup ageGroup, ArrayList<Competitive> competitors) {
    // todo name and parameters combined are incongruent
    
    
    
  }

  public static void printMemberList(MemberList memberList) {
    System.out.println(memberList);
  }
  
  public static Discipline selectDiscipline() {
    // todo hav valg af discipliner i en seperat hjælpemetode
    // only get discipline if competitive has it
    Discipline discipline = null;
    String input;
    
    System.out.print("""

        DISCIPLINE SELECT
        crawl -> c | back crawl -> bc | breast stroke -> bs | butterfly -> b
        - Return to main menu -> Enter
        SELECT:\040""");
    
    getDiscipline:
    while (discipline == null) {
      input = scanner.nextLine();
      
      switch (input) {
        case "1", "crawl", "c" -> discipline = CRAWL;
        case "2", "back crawl", "bc" -> discipline = BACK_CRAWL;
        case "3", "breast stroke", "bs" -> discipline = BREAST_STROKE;
        case "4", "butterfly", "b" -> discipline = BUTTERFLY;
        case "" -> {
          return null;
        }
        default -> {
          System.out.printf("%sCANNOT SELECT BY \"%s\"%s - SELECT BY: crawl -> c | back crawl -> bc | breast stroke -> bs | butterfly -> b\n",
              Color.TEXT_RED,
              input,
              Color.TEXT_RESET);
        }
      }
    }
    
    return discipline;
  }

  public static void printNumberInputError(String numberInput) {
    System.out.println(numberInput + " is not a valid number");
  }
}
