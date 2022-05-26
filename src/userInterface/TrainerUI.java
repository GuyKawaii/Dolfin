package userInterface;

import enums.AgeGroup;
import enums.Discipline;
import member.Competitive;

import java.time.LocalDate;

import static userInterface.Color.*;
import static enums.Discipline.*;
import static enums.Discipline.BUTTERFLY;

public class TrainerUI {
  public static void printAddRecord() {
    System.out.println("ADDED RECORD\n");
  }
  
  public static void printConventionHeader(AgeGroup ageGroup) {
    System.out.printf(TEXT_BLUE + "%s - CONVENTIONS ALREADY PRESENTLY USED\n" + TEXT_RESET, ageGroup);
  }
  
  public static void printEmptyConventionHeader(AgeGroup ageGroup) {
    System.out.printf("%s%s - CONVENTIONS ALREADY PRESENTLY USED%s [NA]\n", TEXT_BLUE, ageGroup , TEXT_RESET);
  }
  
  public static void printTrainerMenu() {
    System.out.print("""
          
          TRAINER:
          - add record                               -> 1
          - See Junior or senior top 5               -> 2 [J/S]
          - see Junior or senior team                -> 3 [J/S]
          - see junior or senior competition records -> 4 [J/S]
          - see already used conventions             -> 5 [J/S]
          - Return to main menu                      -> Enter
          SELECT:\040""");
  }
  
  public static void printInputConventionInstructions() {
    System.out.print("Choose convention by name: ");
  }
  
  public static void printConventionHasNoMembers(String convention, Discipline discipline) {
    System.out.printf("""
          
          %sCONVENTION: %s - DISCIPLINE: %s%s [NA]
          """, TEXT_BLUE, convention, discipline, TEXT_RESET);
  }
  
  public static void printConventionInfo(String convention, Discipline discipline) {
    System.out.printf("""
        
        %sCONVENTION: %s - DISCIPLINE: %s%s
        %-15s PLACEMENT  TIME  DATE
        """, TEXT_BLUE, convention, discipline, TEXT_RESET, "NAME");
  }
  
  public static void printMemberRecordInfo(String memberName, int placement, int timeInSeconds, LocalDate date) {
    System.out.printf("%-15s %9s  %-4s  %s\n", memberName, placement, timeInSeconds, date);
  }
  
  public static void printInputNameInRecordInstructions() {
    System.out.print("""
          
          ADDING RECORDS:
          - ID for person       -> ID of person
          - Return to main menu -> Enter
          SELECT:\040""");
  }
  
  public static void printInputTimeInRecordInstructions() {
    System.out.print("""
          
          TIME:
          - time (sec)          -> number
          - Return to main menu -> Enter
          SELECT:\040""");
  }
  
  public static void printInputDateInRecordInstructions() {
    System.out.print("\nINPUT date (yyyy-mm-dd): ");
  }
  
  public static void printInputRecordTypeInstructions() {
    System.out.print("""
          
          TYPE:
          - training            -> 1
          - competition         -> 2
          - Return to main menu -> Enter
          SELECT:\040""");
  }
  
  public static void printInputPlacementInRecordCompetitionInstructions() {
    System.out.print("""
            
            PLACEMENT:
            - placement           -> number
            - Return to main menu -> Enter
            SELECT:\040""");
  }
  
  public static void printInputPlaceInRecordCompetitionInstructions() {
    System.out.print("""
            
            TYPE:
            - convention          -> convention-name
            - Return to main menu -> Enter
            SELECT:\040""");
  }
  
  public static void printTeam(AgeGroup ageGroup, String trainerName) {
    System.out.printf("""
            
            %sTEAM: %s | TRAINER: %s - ACTIVE MEMBERS%s
            %-15s %s   | %5s | %10s | %13s | %9s |
            """, TEXT_GREEN,
        ageGroup, trainerName, TEXT_RESET,
        "NAME", "AGE", CRAWL, BACK_CRAWL, BREAST_STROKE, BUTTERFLY);
  }
  
  public static void printNoMembersInTeam(AgeGroup ageGroup, String trainerName) {
    System.out.printf("\n%sTEAM: %s | TRAINER: %s - ACTIVE MEMBERS%s [NA]\n", TEXT_GREEN, ageGroup, trainerName, TEXT_RESET);
  }
  
  public static void printMemberInTeam(Competitive competitive) {
    System.out.printf("%-15s %3s   | %5s | %10s | %13s | %9s |\n", competitive.getName(), competitive.getAge(),
        competitive.hasDiscipline(CRAWL),
        competitive.hasDiscipline(BACK_CRAWL),
        competitive.hasDiscipline(BREAST_STROKE),
        competitive.hasDiscipline(BUTTERFLY));
  }
  
  public static String printTeamCrawlCompetitionsRecords(String conventionName) {
  return String.format("discipline: %-13s | convention: %s", CRAWL, conventionName);
  }
  
  public static String printTeamBackCrawlCompetitionRecords(String conventionName) {
  return String.format("discipline: %-13s | convention: %s", BACK_CRAWL, conventionName);
  }
  
  public static String printTeamBreastStrokeCompetitionRecords(String conventionName) {
  return String.format("discipline: %-13s | convention: %s", BREAST_STROKE, conventionName);
  }
  
  public static String printTeamButterflyCompetitionRecords(String conventionName) {
    return String.format("discipline: %-13s | convention: %s", BUTTERFLY, conventionName);
  }
}
