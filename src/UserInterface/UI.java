package UserInterface;

import enums.AgeGroup;
import enums.Discipline;
import member.*;
import other.TeamRecords;
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
  
  public static String capitalizeStringInput() {
    String input;
    Scanner scanner = new Scanner(System.in);
    input = scanner.nextLine();
    if (input.equals("")) {
      return "";
    }
    
    return input.substring(0, 1).toUpperCase() + input.substring(1);
  }
  
  public static String stringInput() {
    String input;
    Scanner scanner = new Scanner(System.in);
    input = scanner.nextLine();
    return input;
  }
  
  public static String uppercaseStringInput() {
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
    String stringID;
    int ID;
    Competitive competitive = null;
    boolean foundActiveCompetitive = false;
    
    while (!foundActiveCompetitive) {
      // user input
      stringID = scanner.nextLine();
      if (stringID.isEmpty()) return null;
      
      try {
        ID = Integer.parseInt(stringID);
        competitive = memberList.getCompetitive(ID);
      } catch (Exception e) {
      }
      
      // tests
      if (competitive == null)
        System.out.printf(Color.TEXT_RED + "%s ID DOES NOT BELONG TO ANY COMPETITIVE MEMBER\n" + Color.TEXT_RESET, stringID);
      else if (competitive.getMembershipStatus() == PASSIVE)
        System.out.printf(Color.TEXT_RED + "%s - IS A PASSIVE MEMBER AND THEREFORE NOT ON A TEAM\n" + Color.TEXT_RESET, stringID);
      else
        foundActiveCompetitive = true;
    }
    
    return competitive;
  }
  
  public static void printMemberHeader() {
    System.out.printf(Color.TEXT_BLUE + "%4s | %-15s | %-10s | %-11s | %-11s | %-7s | %-6s | %-11s\n" + Color.TEXT_RESET, "ID", "NAME", "BIRTHDAY", "CONTINGENT", "RESTANCE", "STATUS", "GROUP", "ACTIVITY");
  }
  
  public static void printMembers(ArrayList<Member> members) {
    if (members.size() == 0) {
      System.out.printf("%s%4s | %-15s | %-10s | %-11s | %-11s | %-7s | %-6s | %-11s%s[NA]\n",
          Color.TEXT_BLUE,
          "ID", "NAME", "BIRTHDAY", "CONTINGENT", "RESTANCE", "STATUS", "GROUP", "ACTIVITY",
          Color.TEXT_RESET);
      return;
    }
    
    // header
    printMemberHeader();
    
    // members
    for (Member member : members) {
      printMember(member);
    }
  }
  
  public static void printCompetitors(ArrayList<Competitive> competitors) {
    if (competitors.size() == 0) {
      System.out.printf("%s%4s | %-15s | %-10s | %-11s | %-11s | %-7s | %-6s | %-11s%s[NA]\n",
          Color.TEXT_BLUE,
          "ID", "NAME", "BIRTHDAY", "CONTINGENT", "RESTANCE", "STATUS", "GROUP", "ACTIVITY",
          Color.TEXT_RESET);
      return;
    }
    
    // header
    printMemberHeader();
    
    // members
    for (Member member : competitors) {
      printMember(member);
    }
  }
  
  public static void printMember(Member member) {
    System.out.printf("%4s | %-15s , %s , con:%7s , res:%7s , %-7s , %S , ",
        member.getID(),
        member.getName(),
        member.getBirthday(),
        member.getContingent(),
        member.getRestance(),
        member.getMembershipStatus(),
        member.getAgeGroup());
    if (member instanceof Motionist) System.out.println("MOTIONIST");
    if (member instanceof Competitive) System.out.println("COMPETITIVE " + ((Competitive) member).getDisciplines());
  }
  
  
  public static String[] twoStingsArguments() {
    // get 2 strings as a return
    String[] arguments;
    
    arguments = scanner.nextLine().toLowerCase().split(" ", 2);
  
    if (arguments.length == 1) {
      return new String[] {arguments[0], ""};
    }
    
    return arguments;
  }

//  public static void printCompetitors(ArrayList<Competitive> competitors) {
//    for (Competitive competitive : competitors) {
//      printCompetitive(competitive);
//    }
//  }
//
//  public static void printCompetitive(Competitive competitive) {
//    System.out.printf("%4s %-15s %s, con:%7s, res:%7s, %7s, %s, ",
//        competitive.getID(),
//        competitive.getName(),
//        competitive.getBirthday(),
//        competitive.getContingent(),
//        competitive.getRestance(),
//        competitive.getMembershipStatus(),
//        competitive.getAgeGroup());
//    System.out.println("COMPETITIVE " + ((Competitive) competitive).getDisciplines());
//  }

//  public static void printMotionist(Motionist motionist) {
//    System.out.printf("%4s %-15s %s, con:%7s, res:%7s, %7s, %s\n",
//        motionist.getID(),
//        motionist.getName(),
//        motionist.getBirthday(),
//        motionist.getContingent(),
//        motionist.getRestance(),
//        motionist.getMembershipStatus(),
//        motionist.getAgeGroup());
//  }

//  public static void printMotionists(ArrayList<Motionist> motionists) {
//    for (Motionist motionist : motionists) {
//      printMotionist(motionist);
//    }
//  }
  
  public static Discipline selectCompetitorDiscipline(Competitive competitive) {
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
  
  
  public static void printTopFiveDiscipline(Discipline discipline, TeamRecords teamRecords) {
    ArrayList<RecordTraining> records = teamRecords.topFiveForDiscipline(discipline);
    
    // heading
    System.out.printf("\n%s - %s", teamRecords.getAgeGroup().getString(), discipline.getString());
    if (records.size() == 0) System.out.printf(" [NA]\n");
    else {
      System.out.printf("\n%-15s  %-10s  %s\n", "NAME", "TIME (sec)", "DATE");
      
      // records
      for (RecordTraining record : records)
        System.out.printf("%-15s  %-10s  %s\n", record.getName(), record.getTimeInSeconds(), record.getDate());
    }
  }
  
  public static Discipline selectDiscipline() {
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
  
  public static void cannotFindID() {
    System.out.println("CANNOT FIND MEMBER WITH ID");
  }
  
  public static void printString(String string) {
    System.out.println(string);
  }
  
  public static String UpperCaseInput() {
    return scanner.nextLine().toUpperCase();
  }
}
